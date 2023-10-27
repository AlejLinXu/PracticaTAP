import java.lang.reflect.Array;
import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Controller {
    private ArrayList<Invoker> listInvokers;
    private HashMap<String, Function> mapActions;

    public Controller() {
        listInvokers = new ArrayList<Invoker>();
        for(int i = 0;i<10;i++){
            this.listInvokers.add(i, new Invoker());
        }
        this.mapActions = new HashMap<>();
    }

     public void registerAction (String action, Function f) {
         this.mapActions.put(action, f);
     }

     public void invoke (String action, Object o){

     }


}
