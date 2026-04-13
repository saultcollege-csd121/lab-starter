package core;

import java.util.function.Function;

/**
 * Represents a single named unit conversion (e.g "km → miles")
 *
 * <p>This is a Java {@code record} an immutable data container.
 * It automatically generates a constructor, getters, equals, hashCode,
 * and toString.</p>
 */
public record Conversion(String name, Function<Double, Double> formula) {

    /**
     * Applies this conversion's formula to the given input value
     * TODO
     */
    public double convert(double input) {
        return formula.apply(input);
    }
}