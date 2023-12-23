import java.util.Map;
import java.util.function.Function;

public class FunctionWithRam {
    private int ram;
    private Function<Map<String, Integer>, Integer> function;

    public FunctionWithRam(Function<Map<String, Integer>, Integer> function,int ram) {
        this.function = function;
        this.ram = ram;
    }

    public int getRam() {
        return ram;
    }

    public Function getFunction() {
        return function;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setFunction(Function<Map<String, Integer>, Integer> function) {
        this.function = function;
    }
}
