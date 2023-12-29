import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StrategyTest {

    @Test
    public void GreadyGroupTest() {
        System.out.println("GREEDY GROUP STRATEGY TEST");
        Controller controller = new Controller(7, 1000,new GreedyGroupStrategy());
        GreedyGroupStrategy greedyGroupStrategy = new GreedyGroupStrategy();

        Function<Map<String, Integer>, Integer> function1 = x -> x.get("x") + x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function2 = x -> x.get("x") - x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function3 = x -> x.get("x") * x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function4 = x -> x.get("x") / x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function5 = x -> x.get("x") % x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer> function6 = x -> x.get("x") + x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function7 = x -> x.get("x") - x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function8 = x -> x.get("x") * x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function9 = x -> x.get("x") / x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function10 = x -> x.get("x") % x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function11 = x -> x.get("x") + x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer> function12 = x -> x.get("x") % x.get("y") * x.get("z");


        List<Function> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8, function9, function10, function11, function12);

        controller.registerAction("addAction1", function1, 3);
        controller.registerAction("addAction2", function2, 2);
        controller.registerAction("addAction3", function3, 2);
        controller.registerAction("addAction4", function4, 2);
        controller.registerAction("addAction5", function5, 2);
        controller.registerAction("addAction6", function6, 2);
        controller.registerAction("addAction7", function7, 2);
        controller.registerAction("addAction8", function8, 2);
        controller.registerAction("addAction9", function9, 2);
        controller.registerAction("addAction10", function10, 2);
        controller.registerAction("addAction11", function11, 2);
        controller.registerAction("addAction12", function12, 2);

        System.out.println("List of functions " + functions);

        List<Invoker> ListInvokers = controller.getListInvokers();

        List actions = controller.getActions();
        for (Function function : functions) {

            Invoker selectedInvoker = greedyGroupStrategy.assignFunction(controller.getListInvokers(), functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam((String) actions.get(0)));
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(function);
        }
        int i=1;
        for (Invoker invoker : ListInvokers) {
            System.out.println("Invoker "+i + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
            i++;
        }
    }

    @Test
    public void testRoundRobinStrategy(){
        System.out.println("ROUND ROBIN STRATEGY TEST");
        Controller controller = new Controller(8, 1024, new RoundRobinStrategy());
        RoundRobinStrategy roundRobinStrategy = new RoundRobinStrategy();

        Function<Map<String, Integer>, Integer> function1 = x -> x.get("x") + x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function2 = x -> x.get("x") - x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function3 = x -> x.get("x") * x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function4 = x -> x.get("x") / x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function5 = x -> x.get("x") % x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer> function6 = x -> x.get("x") + x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function7 = x -> x.get("x") - x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function8 = x -> x.get("x") * x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function9 = x -> x.get("x") / x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function10 = x -> x.get("x") % x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function11 = x -> x.get("x") + x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer> function12 = x -> x.get("x") % x.get("y") * x.get("z");


        List<Function> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8, function9, function10, function11, function12);

        List<Invoker> ListInvokers = controller.getListInvokers();

        controller.registerAction("addAction1", function1, 3);
        controller.registerAction("addAction2", function2, 2);


        System.out.println("List of invokers " + ListInvokers);
        System.out.println("List of functions " + functions);
        List actions = controller.getActions();
        for (Function function : functions) {

            Invoker selectedInvoker = roundRobinStrategy.assignFunction(ListInvokers, functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam((String) actions.get(0)));
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(function);
        }
        int i=1;
        for (Invoker invoker : ListInvokers) {
            System.out.println("Invoker "+i + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
            i++;
        }
    }

    @Test
    public void testUniformGroupStrategy() {
        System.out.println("UNIFORM GROUP STRATEGY TEST");
        UniformGroupStrategy uniformGroupStrategy = new UniformGroupStrategy(2);
        Controller controller = new Controller(12, 1024,uniformGroupStrategy);


        Function<Map<String, Integer>, Integer> function1 = x -> x.get("x") + x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function2 = x -> x.get("x") - x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function3 = x -> x.get("x") * x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function4 = x -> x.get("x") / x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function5 = x -> x.get("x") % x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer> function6 = x -> x.get("x") + x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function7 = x -> x.get("x") - x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function8 = x -> x.get("x") * x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function9 = x -> x.get("x") / x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function10 = x -> x.get("x") % x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function11 = x -> x.get("x") + x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer> function12 = x -> x.get("x") % x.get("y") * x.get("z");


        List<Function> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8, function9, function10, function11, function12);

        controller.registerAction("addAction1", function1, 3);
        controller.registerAction("addAction2", function2, 2);
        controller.registerAction("addAction3", function3, 2);
        controller.registerAction("addAction4", function4, 2);
        controller.registerAction("addAction5", function5, 2);
        controller.registerAction("addAction6", function6, 2);
        controller.registerAction("addAction7", function7, 2);
        controller.registerAction("addAction8", function8, 2);
        controller.registerAction("addAction9", function9, 2);
        controller.registerAction("addAction10", function10, 2);
        controller.registerAction("addAction11", function11, 2);
        controller.registerAction("addAction12", function12, 2);

        List<Invoker> ListInvokers = controller.getListInvokers();

        System.out.println("List of invokers " + ListInvokers);
        System.out.println("List of functions " + functions);
        List actions = controller.getActions();
        for (Function function : functions) {
            Invoker selectedInvoker = uniformGroupStrategy.assignFunction(ListInvokers, functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam((String) actions.get(0)));
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(function);
        }
        int i=1;
        for (Invoker invoker : ListInvokers) {
            System.out.println("Invoker "+i + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
            i++;
        }
    }

    @Test
    public void testBigGroupStrategy() {
        System.out.println("BIG GROUP STRATEGY TEST");
        Controller controller = new Controller(7, 1024,new BigGroupStrategy(6));
        BigGroupStrategy bigGroupStrategy = new BigGroupStrategy(6);

        Function<Map<String, Integer>, Integer> function1 = x -> x.get("x") + x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function2 = x -> x.get("x") - x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function3 = x -> x.get("x") * x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function4 = x -> x.get("x") / x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function5 = x -> x.get("x") % x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer> function6 = x -> x.get("x") + x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function7 = x -> x.get("x") - x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function8 = x -> x.get("x") * x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function9 = x -> x.get("x") / x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function10 = x -> x.get("x") % x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function11 = x -> x.get("x") + x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer> function12 = x -> x.get("x") % x.get("y") * x.get("z");


        List<Function> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8, function9, function10, function11, function12);

        controller.registerAction("addAction1", function1, 3);
        controller.registerAction("addAction2", function2, 2);
        controller.registerAction("addAction3", function3, 2);
        controller.registerAction("addAction4", function4, 2);
        controller.registerAction("addAction5", function5, 2);
        controller.registerAction("addAction6", function6, 2);
        controller.registerAction("addAction7", function7, 2);
        controller.registerAction("addAction8", function8, 2);
        controller.registerAction("addAction9", function9, 2);
        controller.registerAction("addAction10", function10, 2);
        controller.registerAction("addAction11", function11, 2);
        controller.registerAction("addAction12", function12, 2);

        List<Invoker> ListInvokers = controller.getListInvokers();

        System.out.println("List of invokers " + ListInvokers);
        System.out.println("List of functions " + functions);
        List actions = controller.getActions();
        for (Function function : functions) {

            Invoker selectedInvoker = bigGroupStrategy.assignFunction(ListInvokers, functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam((String) actions.get(0)));
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(function);
        }
        int i=1;
        for (Invoker invoker : ListInvokers) {
            System.out.println("Invoker "+i + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
            i++;
        }
    }
}

