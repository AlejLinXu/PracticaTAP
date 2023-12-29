import java.util.List;
import java.util.function.Function;

public class GreedyGroupStrategy implements IPolicyManager {
//Asigna la función al invoker con más RAM disponible.
    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        //Si no hay invokers disponibles o no hay funciones, devolvemos null
        if (invokers.isEmpty() || functions.isEmpty()) {
            return null;
        }
        Invoker invokerAux = new Invoker(invokers.get(0).getAvailableRam());
        invokerAux.setAvailableRam(0);
        for (Invoker invoker : invokers) {
            if (invoker.getAvailableRam() > invokerAux.getAvailableRam() && invoker.getAvailableRam() >= 0) {
                invokerAux = invoker;
                break;
            }
        }
        return invokerAux;
    }
}
