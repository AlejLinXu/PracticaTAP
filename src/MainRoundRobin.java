import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MainRoundRobin {
    public static void main(String[] args) {
        System.out.println("ROUND ROBIN STRATEGY TEST");
        RoundRobinStrategy roundRobinStrategy = new RoundRobinStrategy();
        Controller<Map<String, Integer>, Integer> controller = new Controller<>(4,1024,roundRobinStrategy);


        Function<Map<String, Integer>, Integer> function1 = x -> x.get("x");
        Function<Map<String, Integer>, Integer> function2 = x -> x.get("x") - x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function3 = x -> x.get("x") * x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function4 = x -> x.get("x") / x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function5 = x -> x.get("x") * (x.get("y") + x.get("z")) - x.get("z")/x.get("y") + x.get("x") - x.get("z");
        Function<Map<String, Integer>, Integer> function6 = x -> x.get("x") + x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer> function7 = x -> x.get("x") - x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function8 = x -> x.get("x") * x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer> function9 = x -> x.get("x") / x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer> function10 = x -> x.get("x") % x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer> function11 = x -> x.get("x") + x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer> function12 = x -> x.get("x") % x.get("y") * x.get("z");

        List<Function<Map<String, Integer>, Integer>> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8, function9, function10, function11, function12);

        controller.registerAction("addAction1", function1, 300);
        controller.registerAction("addAction2", function2, 20);
        controller.registerAction("addAction3", function3, 25);
        controller.registerAction("addAction4", function4, 2);
        controller.registerAction("addAction5", function5, 265);
        controller.registerAction("addAction6", function6, 23);
        controller.registerAction("addAction7", function7, 2);
        controller.registerAction("addAction8", function8, 2);
        controller.registerAction("addAction9", function9, 2);
        controller.registerAction("addAction10", function10, 2);
        controller.registerAction("addAction11", function11, 2);
        controller.registerAction("addAction12", function12, 2);

        List<Invoker> invokers = controller.getListInvokers();

        System.out.println("List of invokers " + invokers);
        System.out.println("List of functions " + functions);
        List<String> actions = controller.getActions();
        System.out.println("List of actions " + actions);
        for(String action : actions) {
            System.out.print(action+ " = ");
            Invoker selectedInvoker = roundRobinStrategy.assignFunction(invokers, functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam(action));
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(functions.get(actions.indexOf(action)));
            int itemList = (int) controller.invoke(action, Map.of("x", 500000, "y", 656565656, "z", 21643064));
            System.out.println(itemList);
        }
        int i=1;
        for (Invoker invoker : invokers) {
            System.out.println("Invoker"+i + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
            i++;
        }

        List<Metric> metrics = controller.getMetrics();
        for (Metric metric : metrics) {
            System.out.println("Action: " + metric.getAction() + ", Execution Time: " + metric.getExecutionTime() + " ms, Used Memory: " + metric.getUsedMemory() + " MB");
        }
        System.out.println("Average Execution Time: " + controller.getAverageExecutionTime() + " ms, Max Execution Time: " + controller.getMaxExecutionTime() + " s, Min Execution Time: " + controller.getMinExecutionTime() + " s, Total Used Memory: " + controller.getTotalUsedMemory() + " MB");

    }
}
