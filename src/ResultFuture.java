public class ResultFuture {
    private Object result;
    private boolean isDone;

    public ResultFuture() {
        this.isDone = false;
    }

    public synchronized void setResult(Object result) {
        this.result = result;
        this.isDone = true;
        notifyAll();
    }

    public synchronized Object getResult() throws InterruptedException {
        while (!isDone) {
            wait();
        }
        return result;
    }
}
