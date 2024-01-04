public interface MetricsObserver {
    void updateMetrics(String action, double executionTime, int usedMemory);
}
