import java.util.List;
import java.util.function.Function;

public class GreadyGroupStrategy implements IPolicyManager {
//asigna la funcion al invoker con mas espacio disponible
    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        // Iteramos sobre las funciones
        for (Function function : functions) {
            // Buscamos el invoker con más espacio disponible
            Invoker invoker = invokers.get(0);
            if (invoker.getAvailableRam() >= 0/*invoker.getAvailableRam() >= function.getMemoryRequirement()*/) {
                // Asignamos la función al invoker
                return invoker;
                // Actualizamos la disponibilidad de memoria del invoker
                //invoker.setRam(invoker.getAvailableRam() - function.getMemoryRequirement());
            }
        }

}
