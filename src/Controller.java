import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Controller<T, V> {
    private ArrayList<Invoker> listInvokers;
    private HashMap<String, Function<T, V>> mapActions;

    public Controller() {
        listInvokers = new ArrayList<Invoker>();
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
            return listInvokers.get(0).executeAction(action, params);
    }

    //getter and setters
    public ArrayList<Invoker> getListInvokers() {
        return listInvokers;
    }

    public HashMap<String, Function<T, V>> getMapActions() {
        return mapActions;
    }
}
