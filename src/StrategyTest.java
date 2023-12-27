import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StrategyTest {

    @Test
    public void GreadyGroupTest() {
        System.out.println("GREADY GROUP STRATEGY TEST");
        Controller controller = new Controller(new GreadyGroupStrategy());
        GreadyGroupStrategy greadyGroupStrategy = new GreadyGroupStrategy();

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        invoker1.setAvailableRam(2);
        invoker2.setAvailableRam(8);
        invoker3.setAvailableRam(10);
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3);

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

        System.out.println("List of invokers " + invokers);
        System.out.println("List of functions " + functions);
        List actions = controller.getActions();
        for (Function function : functions) {

            Invoker selectedInvoker = greadyGroupStrategy.assignFunction(invokers, functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam((String) actions.get(0)));
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(function);
        }
        for (Invoker invoker : invokers) {
            System.out.println(invoker + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
        }
    }

    @Test
    public void testRoundRobinStrategy(){
        System.out.println("ROUND ROBIN STRATEGY TEST");
        Controller controller = new Controller(new RoundRobinStrategy());
        RoundRobinStrategy roundRobinStrategy = new RoundRobinStrategy();

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        invoker1.setAvailableRam(8);
        invoker2.setAvailableRam(8);
        invoker3.setAvailableRam(10);
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3);

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

        System.out.println("List of invokers " + invokers);
        System.out.println("List of functions " + functions);
        List actions = controller.getActions();
        for (Function function : functions) {

            Invoker selectedInvoker = roundRobinStrategy.assignFunction(invokers, functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam((String) actions.get(0)));
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(function);
        }
        for (Invoker invoker : invokers) {
            System.out.println(invoker + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
        }
    }

    @Test
    public void testUniformGroupStrategy() {
        System.out.println("UNIFORM GROUP STRATEGY TEST");
        Controller controller = new Controller(new UniformGroupStrategy(4));
        UniformGroupStrategy uniformGroupStrategy = new UniformGroupStrategy(4);

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        invoker1.setAvailableRam(8);
        invoker2.setAvailableRam(8);
        invoker3.setAvailableRam(10);
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3);

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

        System.out.println("List of invokers " + invokers);
        System.out.println("List of functions " + functions);
        List actions = controller.getActions();
        for (Function function : functions) {

            Invoker selectedInvoker = uniformGroupStrategy.assignFunction(invokers, functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam((String) actions.get(0)));
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(function);
        }
        for (Invoker invoker : invokers) {
            System.out.println(invoker + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
        }
    }

    @Test
    public void testBigGroupStrategy() {
        System.out.println("BIG GROUP STRATEGY TEST");
        Controller controller = new Controller(new BigGroupStrategy(6));
        BigGroupStrategy bigGroupStrategy = new BigGroupStrategy(6);

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        invoker1.setAvailableRam(2);
        invoker2.setAvailableRam(8);
        invoker3.setAvailableRam(10);
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3);

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

        System.out.println("List of invokers " + invokers);
        System.out.println("List of functions " + functions);
        List actions = controller.getActions();
        for (Function function : functions) {

            Invoker selectedInvoker = bigGroupStrategy.assignFunction(invokers, functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam((String) actions.get(0)));
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(function);
        }
        for (Invoker invoker : invokers) {
            System.out.println(invoker + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
        }
    }
}

