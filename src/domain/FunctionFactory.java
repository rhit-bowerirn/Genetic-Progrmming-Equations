package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;
// import java.util.function.Function;

public class FunctionFactory {

    private static final BiFunction<Double, Double, Double> SQRT = (l, r) -> Math.sqrt(Math.abs(r));
    private static final BiFunction<Double, Double, Double> ADD = (l, r) -> l + r;
    private static final BiFunction<Double, Double, Double> SUBTRACT = (l, r) -> l - r;
    private static final BiFunction<Double, Double, Double> MULTIPLY = (l, r) -> l * r;
    private static final BiFunction<Double, Double, Double> DIVIDE = (l, r) -> r == 0 ? 0 : l / r;

    private static final Map<BiFunction<Double, Double, Double>, String> BINARY_FUNCTIONS = Map.of(
        ADD, "+",
        SUBTRACT, "-",
        MULTIPLY, "*",
        DIVIDE, "/",
        SQRT, "\u221A"
    );

    public static BiFunction<Double, Double, Double> randomFunction(Random rand) {
        int randomIndex = rand.nextInt(BINARY_FUNCTIONS.size());
        List<BiFunction<Double, Double, Double>> functions = new ArrayList<BiFunction<Double, Double, Double>>(
                BINARY_FUNCTIONS.keySet());
        return functions.get(randomIndex);
    }

    public static String binaryFunctionName(BiFunction<Double, Double, Double> binaryFunction) {
        if (BINARY_FUNCTIONS.containsKey(binaryFunction)) {
            return BINARY_FUNCTIONS.get(binaryFunction);
        }
        return "INVALID";
    }

}
