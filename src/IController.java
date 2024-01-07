import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;


public interface IController<T, V> {
    //interface to be implemented by the Controller class
    void registerAction(String action, Function<T, V> f, int ram);
    void registerFactorialAction(String action, Callable<Integer> factorialAction, int ram);

    List<Invoker> getListInvokers();
    Object invoke(String actionName, Map<String, Integer> params);
    V invokeCallable(String actionName);


}