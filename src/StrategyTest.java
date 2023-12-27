import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class StrategyTest {

    @Test
    public void GreadyGroupTest() {
        System.out.println("GREADY GROUP STRATEGY TEST");
        GreadyGroupStrategy greadyGroupStrategy = new GreadyGroupStrategy();

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        invoker1.setAvailableRam(0);
        invoker2.setAvailableRam(8);
        invoker3.setAvailableRam(10);
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3);

        FunctionWithRam function1 = new FunctionWithRam(x -> x.get("x") - x.get("y"), 3);
        FunctionWithRam function2 = new FunctionWithRam(x -> x.get("z"), 2);
        FunctionWithRam function3 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 1);
        FunctionWithRam function4 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 1);
        FunctionWithRam function5 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 1);
        FunctionWithRam function6 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 1);
        FunctionWithRam function7 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 1);
        FunctionWithRam function8 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 1);


        List<FunctionWithRam> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8);

        System.out.println("List of invokers "+invokers);
        System.out.println("List of functions "+functions);

        for (FunctionWithRam function: functions){
            Invoker selectedInvoker = greadyGroupStrategy.assignFunction(invokers, functions);
            selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - function.getRam());
            selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            selectedInvoker.addFunction(function);
        }
        for (Invoker invoker: invokers){
            System.out.println(invoker + ": " + invoker.getNumAssignedFunctions() + " functions " + "and available RAM: " + invoker.getAvailableRam());
            invoker.getAssignedFunctions().forEach(System.out::println);
        }
    }

    @Test
    public void testRoundRobinStrategy(){
        System.out.println("ROUND ROBIN STRATEGY TEST");

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3);

        RoundRobinStrategy roundRobinStrategy = new RoundRobinStrategy();

        FunctionWithRam function1 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 3);
        FunctionWithRam function2 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function3 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function4 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function5 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function6 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function7 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function8 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function9 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);

        List<FunctionWithRam> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8, function9);

        System.out.println("List of invokers "+invokers);
        System.out.println("List of functions: " + functions);

        invoker1.setAvailableRam(1);
        invoker2.setAvailableRam(6);
        invoker3.setAvailableRam(4);

        for (FunctionWithRam function : functions) {
            Invoker selectedInvoker = roundRobinStrategy.assignFunction(invokers, functions);
            if (selectedInvoker == null) {
                System.out.println("No invoker has enough available RAM for function: " + function);
            }
            else if (selectedInvoker.getAvailableRam() >= function.getRam()) {
                selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - function.getRam());
                selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
                selectedInvoker.addFunction(function);
            }
            else System.out.println(selectedInvoker+"does not have enough available RAM for function: " + function);
        }

        for (Invoker invoker : invokers) {
            System.out.println(invoker + ": " + invoker.getNumAssignedFunctions() + " functions");
            invoker.getAssignedFunctions().forEach(System.out::println);
        }
    }

    @Test
    public void testUniformGroupStrategy() {
        System.out.println("UNIFORM GROUP STRATEGY TEST");

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2 ,invoker3);

        UniformGroupStrategy uniformGroupStrategy = new UniformGroupStrategy(4);

        FunctionWithRam function1 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function2 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function3 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function4 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function5 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function6 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function7 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function8 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function9 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function10 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function11 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function12 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);

        List<FunctionWithRam> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8, function9, function10, function11, function12);

        System.out.println("List of invokers " + invokers);
        System.out.println("List of functions: " + functions);

        invoker1.setAvailableRam(10);
        invoker2.setAvailableRam(10);
        invoker3.setAvailableRam(10);

        for (FunctionWithRam function : functions) {
            Invoker selectedInvoker = uniformGroupStrategy.assignFunction(invokers, functions);
            if (selectedInvoker == null) {
                System.out.println("No invoker has enough available RAM for function: " + function);
            }
            else if (selectedInvoker.getAvailableRam() >= function.getRam()) {
                selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - function.getRam());
                selectedInvoker.addFunction(function);
                selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);
            }
        }

        for (Invoker invoker : invokers) {
            System.out.println(invoker + ": " + invoker.getNumAssignedFunctions() + " functions");
            invoker.getAssignedFunctions().forEach(System.out::println);
        }
    }

    @Test
    public void testBigGroupStrategy() {
        System.out.println("BIG GROUP STRATEGY TEST");

        Invoker invoker1 = new Invoker();
        Invoker invoker2 = new Invoker();
        Invoker invoker3 = new Invoker();
        List<Invoker> invokers = Arrays.asList(invoker1, invoker2, invoker3);

        BigGroupStrategy bigGroupStrategy = new BigGroupStrategy(4);

        FunctionWithRam function1 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function2 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function3 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function4 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function5 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function6 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function7 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function8 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function9 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function10 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function11 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);
        FunctionWithRam function12 = new FunctionWithRam(x -> x.get("x") + x.get("y") + x.get("z"), 2);

        List<FunctionWithRam> functions = Arrays.asList(function1, function2, function3, function4, function5, function6, function7, function8, function9, function10, function11, function12);

        System.out.println("List of invokers " + invokers);
        System.out.println("List of functions: " + functions);

        invoker1.setAvailableRam(20);
        invoker2.setAvailableRam(12);
        invoker3.setAvailableRam(12);

        for (FunctionWithRam function : functions) {
            Invoker selectedInvoker = bigGroupStrategy.assignFunction(invokers, functions);
            if (selectedInvoker == null) {
                System.out.println("No invoker has enough available RAM for function: " + function);
            } else if (selectedInvoker.getAvailableRam() >= function.getRam()) {
                selectedInvoker.setAvailableRam(selectedInvoker.getAvailableRam() - function.getRam());
                selectedInvoker.addFunction(function);
                selectedInvoker.setNumAssignedFunctions(selectedInvoker.getNumAssignedFunctions() + 1);

            }

        }

        for (Invoker invoker : invokers) {
            System.out.println(invoker + ": " + invoker.getNumAssignedFunctions() + " functions");
            invoker.getAssignedFunctions().forEach(System.out::println);
        }
    }
}
