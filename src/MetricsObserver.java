public interface MetricsObserver {
    void updateMetrics(String action, long executionTime, int usedMemory);
}
