import java.util.function.Function;

public interface InvokerInterface {
    public Object executeAction(Function<Object, Object> action, Object params);
}
