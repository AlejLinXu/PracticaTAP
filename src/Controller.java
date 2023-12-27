import javax.xml.transform.Result;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class Controller<T, V> {
    private final ArrayList<Invoker> listInvokers;
    private final HashMap<String, Function<T, V>> mapActions;
    private HashMap<String, Integer> mapRam;
    private IPolicyManager policy;

    public Controller (IPolicyManager policy) {
        listInvokers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            this.listInvokers.add(i, new Invoker());
        }
        this.mapActions = new HashMap<>();
        this.mapRam = new HashMap<String, Integer>(4, 4);
        this.policy = policy;
    }

    public Controller() {
        listInvokers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            this.listInvokers.add(i, new Invoker());
        }
        this.mapActions = new HashMap<>();
        this.mapRam = new HashMap<String, Integer>(4, 4);
    }

    public void registerAction(String action, Function<T, V> f, int ram) {
        this.mapActions.put(action, f);
        this.mapRam.put(action, ram);
    }

    public Object invoke(String actionName, Map<String, Integer> params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null){
            if(listInvokers.get(0).getAvailableRam() > mapRam.get(actionName)){
                listInvokers.get(0).setRam(mapRam.get(actionName));
            }
            else throw new IllegalArgumentException("Not enough RAM");

            return listInvokers.get(0).executeAction(action, params);
        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
    }

    public Future<Object> invokeAsync(String actionName, Map<String, Integer> params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null) {
            if (listInvokers.get(0).getAvailableRam() > mapRam.get(actionName)) {
                listInvokers.get(0).setRam(mapRam.get(actionName));
            } else throw new IllegalArgumentException("Not enough RAM");

        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
        return listInvokers.get(0).executeActionAsync(action, params);
    }


    public Future<Object> invokeAsyncInt (String actionName, int params) {
        Function<Object, Object> action = (Function<Object, Object>) mapActions.get(actionName);
        if (action != null){
            return listInvokers.get(0).executeActionAsync(action, params);
        }
        else throw new IllegalArgumentException("Action not registered: " + actionName);
    }


    public int getRam(String function) {
        return mapRam.get(function);
    }


    public List getActions() {
       //devuleve una lista con todas las acciones
        return new ArrayList(mapActions.keySet());
    }
}
