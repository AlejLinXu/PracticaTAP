import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BigGroupStrategy implements IPolicyManager{
    //coloca los grupos empaquetados si es posible.
    private final int groupSize;
    public BigGroupStrategy(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function<Map<String, Integer>, Integer>> functions) {
        //Si no hay invokers disponibles o no hay funciones, devolvemos null
        if (invokers.isEmpty() || functions.isEmpty()) {
            return null;
        }
        Invoker selectedInvoker = new Invoker(0);
        for (Invoker invoker : invokers) {
               //si la ram disponible del invoker es mayor que el tamaño del grupo, lo seleccionamos sino pasamos al siguiente
               if (invoker.getAvailableRam() > groupSize) {
                    selectedInvoker = invoker;
                    break;
                }

        }
        return selectedInvoker;

    }
}