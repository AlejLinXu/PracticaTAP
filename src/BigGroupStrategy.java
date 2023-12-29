import java.util.List;
import java.util.function.Function;

public class BigGroupStrategy implements IPolicyManager{
    //coloca los grupos empaquetados si es posible.
    private int groupSize;
    public BigGroupStrategy(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<Function> functions) {
        Invoker selectedInvoker = new Invoker(invokers.get(0).getAvailableRam());
        selectedInvoker.setAvailableRam(0);
        for (int i = 0; i < functions.size(); i += groupSize) {
            int end = Math.min(i + groupSize, functions.size());
            List<Function> group = functions.subList(i, end);

            // Agregar el grupo al primer invocador disponible
            for (Invoker invoker : invokers) {
                if (invoker.getAvailableRam() >= 0) {
                    invoker.addFunction(group.get(0));
                    selectedInvoker = invoker;
                    break;
                }
            }

        }
        return selectedInvoker;

    }
}