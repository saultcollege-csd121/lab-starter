package core;

import java.util.function.Function;

/**
 * Represents a single named unit conversion (e.g "km → miles")
 *
 * It belongs to the core package, so it has no knowledge of UI or IO.
 * It just holds a name and a formula, and knows how to apply it
 * @param name the display name shown in the dropdown (e.g. "km → miles")
 * @param formula the math function that takes the input and returns the result
 */
public record Conversion(String name, Function<Double, Double> formula) {

    /**
     * Applies the formula to the given input and returns the result.
     * @param input the number the user typed
     * @return the converted value
     */
    public double convert(double input) {
        return formula.apply(input);
    }
}