import java.util.List;
import java.util.function.Function;

public class GreadyGroupStrategy implements IPolicyManager {
//asigna la funcion al invoker con mas espacio disponible
    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        List<Invoker> freeInvokers = invokers.stream().filter(invoker -> invoker.getAvailableRam() >= 0).toList();
        if (freeInvokers.isEmpty()) {
            return null;
        }
        return freeInvokers.get(0);
    }
}
