import java.util.concurrent.Callable;

public class MemoizationDecorator extends Decorator{
    public Invoker invoker;
    public MemoizationDecorator(Callable wrapee, Invoker invoker) {
        super(wrapee);
        this.invoker = invoker;
    }

    @Override
    public Object call() throws Exception {

        Object result = invoker.getCache().get(wrapee.toString());
        if (result == null) {
            result = wrapee.call();
            invoker.getCache().put(wrapee.toString(), result);
            //System.out.println("Cache result: " + result);
        }else  {
            result = Cache.get(wrapee.toString());
        }
        return result;
    }
}
