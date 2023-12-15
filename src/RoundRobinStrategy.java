import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class RoundRobinStrategy implements IPolicyManager {

    public void assignFunction(List<Invoker> invokers, List<Function> functions) {
        // Ordenamos los invokers por su disponibilidad de recursos
        invokers.sort(Comparator.comparingInt(Invoker::getAvailableRam));

    }

}