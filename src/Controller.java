import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Controller {
    private ArrayList<Invoker> listInvokers;
    private HashMap<String, Function> mapActions;

    public Controller() {
        listInvokers = new ArrayList<Invoker>();
        for (int i = 0; i < 10; i++) {
            this.listInvokers.add(i, new Invoker());
        }
        this.mapActions = new HashMap<>();
    }

    public void registerAction(String action, Function<Map<String, Integer>, Integer> f) {
        this.mapActions.put(action, f);
    }

    public Object invoke(String actionName, Map<String, Integer> params) {
        Function<Map<String, Integer>, Integer> action = mapActions.get(actionName);
        if (action != null) {
            return action.apply(params);
        } else {
            throw new IllegalArgumentException("Action not found: " + actionName);
        }


    }
}
