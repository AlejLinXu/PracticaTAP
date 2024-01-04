import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*Controller<Map<String, Integer>, Integer> controller = new Controller<>();
        Function<Map<String, Integer>, Integer> f = x -> x.get("x") - x.get("y");
        controller.registerAction("addAction", f, 3);
        int res = (int) controller.invoke("addAction", Map.of("x", 6, "y", 2));
        System.out.println(res);



        List<Map<String, Integer>> input = Arrays.asList(
                Map.of("x", 2, "y", 3),
                Map.of("x", 9, "y", 1),
                Map.of("x", 8, "y", 8)
        );
        input.forEach(inputs -> {
            int itemList = (int) controller.invoke("addAction", inputs);
            System.out.println(itemList);
        });

        Future future = controller.invokeAsync("addAction", Map.of("x", 6, "y", 2));
        System.out.println(future.get());*/
    }
}