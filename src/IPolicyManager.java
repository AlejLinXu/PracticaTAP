import java.util.List;
import java.util.function.Function;

public interface IPolicyManager {
    Invoker assignFunction(List<Invoker> invokers, List<FunctionWithRam> function);


}
