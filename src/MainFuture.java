import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Function;

public class MainFuture {
    /*public static void main(String[] args) throws Exception {
        Controller<Integer, String> controller = new Controller<>();
        Function<Integer, String> sleep = s -> {
            try {
                Thread.sleep(5000);
                return "Done!";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        controller.registerAction("sleepAction", sleep, 128);
        Future fut1 = controller.invokeAsyncInt("sleepAction", 5000);
        Future fut2 = controller.invokeAsyncInt("sleepAction", 5004);
        Future fut3 = controller.invokeAsyncInt("sleepAction", 1232313);
        System.out.println(fut1.get());
        System.out.println(fut2.get());
        System.out.println(fut3.get());
    }*/

}
