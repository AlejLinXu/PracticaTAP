import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public class Invoker implements InvokerInterface {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int ram = 1024;
    private int availableRam;
    private int numAssignedFunctions = 0;
    private List<Function> assignedFunctions = new ArrayList<>();

    private Cache cache = new Cache();
    private boolean cacheEnabled = false; //cache for decorator
    private boolean timerOn = false; //timer for decorator
    public Invoker(int availableRam) {
        this.availableRam = availableRam;
    }

    /**
     * Execute the action
     * @param action
     * @param params
     * @return the result of the action
     */
    @Override
    public Object executeAction(Function<Object, Object> action, Object params) {
        return action.apply(params);
    }

    /**
     * Execute the action asynchronously
     * @param action
     * @param params
     * @return the result of the action
     */
    public Future<Object> executeActionAsync(Function<Object, Object> action, Object params) {
        return executorService.submit(() -> action.apply(params));
    }


    //getters y setters RAM

    /**
     * Get the cache
     * @return cache
     */
    public Cache getCache() {
        return cache;
    }

    /**
     * Sets the cache
     * @param cache
     */
    public void setCache(Cache cache) {
        this.cache = cache;
    }

    /**
     * Get the cache status
     * @return cacheEnabled
     */
    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    /**
     * Sets the cache status
     * @param cacheEnabled
     */
    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    /**
     * Get the timer status
     * @return timerOn
     */
    public boolean isTimerOn() {
        return timerOn;
    }

    /**
     * Sets the timer status
     * @param timerOn
     */
    public void setTimerOn(boolean timerOn) {
        this.timerOn = timerOn;
    }

    /**
     * Get the RAM
     * @return ram
     */
    public int getRam() {
        return ram;
    }

    /**
     * Get the available RAM
     * @return availableRam
     */
    public int getAvailableRam() {
        return availableRam;
    }

    /**
     * Set the available RAM
     * @param availableRam
     */
    public void setAvailableRam(int availableRam) {
        this.availableRam = availableRam;
    }

    /**
     * Set the RAM
     * @param ram
     */
    public void setRam(int ram) {
        this.availableRam = availableRam - ram;
    }

    /**
     * Get the number of assigned functions
     * @return numAssignedFunctions
     */
    public int getNumAssignedFunctions() {
        return numAssignedFunctions;
    }

    /**
     * Set the number of assigned functions
     * @param numAssignedFunctions
     */
    public void setNumAssignedFunctions(int numAssignedFunctions) {
        this.numAssignedFunctions = numAssignedFunctions;
    }

    /**
     * Gets a list of the assigned functions
     * @return assignedFunctions
     */
    public List<Function> getAssignedFunctions() {
        return assignedFunctions;
    }

    /**
     * Adds a function to the list of assigned functions
     * @param function
     */
    public void addFunction(Function function) {
        assignedFunctions.add(function);
    }

}
