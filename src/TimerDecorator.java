import java.util.concurrent.Callable;

public class TimerDecorator extends Decorator {
    public TimerDecorator(Callable wrapee) {
        super(wrapee);
    }

    @Override
    public Object call() throws Exception {
        long startTime = 0;
        startTime = System.nanoTime();
        Object result = wrapee.call();
        long endTime = 0;
        endTime = System.nanoTime();
        System.out.println("Time elapsed: " + (endTime - startTime) + " nanoseconds");
        return result;
    }
}
