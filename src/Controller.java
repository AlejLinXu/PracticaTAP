import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Controller<T, V> {
    private final ArrayList<Invoker> listInvokers;
    private final HashMap<String, Function<T, V>> mapActions;

    public Controller() {
        listInvokers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            this.listInvokers.add(i, new Invoker());
        }
        this.mapActions = new HashMap<>();
    }

    public void registerAction(String action, Function<T, V> f) {
        this.mapActions.put(action, f);
    }

    public Object invoke(String actionName, Map<String, Integer> params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null){
            return listInvokers.get(0).executeAction(action, params);
        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
    }
}
