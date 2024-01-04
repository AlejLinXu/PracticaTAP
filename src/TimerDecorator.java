import java.util.concurrent.Callable;

public class TimerDecorator extends Decorator {
    public TimerDecorator(Callable wrapee) {
        super(wrapee);
    }

    @Override
    public Object call() throws Exception {
        long startTime = System.currentTimeMillis();
        Object result = wrapee.call();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + ((endTime - startTime)/1000) + "seconds.");
        return result;
    }
}
