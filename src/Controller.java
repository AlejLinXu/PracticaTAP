import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Controller {
    private List<Invoker> listInvokers;
    private HashMap<String, Function> mapActions;

    public Controller() {
        this.listInvokers = new ArrayList<>();
        for(int i = 0;i<10;i++){
        }
        this.mapActions = new HashMap<>();
    }

     public void registerAction (String action, Function f) {
         this.mapActions.add(action, f);
     }

     public void invoke (String action, Object o){

     }


}
