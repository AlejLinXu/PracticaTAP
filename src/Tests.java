import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Tests {

    @Test
    public void testGreadyGroupStrategy() {
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

        System.out.println("TEST FOR INVOKER WITH AVAILABLE RAM");
        invoker1.setAvailableRam(10);
        invoker2.setAvailableRam(6);
        invoker3.setAvailableRam(0);
        invoker4.setAvailableRam(1);
        List<Invoker> freeInvokers = invokers.stream().filter(invoker -> invoker.getAvailableRam() > 0).toList();
        System.out.println("Invokers with available RAM: "+freeInvokers);
        Invoker selectedInvoker = greadyGroupStrategy.assignFunction(invokers, functions);
        System.out.println("Invoker Selected: "+selectedInvoker);
        assertEquals(invoker1, selectedInvoker, "Should select invoker with the most available RAM");

        System.out.println("TEST FOR INVOKER WITH NO AVAILABLE RAM");
        invoker1.setAvailableRam(-5);
        invoker2.setAvailableRam(-3);
        invoker3.setAvailableRam(-1);
        invoker4.setAvailableRam(0);
        List<Invoker> NofreeInvokers = invokers.stream().filter(invoker -> invoker.getAvailableRam() > 0).toList();
        System.out.println("Invokers with available RAM: "+NofreeInvokers);
        assertNull(greadyGroupStrategy.assignFunction(invokers, functions), "Should return null when no invokers have positive available RAM");
    }

    @Test
    public void testRoundRobinStrategy(){
        RoundRobinStrategy roundRobinStrategy = new RoundRobinStrategy();

        // Create sample invokers and functions
        Invoker invoker1 = new Invoker();  // consider customizing Invoker constructor if needed
        Invoker invoker2 = new Invoker();
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2);

        // Set available RAM for the invokers
        invoker1.setAvailableRam(10);
        invoker2.setAvailableRam(5);

        // Test case 1: First invoker should be selected initially (lowest available RAM)
        Invoker selectedInvoker = roundRobinStrategy.assignFunction(invokers, null);
        assertEquals(invoker2, selectedInvoker, "Invoker with lowest available RAM should be selected first");

        // Test case 2: After the first selection, the second invoker should be selected (higher available RAM)
        selectedInvoker = roundRobinStrategy.assignFunction(invokers, null);
        assertEquals(invoker1, selectedInvoker, "Invoker with higher available RAM should be selected next");

        // Test case 3: After both invokers are selected, it should go back to the first invoker
        selectedInvoker = roundRobinStrategy.assignFunction(invokers, null);
        assertEquals(invoker2, selectedInvoker, "Should cycle back to the first invoker with lowest available RAM");

        // Add more test cases based on your specific requirements and scenarios
    }

    @Test
    public void testUniformGroupStrategy(){
        UniformGroupStrategy uniformGroupStrategy = new UniformGroupStrategy(6);

        // Create sample invokers and functions
        Invoker invoker1 = new Invoker();  // consider customizing Invoker constructor if needed
        Invoker invoker2 = new Invoker();
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2);

        // Test case 1: Both invokers have positive available RAM
        invoker1.setAvailableRam(10);
        invoker2.setAvailableRam(5);
        Invoker selectedInvoker = uniformGroupStrategy.assignFunction(invokers, null);
        assertEquals(invoker1, selectedInvoker, "Should select invoker with the most available RAM");

        // Test case 2: No invokers with positive available RAM
        invoker1.setAvailableRam(-5);
        invoker2.setAvailableRam(-3);
        selectedInvoker = uniformGroupStrategy.assignFunction(invokers, null);
        assertNull(selectedInvoker, "Should return null when no invokers have positive available RAM");

    }
}
