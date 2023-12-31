import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RoundRobinStrategy implements IPolicyManager {
    private int currentIndex = 0;

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function<Map<String, Integer>, Integer>> functions) {
        if (invokers.isEmpty() || functions.isEmpty()) {
            return null;
        }

        Invoker selectedInvoker = invokers.get(currentIndex);

        currentIndex = (currentIndex + 1) % invokers.size();

        return selectedInvoker;
    }
}
