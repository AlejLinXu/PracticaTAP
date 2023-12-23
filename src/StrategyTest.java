import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StrategyTest {

    @Test
    public void GreadyGroupTest() {
        System.out.println("GREADY GROUP STRATEGY TEST");
        GreadyGroupStrategy greadyGroupStrategy = new GreadyGroupStrategy();

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        Invoker invoker4 = new Invoker();
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3, invoker4);

        Function<Map<String, Integer>, Integer> function1 = x -> x.get("x") - x.get("y");
        Function<Map<String, Integer>, Integer> function2 = x -> x.get("z");
        Function<Map<String, Integer>, Integer> function3 = x -> x.get("x") + x.get("y") + x.get("z");
        List<Function> functions = Arrays.asList(function1, function2, function3);

        System.out.println("List of invokers "+invokers);

        invoker1.setAvailableRam(-5);
        invoker2.setAvailableRam(6);
        invoker3.setAvailableRam(10);
        invoker4.setAvailableRam(1);


        for (Invoker invoker: invokers){
            System.out.println("Invoker Selected: "+invoker);
            Invoker selectedInvoker = greadyGroupStrategy.assignFunction(invokers, functions);
            if (selectedInvoker != null) {
                assertEquals(invoker3, selectedInvoker, "Should select the invoker with the most available RAM");
            } else {
                assertNull(selectedInvoker, "Should return null when no invokers have positive available RAM");
            }
        }


    }

    @Test

    public void testRoundRobinStrategy(){
        System.out.println("ROUND ROBIN STRATEGY TEST");

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3);
        RoundRobinStrategy roundRobinStrategy = new RoundRobinStrategy(invokers);

        Function<Map<String, Integer>, Integer> function1 = x -> x.get("x") - x.get("y");
        Function<Map<String, Integer>, Integer> function2 = x -> x.get("z");
        Function<Map<String, Integer>, Integer> function3 = x -> x.get("x") + x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function4 = x -> x.get("x") + x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function5 = x -> x.get("x") + x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function6 = x -> x.get("x") + x.get("y") + x.get("z");

        List<Function> functions = Arrays.asList(function1, function2, function3,function4, function5, function6);
        invoker1.setAvailableRam(3);
        invoker2.setAvailableRam(6);
        invoker3.setAvailableRam(3);
        for (Invoker invoker: invokers){
            System.out.println("Invoker Selected: "+invoker);
            invoker.setAssignedFunctions(0);
            invoker = roundRobinStrategy.assignFunction(invokers, functions);

        }
        assertEquals(2, invoker1.getAssignedFunctions());
        assertEquals(2, invoker2.getAssignedFunctions());
        assertEquals(2, invoker3.getAssignedFunctions());
    }
}
