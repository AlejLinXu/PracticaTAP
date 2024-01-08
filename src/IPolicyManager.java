import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface IPolicyManager {
    /**
     * Assigns a function to an invoker depending on the policy.
     * @param invokers
     * @param functions
     * @return the invoker that will execute the function.
     */
    Invoker assignFunction(List<Invoker> invokers, List<Function<Map<String, Integer>, Integer>> functions);
}
