import java.util.List;
import java.util.function.Function;

public class BigGroupStrategy implements IPolicyManager{
    //coloca los grupos empaquetados si es posible.
    private int groupSize;
    public BigGroupStrategy(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<FunctionWithRam> functions) {
        Invoker selectedInvoker = new Invoker();
        selectedInvoker.setAvailableRam(0);
        for (int i = 0; i < functions.size(); i += groupSize) {
            int end = Math.min(i + groupSize, functions.size());
            List<FunctionWithRam> group = functions.subList(i, end);

            // Agregar el grupo al primer invocador disponible
            for (Invoker invoker : invokers) {
                if (invoker.getAvailableRam() >= group.stream().mapToInt(FunctionWithRam::getRam).sum()) {
                    invoker.addFunction(group.get(0));
                    selectedInvoker = invoker;
                }
            }

        }
        return selectedInvoker;

}
}