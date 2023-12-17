import java.util.List;
import java.util.function.Function;

public class UniformGroupStrategy implements IPolicyManager{
    //define un tamaño de grupo (6 por ejemplo) y que coloca de manera uniforme entre todos los Invokers
    private int groupSize;

    public UniformGroupStrategy(int groupSize) {
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
        int functionsPerInvoker = numFunctions/numInvokers;
        int remainingFunctions = numFunctions % numInvokers;

        for (Invoker invoker : invokers) {
            if (groupSize > functionsPerInvoker && remainingFunctions != 0) {
                invokerAux = invoker;
                remainingFunctions--;
                return invoker;
            }
        }
    }
}
