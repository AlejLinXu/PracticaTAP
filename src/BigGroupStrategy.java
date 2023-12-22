import java.util.List;
import java.util.function.Function;

public class BigGroupStrategy implements IPolicyManager{
    //coloca los grupos empaquetados si es posible.
    private int groupSize;
    public BigGroupStrategy(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        if (invokers.isEmpty() || functions.isEmpty()) {
            return null;
        }
        Invoker invokerAux = null;
        int numFunctions = functions.size();
        int numInvokers = invokers.size();

    }
}
