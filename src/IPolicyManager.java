import java.util.List;
import java.util.function.Function;

public interface IPolicyManager {
    void assignFunction(List<Invoker> invokers, List<Function> function);
}
