import java.util.List;
import java.util.function.Function;

public class UniformGroupStrategy implements IPolicyManager {
    //define un tamaño de grupo (6 por ejemplo) y que coloca de manera uniforme entre todos los Invokers
    private int groupSize;

    public UniformGroupStrategy(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        List<Invoker> freeInvokers = invokers.stream().filter(invoker -> invoker.getAvailableRam() >= 0).toList();
        if (freeInvokers.isEmpty()) {
            return null;
        }
        int index = functions.size() % freeInvokers.size();
        return freeInvokers.get(index);
    }
}

