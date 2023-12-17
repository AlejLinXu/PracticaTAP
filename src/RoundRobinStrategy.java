import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class RoundRobinStrategy implements IPolicyManager {

    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        // Ordenamos los invokers por su disponibilidad de recursos
        invokers.sort(Comparator.comparingInt(Invoker::getAvailableRam));
        Invoker invokerAux = null;
        // Iteramos sobre las funciones
        for (Function function : functions) {
            // Buscamos el primer invoker disponible
            Invoker invoker = invokers.get(0);
            if (invoker.getAvailableRam() >= 0 ) {
                invokerAux = invoker;
                // Eliminamos el invoker de la lista
                invokers.remove(0);
                // Asignamos la función al invoker



            }
        }
        return invokerAux;
    }

}