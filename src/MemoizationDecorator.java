import java.util.concurrent.Callable;

public class MemoizationDecorator extends Decorator{
    public MemoizationDecorator(Callable wrapee) {
        super(wrapee);
    }

    @Override
    public Object call() throws Exception {
        Object result = Cache.get(wrapee.toString());
        if (result == null) {
            result = wrapee.call();
            Cache.put(wrapee.toString(), result);
        }
        return result;
    }
}
