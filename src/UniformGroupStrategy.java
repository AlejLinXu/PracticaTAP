import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UniformGroupStrategy implements IPolicyManager {
    //define un tamaño de grupo (6 por ejemplo) y que coloca de manera uniforme entre todos los Invokers
    private final int groupSize;

    public UniformGroupStrategy(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function<Map<String, Integer>, Integer>> functions) {
        List<Invoker> freeInvokers = invokers.stream()
                .filter(invoker -> invoker.getAvailableRam() > 0)
                .toList();

        if (functions.isEmpty() || freeInvokers.isEmpty() || groupSize <= 0) {
            return null;
        }

        Invoker selectedInvoker = new Invoker(-1);

        for (Invoker invoker : freeInvokers) {
            if (invoker.getNumAssignedFunctions() < groupSize && invoker.getAvailableRam() > selectedInvoker.getAvailableRam()){
                selectedInvoker = invoker;
            }
        }
        return selectedInvoker;
    }
}

