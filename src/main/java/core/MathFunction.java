package core;

import javafx.geometry.Point2D;

/**
 * MathFunction - Model class representing a mathematical function
 *
 * This class stores the function's metadata (name, type, parameters)
 * and calculates the points for one complete period.
 *
 * Responsibilities:
 * - Store function parameters
 * - Calculate Point2D array for the function over a given domain
 * - Provide function evaluation logic
 */
public class MathFunction {

    private final String name;
    private final String type;  // "complex_wave", "parabola", "exponential"
    private double amplitude;
    private double parameterA;  // Used differently per function type
    private double parameterC;  // Used by complex wave

    // Domain for x values
    private final double xMin = -10.0;
    private final double xMax = 10.0;

    /**
     * Constructor
     */
    public MathFunction(String name, String type, double amplitude, double parameterA, double parameterC) {
        this.name = name;
        this.type = type;
        this.amplitude = amplitude;
        this.parameterA = parameterA;
        this.parameterC = parameterC;
    }

    /**
     * Calculates points for one complete period of the function
     *
     * @param numPoints Number of points to calculate (ensures even number as per requirements)
     * @return Array of Point2D representing the function
     */
    public Point2D[] calculatePoints(int numPoints) {
        // Ensure even number of points
        if (numPoints % 2 != 0) {
            numPoints++;
        }

        Point2D[] points = new Point2D[numPoints];
        double dx = (xMax - xMin) / (numPoints - 1);

        for (int i = 0; i < numPoints; i++) {
            double x = xMin + i * dx;
            double y = evaluateFunction(x);
            points[i] = new Point2D(x, y);
        }

        return points;
    }

    /**
     * Evaluates the function at a given x value
     * This is where the hardcoded formulas live!
     */
    private double evaluateFunction(double x) {
        switch (type) {
            case "complex_wave":
                // f(x) = x^(2/3) + 0.9(c - x^2)^(1/2) * sin(a*π*x)
                // From the uploaded image
                double term1 = Math.pow(Math.abs(x), 2.0/3.0);
                double term2Inner = parameterC - x * x;
                double term2 = 0.0;
                if (term2Inner >= 0) {
                    term2 = 0.9 * Math.sqrt(term2Inner) * Math.sin(parameterA * Math.PI * x);
                }
                return amplitude * (term1 + term2);

            case "parabola":
                // f(x) = a * x^2
                return amplitude * parameterA * x * x;

            case "exponential":
                // f(x) = a * e^x
                return amplitude * parameterA * Math.exp(x / 5.0); // Scaled for visibility

            default:
                return 0.0;
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getParameterA() {
        return parameterA;
    }

    public void setParameterA(double parameterA) {
        this.parameterA = parameterA;
    }

    public double getParameterC() {
        return parameterC;
    }

    public void setParameterC(double parameterC) {
        this.parameterC = parameterC;
    }

    public double getXMin() {
        return xMin;
    }

    public double getXMax() {
        return xMax;
    }
}
