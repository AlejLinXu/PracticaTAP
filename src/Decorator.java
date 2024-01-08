import java.util.concurrent.Callable;

public class Decorator<R> implements Callable<R> {

    public Callable<R> wrapee;
    public Decorator(Callable<R> wrapee) {
        this.wrapee = wrapee;
    }

    /**
     * @see java.util.concurrent.Callable#call()
     * @return R
     * @throws Exception
     */
    @Override
    public R call() throws Exception {
        return wrapee.call();
    }
}
