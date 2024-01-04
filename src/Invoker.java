import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public class Invoker implements InvokerInterface {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int ram = 1024;
    private int availableRam;
    private int numAssignedFunctions = 0;
    private List<Function> assignedFunctions = new ArrayList<>();

    private boolean cacheEnabled = false; //cache for decorator
    private boolean timerOn = false; //timer for decorator
    public Invoker(int availableRam) {
        this.availableRam = availableRam;
    }
    @Override
    public Object executeAction(Function<Object, Object> action, Object params) {
        return action.apply(params);
    }

    public Future<Object> executeActionAsync(Function<Object, Object> action, Object params) {
        return executorService.submit(() -> action.apply(params));
    }


    //getters y setters RAM


    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public boolean isTimerOn() {
        return timerOn;
    }

    public void setTimerOn(boolean timerOn) {
        this.timerOn = timerOn;
    }
    public int getRam() {
        return ram;
    }

    public int getAvailableRam() {
        return availableRam;
    }
    public void setAvailableRam(int availableRam) {
        this.availableRam = availableRam;
    }

    public void setRam(int ram) {
        this.availableRam = availableRam - ram;
    }

    public int getNumAssignedFunctions() {
        return numAssignedFunctions;
    }

    public void setNumAssignedFunctions(int numAssignedFunctions) {
        this.numAssignedFunctions = numAssignedFunctions;
    }

    public List<Function> getAssignedFunctions() {
        return assignedFunctions;
    }

    public void addFunction(Function function) {
        assignedFunctions.add(function);
    }

}
