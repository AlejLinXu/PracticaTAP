import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Function<Map<String, Integer>, Integer> f = x -> x.get("x") - x.get("y");
        controller.registerAction("addAction", f);
        int res = (int) controller.invoke("addAction", Map.of("x", 6, "y", 2));
        System.out.println(res);

        /*
        List<Map<String, Integer>> input = Arrays.asList(new Map[]{
                Map.of("x", 2, "y", 3),
                Map.of("x", 9, "y", 1),
                Map.of("x", 8, "y", 8),
        });
        List<Integer> result = (List<Integer>) controller.invoke("addAction",  input);
        System.out.println(result.toString());
        */
    }
}
