public class Metric {
    private String action;
    private long executionTime;
    private int usedMemory;

    public Metric(String action, long executionTime, int usedMemory) {
        this.action = action;
        this.executionTime = executionTime;
        this.usedMemory = usedMemory;
    }

    public String getAction() {
        return action;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public int getUsedMemory() {
        return usedMemory;
    }

}
