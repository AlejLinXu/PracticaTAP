public class Metric {
    private final String action;
    private final double executionTime;
    private final int usedMemory;


    public Metric(String action, double executionTime, int usedMemory) {
        this.action = action;
        this.executionTime = executionTime;
        this.usedMemory = usedMemory;
    }

    /**
     * Get the action
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * Get the execution time
     * @return executionTime
     */
    public double getExecutionTime() {
        return executionTime;
    }

    /**
     * Get the used memory
     * @return usedMemory
     */
    public int getUsedMemory() {
        return usedMemory;
    }

}
