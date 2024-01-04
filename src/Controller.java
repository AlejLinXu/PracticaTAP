import java.util.*;
import java.util.function.Function;

public class Controller<T, V> implements MetricsObserver{
    private final ArrayList<Invoker> listInvokers;
    private final HashMap<String, Function<T, V>> mapActions;
    private final HashMap<String, Integer> mapRam;
    private final IPolicyManager policy;
    private final List<MetricsObserver> metricsObservers;
    private final List<Metric> metricList;

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
    }

//    public Controller() {
//        listInvokers = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            this.listInvokers.add(i, new Invoker());
//        }
//        this.mapActions = new HashMap<>();
//        this.mapRam = new HashMap<String, Integer>(4, 4);
//    }

    public void registerAction(String action, Function<T, V> f, int ram) {
        this.mapActions.put(action, f);
        this.mapRam.put(action, ram);
    }

    public List<Invoker> getListInvokers() {
        return listInvokers;
    }

    public Object invoke(String actionName, Map<String, Integer> params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null){
            if(listInvokers.get(0).getAvailableRam() < mapRam.get(actionName)){
                throw new IllegalArgumentException("Not enough RAM");
            }

            long executionTime = System.currentTimeMillis();
            int usedMemory = mapRam.get(actionName);

            updateMetrics(actionName, executionTime, usedMemory);

            return listInvokers.get(0).executeAction(action, params);
        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
    }

    /*public Future<Object> invokeAsync(String actionName, Map<String, Integer> params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null) {
            if (listInvokers.get(0).getAvailableRam() > mapRam.get(actionName)) {
                listInvokers.get(0).setRam(mapRam.get(actionName));
            } else throw new IllegalArgumentException("Not enough RAM");

        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
        return listInvokers.get(0).executeActionAsync(action, params);
    }


    public Future<Object> invokeAsyncInt (String actionName, int params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null){
            return listInvokers.get(0).executeActionAsync(action, params);
        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
    }*/


    public int getRam(String function) {
        return mapRam.get(function);
    }


    public List<String> getActions() {
       //devuleve una lista con todas las acciones
        List<String> actions = new ArrayList<>(mapActions.keySet());
        actions.sort(String::compareTo);
        return actions;
    }

    public void notifyMetrics(String action, long executionTime, int usedMemory) {
        for (MetricsObserver observer : metricsObservers) {
            observer.updateMetrics(action, executionTime, usedMemory);
        }
    }

    public List<Metric> getMetrics(){
        return metricList;
    }

    @Override
    public void updateMetrics(String action, long executionTime, int usedMemory) {
        metricList.add(new Metric(action, executionTime, usedMemory));
        notifyMetrics(action, executionTime, usedMemory);
    }

    //methods to get max, min and average execution time
    public long getMaxExecutionTime(){
        long max = 0;
        for (Metric metric : metricList) {
            if (metric.getExecutionTime() > max) {
                max = metric.getExecutionTime();
            }
        }
        return max;
    }

    public long getMinExecutionTime(){
        long min = metricList.get(0).getExecutionTime();
        for (Metric metric : metricList) {
            if (metric.getExecutionTime() < min) {
                min = metric.getExecutionTime();
            }
        }
        return min;
    }

    public long getAverageExecutionTime(){
        long sum = 0;
        for (Metric metric : metricList) {
            sum += metric.getExecutionTime();
        }
        return sum/metricList.size();
    }
}
