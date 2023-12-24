import java.util.List;

public class UniformGroupStrategy implements IPolicyManager {
    //define un tamaño de grupo (6 por ejemplo) y que coloca de manera uniforme entre todos los Invokers
    private final int groupSize;

    public UniformGroupStrategy(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public Invoker assignFunction(List<Invoker> invokers, List<FunctionWithRam> functions) {
        List<Invoker> freeInvokers = invokers.stream()
                .filter(invoker -> invoker.getAvailableRam() > 0)
                .toList();

        if (functions.isEmpty() || freeInvokers.isEmpty() || groupSize <= 0) {
            return null;
        }

        Invoker selectedInvoker = new Invoker();
        selectedInvoker.setAvailableRam(0);

        for (Invoker invoker : freeInvokers) {
            if (invoker.getNumAssignedFunctions() < groupSize && invoker.getAvailableRam() > selectedInvoker.getAvailableRam()){
                break;
            }
        }
        return selectedInvoker;
    }
}

