public class Metric {
    private final String action;
    private final double executionTime;
    private final int usedMemory;

    public Metric(String action, double executionTime, int usedMemory) {
        this.action = action;
        this.executionTime = executionTime;
        this.usedMemory = usedMemory;
    }

    public String getAction() {
        return action;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public int getUsedMemory() {
        return usedMemory;
    }

}
