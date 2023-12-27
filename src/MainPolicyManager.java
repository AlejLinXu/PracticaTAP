import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class MainPolicyManager {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Controller<Map<String, Integer>, Integer> controller = new Controller<>(new RoundRobinStrategy()); //asignamos politica al controller
        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3);

        RoundRobinStrategy roundRobinStrategy = new RoundRobinStrategy();

        Function<Map<String, Integer>, Integer>  function1 = x -> x.get("x") + x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer>   function2 = x -> x.get("x") - x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer>   function3 = x -> x.get("x") * x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer>   function4 = x -> x.get("x") / x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer>   function5 = x -> x.get("x") % x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer>   function6 = x -> x.get("x") + x.get("y") - x.get("z");
        Function<Map<String, Integer>, Integer>   function7 = x -> x.get("x") - x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer>   function8 = x -> x.get("x") * x.get("y") / x.get("z");
        Function<Map<String, Integer>, Integer>   function9 = x -> x.get("x") / x.get("y") * x.get("z");
        Function<Map<String, Integer>, Integer>   function10 = x -> x.get("x") % x.get("y") + x.get("z");
        Function<Map<String, Integer>, Integer>   function11 = x -> x.get("x") + x.get("y") % x.get("z");
        Function<Map<String, Integer>, Integer>   function12 = x -> x.get("x") % x.get("y") * x.get("z");


        List<Function> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8, function9, function10, function11, function12);

        System.out.println("List of invokers "+invokers);
        System.out.println("List of functions: " + functions);

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
        invoker1.setAvailableRam(1);
        invoker2.setAvailableRam(6);
        invoker3.setAvailableRam(4);

        for (Function function : functions) {
            Invoker selectedInvoker = roundRobinStrategy.assignFunction(invokers, functions);
            if (selectedInvoker == null) {
                System.out.println("No invoker has enough available RAM for function: " + function);
            }
            else if (selectedInvoker.getAvailableRam() >= controller.getRam(function)) {
                selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - controller.getRam(function));
                selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
                selectedInvoker = (Invoker) controller.invoke(controller.getAction(function), Map.of("x", 6, "y", 2, "z", 3));
                System.out.println("Invoker " + selectedInvoker + " has been assigned function: " + function);
            }
            else System.out.println(selectedInvoker+"does not have enough available RAM for function: " + function);
        }

        for (Invoker invoker : invokers) {
            System.out.println(invoker + ": " + invoker.getNumAssignedFunctions() + " functions");
            invoker.getAssignedFunctions().forEach(System.out::println);
        }
    }
}
