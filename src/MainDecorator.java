import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class MainDecorator {
    public static void main(String[] args) {
        RoundRobinStrategy roundRobinStrategy = new RoundRobinStrategy();
        Controller<Map<String, Integer>, Integer> controller = new Controller<>(1,20, roundRobinStrategy);
        controller.getListInvokers().forEach(invoker -> invoker.setTimerOn(true)); //turn on timer
        controller.getListInvokers().forEach(invoker -> invoker.setCacheEnabled(true)); //turn on cache

        /*Callable factorial = (Callable <Integer>) (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        };

        controller.registerAction("factorial", factorial, 5);
        boolean cacheEnabled = controller.getListInvokers().get(0).isCacheEnabled();
        System.out.println("Cache enabled: " + cacheEnabled);
        boolean timerOn = controller.getListInvokers().get(0).isTimerOn();
        System.out.println("Timer on: " + timerOn);
        if(cacheEnabled) {
            factorial = new MemoizationDecorator(factorial);
        }
*/

    }
}
