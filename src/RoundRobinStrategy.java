import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class RoundRobinStrategy implements IPolicyManager {
    private Iterator<Invoker> invokerIterator;

    public RoundRobinStrategy(List<Invoker> invokers) {
        List<Invoker> freeInvokers = invokers.stream().filter(invoker -> invoker.getAvailableRam() > 0).toList();
        this.invokerIterator = freeInvokers.iterator();
    }

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        List<Invoker> freeInvokers = invokers.stream()
                .filter(invoker -> invoker.getAvailableRam() > 0)
                .toList();

        if (functions.isEmpty() || freeInvokers.isEmpty()) {
            return null;
        }

        int functionsPerInvoker = functions.size() / freeInvokers.size();
        int remainingFunctions = functions.size() % freeInvokers.size();

        Invoker assignedInvoker = null;

        for (int i = 0; i < remainingFunctions; i++) {
            assignedInvoker = getNextInvoker(freeInvokers);
        }

        // Distribute the remaining functions among the invokers
        for (Invoker invoker : freeInvokers) {
            for (int i = 0; i < functionsPerInvoker; i++) {
                assignedInvoker = getNextInvoker(freeInvokers);
            }
        }
        assignedInvoker.setAssignedFunctions(assignedInvoker.getAssignedFunctions() + 1);
        return assignedInvoker;
    }

    private Invoker getNextInvoker(List<Invoker> freeInvokers) {
        if (!invokerIterator.hasNext()) {
            invokerIterator = freeInvokers.iterator();
        }
        return invokerIterator.next();
    }
}