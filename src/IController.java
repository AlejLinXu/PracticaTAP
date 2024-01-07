import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;


public interface IController<T, V> {
    //interface to be implemented by the Controller class
    public void registerAction(String action, Function<T, V> f, int ram);
    public void registerFactorialAction(String action, Callable<Integer> factorialAction, int ram);

    public List<Invoker> getListInvokers();
    public Object invoke(String actionName, Map<String, Integer> params);
    public V invokeCallable(String actionName);


}