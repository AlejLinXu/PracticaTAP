import java.util.List;

public class GreadyGroupStrategy implements IPolicyManager {
//Asigna la función al invoker con más RAM disponible.
    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<FunctionWithRam> functions) {
        //Filtramos los invokers que tienen RAM disponible
        List<Invoker> freeInvokers = invokers.stream().filter(invoker -> invoker.getAvailableRam() > 0).toList();

        //Si no hay invokers disponibles o no hay funciones, devolvemos null
        if (freeInvokers.isEmpty() || functions.isEmpty()) {
            return null;
        }

        //Creamos un invoker auxiliar para comparar con los demás
        Invoker invokerAux = new Invoker();
        invokerAux.setAvailableRam(0);

        //Recorremos los invokers disponibles y nos quedamos con el que tenga más RAM disponible
        for (Invoker invoker : freeInvokers) {
            if (invoker.getAvailableRam() > invokerAux.getAvailableRam()) {
                invokerAux = invoker;
            }
        }
        return invokerAux;

    }
}
