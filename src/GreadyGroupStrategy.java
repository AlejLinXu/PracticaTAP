import java.util.List;

public class GreadyGroupStrategy implements IPolicyManager {
//Asigna la función al invoker con más RAM disponible.
    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<FunctionWithRam> functions) {
        //Si no hay invokers disponibles o no hay funciones, devolvemos null
        if (invokers.isEmpty() || functions.isEmpty()) {
            return null;
        }
        Invoker invokerAux = new Invoker();
        invokerAux.setAvailableRam(0);
        for (Invoker invoker : invokers) {
            if (invoker.getAvailableRam() > invokerAux.getAvailableRam()){
                invokerAux = invoker;
                break;
            }
        }
        return invokerAux;
    }
}
