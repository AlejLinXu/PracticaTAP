import java.util.List;
import java.util.function.Function;

public class RoundRobinStrategy implements IPolicyManager {
    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        List<Invoker> freeInvokers = invokers.stream()
                .filter(invoker -> invoker.getAvailableRam() > 0)
                .toList();

        int functionsPerInvoker = functions.size() / freeInvokers.size();

        List<Invoker> assignableInvokers = freeInvokers.stream()
                .filter(invoker -> invoker.getNumAssignedFunctions() < functionsPerInvoker)
                .toList();

        if (functions.isEmpty() || assignableInvokers.isEmpty()) {
            return null;
        }

        return assignableInvokers.get(0);
    }
}