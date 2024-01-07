import java.util.Map;
import java.util.function.Function;

public class MainActionProxy {
    public static void main(String[] args) {
        // TODO code application logic here
        Controller<Map<String, Integer>, Integer> controller = (Controller<Map<String, Integer>, Integer>) ActionProxy.newProxyInstance(new Controller<>(4,1024,new RoundRobinStrategy()));
        Function<Map<String, Integer>, Integer> function = x -> x.get("x") - x.get("y") - x.get("z");
        controller.registerAction("Action", function, 20);
        int result = (int)controller.invoke("Action", Map.of("x", 10, "y", 5, "z", 3));
        System.out.println(result);

    }

}
