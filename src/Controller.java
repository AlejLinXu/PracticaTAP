import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Function;

public class Controller<T, V> implements MetricsObserver, IController<T, V>{
    private final ArrayList<Invoker> listInvokers;
    private final HashMap<String, Function<T, V>> mapActions;
    private final HashMap<String, Callable<V>> mapFactorialActions;
    private final HashMap<String, Integer> mapRam;
    private final IPolicyManager policy;
    private final List<MetricsObserver> metricsObservers;
    private final List<Metric> metricList;

    //constructor
    public Controller (int numInvokers, int invokerRam, IPolicyManager policy) {
        listInvokers = new ArrayList<>();
        for (int i = 0; i < numInvokers; i++) {
            this.listInvokers.add(i, new Invoker(invokerRam));
        }
        this.mapActions = new HashMap<>();
        this.mapRam = new HashMap<>(4, 4);
        this.policy = policy;
        this.metricsObservers = new ArrayList<>();
        this.metricList = new ArrayList<>();
        this.mapFactorialActions = new HashMap<>();
    }
    //constructor
    public Controller() {
        listInvokers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            this.listInvokers.add(i, new Invoker(1024));
        }
        this.policy = new RoundRobinStrategy();
        this.metricsObservers = new ArrayList<>();
        this.metricList = new ArrayList<>();
        this.mapFactorialActions = new HashMap<>();
        this.mapActions = new HashMap<>();
        this.mapRam = new HashMap<String, Integer>(4, 4);
    }

    /**
     * Register an action to be executed by the controller
     * @param action
     * @param f
     * @param ram
     */
    public void registerAction(String action, Function<T, V> f, int ram) {
        this.mapActions.put(action, f);
        this.mapRam.put(action, ram);
    }

    /**
     * Register the factorial action to be executed by the controller
     * @param action
     * @param factorialAction
     * @param ram
     */
    public void registerFactorialAction(String action, Callable<Integer> factorialAction, int ram) {
        this.mapFactorialActions.put(action, (Callable<V>) factorialAction);
        this.mapRam.put(action, ram);
    }

    /**
     * Assigns a function to an invoker
     * @return listInvokers
     */
    public List<Invoker> getListInvokers() {
        return listInvokers;
    }

    /**
     * Assigns a function to an invoker
     * @param actionName
     * @param params
     * @return result
     */
    public Object invoke(String actionName, Map<String, Integer> params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null){
            if(listInvokers.get(0).getAvailableRam() < mapRam.get(actionName)){
                throw new IllegalArgumentException("Not enough RAM");
            }

            long startTime = System.nanoTime();

            Object result = listInvokers.get(0).executeAction(action, params);

            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            double msExecution = (double) executionTime / 1000000.0;
            int usedMemory = mapRam.get(actionName);

            updateMetrics(actionName, msExecution, usedMemory);

            return result;
        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
    }

    /**
     * Assigns a callable function to an invoker
     * @param actionName
     * @return result
     */
    @Override
    public V invokeCallable(String actionName){
        Callable<V> factorialAction = mapFactorialActions.get(actionName);
        if (factorialAction != null) {
            try {
                // Check available RAM before executing
                if (listInvokers.get(0).getAvailableRam() < mapRam.get(actionName)) {
                    throw new IllegalArgumentException("Not enough RAM");
                }

                long startTime = System.nanoTime();
                V result = factorialAction.call();
                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;
                double msExecution = (double) executionTime / 1000000.0;
                int usedMemory = mapRam.get(actionName);

                updateMetrics(actionName, msExecution, usedMemory);

                return result;
            } catch (Exception e) {
                throw new RuntimeException("Error executing factorial action", e);
            }
        } else {
            throw new IllegalArgumentException("Factorial action not registered: " + actionName);
        }
    }

    /**
     * Assigns asynchronous a function to an invoker
     * @param actionName
     * @param params
     * @return result
     */
    public Future<Object> invokeAsync(String actionName, Map<String, Integer> params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null) {
            if (listInvokers.get(0).getAvailableRam() > mapRam.get(actionName)) {
                listInvokers.get(0).setRam(mapRam.get(actionName));
            } else throw new IllegalArgumentException("Not enough RAM");

        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
        return listInvokers.get(0).executeActionAsync(action, params);
    }

    /**
     * Assigns asynchronous an integer function to an invoker
     * @param actionName
     * @return result
     */
    public Future<Object> invokeAsyncInt (String actionName, int params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null){
            return listInvokers.get(0).executeActionAsync(action, params);
        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
    }

    /**
     * Gets the RAM assigned to a function
     * @param function
     * @return ram
     */
    public int getRam(String function) {
        return mapRam.get(function);
    }

    /**
     * Gets the actions registered
     * @return actions
     */
    public List<String> getActions() {
       //devuleve una lista con todas las acciones
        List<String> actions = new ArrayList<>(mapActions.keySet());
        actions.sort(String::compareTo);
        return actions;
    }

    /**
     * Gets the factorial actions registered
     * @return actions
     */
    public List<String> getFactorialActions() {
        //devuleve una lista con todas las acciones
        List<String> actions = new ArrayList<>(mapFactorialActions.keySet());
        actions.sort(String::compareTo);
        return actions;
    }

    /**
     * notify the metrics to the observers
     * @param action
     * @param executionTime
     * @param usedMemory
     */
    public void notifyMetrics(String action, double executionTime, int usedMemory) {
        for (MetricsObserver observer : metricsObservers) {
            observer.updateMetrics(action, executionTime, usedMemory);
        }
    }

    /**
     * Gets the metric list
     * @return metricList
     */
    public List<Metric> getMetrics(){
        return metricList;
    }

    /**
     * Updates the metrics
     * @param action
     * @param executionTime
     * @param usedMemory
     */
    @Override
    public void updateMetrics(String action, double executionTime, int usedMemory) {
        metricList.add(new Metric(action, executionTime, usedMemory));
        notifyMetrics(action, executionTime, usedMemory);
    }

    /**
     * Gets maximum execution time
     * @return max
     */
    public double getMaxExecutionTime(){
        double max = 0;
        for (Metric metric : metricList) {
            if (metric.getExecutionTime() > max) {
                max = metric.getExecutionTime();
            }
        }
        return max;
    }

    /**
     * Gets minimum execution time
     * @return min
     */
    public double getMinExecutionTime(){
        double min = metricList.get(0).getExecutionTime();
        for (Metric metric : metricList) {
            if (metric.getExecutionTime() < min) {
                min = metric.getExecutionTime();
            }
        }
        return min;
    }

    /**
     * Gets average execution time
     * @return average
     */
    public double getAverageExecutionTime(){
        double sum = 0;
        for (Metric metric : metricList) {
            sum += metric.getExecutionTime();
        }
        return sum/metricList.size();
    }

    /**
     * Gets total used memory
     * @return sum
     */
    public int getTotalUsedMemory(){
        int sum = 0;
        for (Metric metric : metricList) {
            sum += metric.getUsedMemory();
        }
        return sum;
    }
}
