import java.util.List;
import java.util.function.Function;

public class RoundRobinStrategy implements IPolicyManager {
    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        //Filtramos los invokers que tienen RAM disponible
        List<Invoker> freeInvokers = invokers.stream()
                .filter(invoker -> invoker.getAvailableRam() > 0)
                .toList();
        //Si no hay invokers disponibles o no hay funciones, devolvemos null
        if (functions.isEmpty() || freeInvokers.isEmpty()) {
            return null;
        }

        int functionsPerInvoker = functions.size() / freeInvokers.size();

        //Creamos un invoker auxiliar para comparar con los demás
        Invoker selectedInvoker = new Invoker();
        selectedInvoker.setAvailableRam(0);

        //Recorremos los invokers disponibles y nos quedamos con el que tenga más RAM disponible
        for (Invoker invoker : freeInvokers) {
            if (invoker.getAvailableRam() > selectedInvoker.getAvailableRam() && invoker.getNumAssignedFunctions() < functionsPerInvoker) {
                selectedInvoker = invoker;
            }
        }

        return selectedInvoker;
    }

}