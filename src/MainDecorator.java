import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class MainDecorator {
    public static void main(String[] args) throws Exception {
        RoundRobinStrategy roundRobinStrategy = new RoundRobinStrategy();
        Controller<Map<String, Integer>, Integer> controller = new Controller<>(1,20, roundRobinStrategy);


        Function<Integer, Integer> factorialFunction = n -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        };

        Callable<Integer> factorial1 = () -> factorialFunction.apply(1);
        Callable<Integer> factorial2 = () -> factorialFunction.apply(2);
        Callable<Integer> factorial3 = () -> factorialFunction.apply(3);
        Callable<Integer> factorial4 = () -> factorialFunction.apply(4);
        Callable<Integer> factorial5 = () -> factorialFunction.apply(5);

        List<Callable<Integer>> functions = List.of(factorial1, factorial2, factorial3, factorial4, factorial5);

        controller.registerFactorialAction("factorial", factorial1, 5);
        controller.registerFactorialAction("factorial", factorial2, 5);
        controller.registerFactorialAction("factorial", factorial3, 5);
        controller.registerFactorialAction("factorial", factorial4, 5);
        controller.registerFactorialAction("factorial", factorial5, 5);

        boolean cacheEnabled = controller.getListInvokers().get(0).isCacheEnabled();
        System.out.println("Cache enabled: " + cacheEnabled);
        boolean timerOn = controller.getListInvokers().get(0).isTimerOn();
        System.out.println("Timer on: " + timerOn);

        controller.getListInvokers().forEach(invoker -> invoker.setTimerOn(false)); //turn on timer
        controller.getListInvokers().forEach(invoker -> invoker.setCacheEnabled(true)); //turn on cache

            System.out.println("---------CACHE ENABLED---------");
            factorial1 = new MemoizationDecorator(factorial1);
            factorial2 = new MemoizationDecorator(factorial2);
            factorial3 = new MemoizationDecorator(factorial3);
            factorial4 = new MemoizationDecorator(factorial4);
            factorial5 = new MemoizationDecorator(factorial5);

            System.out.println("Factorial 1: " + factorial1.call());
            System.out.println("Factorial 2: " + factorial2.call());
            System.out.println("Factorial 3: " + factorial3.call());
            System.out.println("Factorial 4: " + factorial4.call());
            System.out.println("Factorial 5: " + factorial5.call());


        controller.getListInvokers().forEach(invoker -> invoker.setTimerOn(true)); //turn on timer
        controller.getListInvokers().forEach(invoker -> invoker.setCacheEnabled(false)); //turn on cache

            System.out.println("---------TIMER ON---------");
            factorial1 = new TimerDecorator(factorial1);
            factorial2 = new TimerDecorator(factorial2);
            factorial3 = new TimerDecorator(factorial3);
            factorial4 = new TimerDecorator(factorial4);
            factorial5 = new TimerDecorator(factorial5);

            System.out.println("Factorial 1: " + factorial1.call());
            System.out.println("Factorial 2: " + factorial2.call());
            System.out.println("Factorial 3: " + factorial3.call());
            System.out.println("Factorial 4: " + factorial4.call());
            System.out.println("Factorial 5: " + factorial5.call());

        controller.getListInvokers().forEach(invoker -> invoker.setTimerOn(true)); //turn on timer
        controller.getListInvokers().forEach(invoker -> invoker.setCacheEnabled(true)); //turn on cache

            System.out.println("---------TIMER ON AND CACHE ENABLED---------");
            factorial1 = new MemoizationDecorator(factorial1);
            System.out.println("Factorial 1: " + factorial1.call());
            factorial1 = new TimerDecorator(factorial1);
            System.out.println("Factorial 1: " + factorial1.call());

    }
}