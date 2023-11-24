import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public class Invoker implements InvokerInterface {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    private int ram = 1024;
    public Invoker() {

    }
    @Override
    public Object executeAction(Function<Object, Object> action, Object params) {
        return action.apply(params);
    }

    public Future<Object> executeActionAsync(Function<Object, Object> action, Object params) {
        return executorService.submit(() -> action.apply(params));
    }
    //getters y setters RAM
    public int getRam() {
        return ram;
    }
    public void setRam(int ram) {
        this.ram = ram;
    }
}
