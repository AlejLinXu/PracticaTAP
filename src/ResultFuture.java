public class ResultFuture {
    private Object result;
    private boolean isDone;

    public ResultFuture() {
        this.isDone = false;
    }

    /**
     * Sets the future result
     * @param result
     */
    public synchronized void setResult(Object result) {
        this.result = result;
        this.isDone = true;
        notifyAll();
    }

    /**
     * Gets the future result
     * @return
     * @throws InterruptedException
     */
    public synchronized Object getResult() throws InterruptedException {
        while (!isDone) {
            wait();
        }
        return result;
    }
}
