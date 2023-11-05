import java.util.function.Function;

public class Invoker implements InvokerInterface {
    public Invoker() {

    }
    @Override
    public Object executeAction(Function<Object, Object> action, Object params) {
        return action.apply(params);
    }
}
