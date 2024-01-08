public interface MetricsObserver {
    /**
     * Updates the metrics of the invoker.
     * @param action
     * @param executionTime
     * @param usedMemory
     */
    void updateMetrics(String action, double executionTime, int usedMemory);
}
