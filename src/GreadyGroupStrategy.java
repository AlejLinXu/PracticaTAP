import java.util.List;
import java.util.function.Function;

public class GreadyGroupStrategy implements IPolicyManager {
//Asigna la función al invoker con más RAM disponiblee.
    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        List<Invoker> freeInvokers = invokers.stream().filter(invoker -> invoker.getAvailableRam() > 0).toList();
        if (freeInvokers.isEmpty()) {
            return null;
        }
        Invoker invokerAux = new Invoker();
        invokerAux.setAvailableRam(-1);
        for (Invoker invoker : freeInvokers) {
            if (invoker.getAvailableRam() > invokerAux.getAvailableRam()) {
                invokerAux = invoker;
            }
        }
        return invokerAux;
    }
}
