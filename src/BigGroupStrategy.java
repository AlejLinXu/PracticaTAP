import java.util.List;
import java.util.function.Function;

public class BigGroupStrategy implements IPolicyManager{
    //coloca los grupos empaquetados si es posible.
    private int groupSize;
    public BigGroupStrategy(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<FunctionWithRam> functions) {
        List<Invoker> freeInvokers = invokers.stream().filter(invoker -> invoker.getAvailableRam() >= 0).toList();
        if (freeInvokers.isEmpty()) {
            return null;
        }
        return freeInvokers.get(0);
    }
}
