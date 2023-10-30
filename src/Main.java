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


    }
}
