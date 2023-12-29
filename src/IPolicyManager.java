import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface IPolicyManager {
    Invoker assignFunction(List<Invoker> invokers, List<Function<Map<String, Integer>, Integer>> function);
}
