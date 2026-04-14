package controllers;

import ui.GraphView;
import core.MathFunction;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;

import java.util.Arrays;

/**
 * AnimationController - Controller class managing the animation logic
 *
 * This is the heart of the application's logic. It coordinates between
 * the Model (MathFunction) and the View (GraphView).
 *
 * Responsibilities:
 * - Manage the AnimationTimer (the app's "heartbeat")
 * - Maintain the three indices (currentIndex, firstIdx, secondIdx)
 * - Calculate opacity levels for each point
 * - Tell the View what to draw each frame
 * - Handle parameter updates from UI controls
 */
public class AnimationController {

    private final GraphView view;
    private MathFunction currentFunction;
    private final AnimationTimer timer;

    // The full period of points (calculated once)
    private Point2D[] fullPeriodPoints;

    // The three indices for animation (your design!)
    private int currentIndex;  // Newest point (100% opacity)
    private int firstIdx;      // Second opacity level (66.66%)
    private int secondIdx;     // Third opacity level (33.33%)

    // Flags to track when to spawn the trailing indices
    private boolean firstIdxSpawned;
    private boolean secondIdxSpawned;

    // Animation speed (frames per second adjustment)
    private long lastUpdateTime;
    private static final long FRAME_DELAY_NS = 16_000_000; // ~60 FPS

    // Opacity array to store current opacity for each point
    private double[] opacityArray;

    /**
     * Constructor
     */
    public AnimationController(GraphView view) {
        this.view = view;
        this.currentIndex = 0;
        this.firstIdx = 0;
        this.secondIdx = 0;
        this.firstIdxSpawned = false;
        this.secondIdxSpawned = false;

        // Create the AnimationTimer
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Throttle to ~60 FPS
                if (now - lastUpdateTime < FRAME_DELAY_NS) {
                    return;
                }
                lastUpdateTime = now;

                updateAnimation();
            }
        };
    }

    /**
     * Sets the current function and recalculates points
     */
    public void setFunction(MathFunction function) {
        this.currentFunction = function;

        // Calculate points for one complete period
        // Using 600 points for smooth animation (even number)
        fullPeriodPoints = function.calculatePoints(600);

        // Initialize opacity array
        opacityArray = new double[fullPeriodPoints.length];
        // Start invisible
        Arrays.fill(opacityArray, 0.0);

        // Reset indices
        currentIndex = 0;
        firstIdx = 0;
        secondIdx = 0;
        firstIdxSpawned = false;
        secondIdxSpawned = false;

        // Update view's coordinate system to fit the function
        updateViewCoordinates();
    }

    /**
     * Updates the view's coordinate system based on function's domain and range
     */
    private void updateViewCoordinates() {
        if (fullPeriodPoints == null || fullPeriodPoints.length == 0) {
            return;
        }

        // Find min/max Y values for proper scaling
        double yMin = Double.MAX_VALUE;
        double yMax = Double.MIN_VALUE;

        for (Point2D point : fullPeriodPoints) {
            if (!Double.isNaN(point.getY()) && !Double.isInfinite(point.getY())) {
                yMin = Math.min(yMin, point.getY());
                yMax = Math.max(yMax, point.getY());
            }
        }

        // Add some padding
        double yPadding = (yMax - yMin) * 0.1;
        yMin -= yPadding;
        yMax += yPadding;

        view.setCoordinateSystem(
                currentFunction.getXMin(),
                currentFunction.getXMax(),
                yMin,
                yMax
        );
    }

    /**
     * Main animation update logic - called every frame
     * This is where your three-index system comes to life!
     */
    private void updateAnimation() {
        if (fullPeriodPoints == null || fullPeriodPoints.length == 0) {
            return;
        }

        int arrayLength = fullPeriodPoints.length;

        // YOUR DESIGN: Check if we need to spawn trailing indices
        // firstIdx spawns when currentIndex passes first third
        if (!firstIdxSpawned && currentIndex > arrayLength / 3) {
            firstIdxSpawned = true;
            firstIdx = 0;  // Starts from beginning
        }

        // secondIdx spawns when currentIndex passes second third
        if (!secondIdxSpawned && currentIndex > (2 * arrayLength) / 3) {
            secondIdxSpawned = true;
            secondIdx = 0;  // Starts from beginning
        }

        // Update opacity values based on index positions
        // YOUR DESIGN: 33.33% -> 66.66% -> 100% as indices pass over points

        if (secondIdxSpawned) {
            // Set opacity at secondIdx position to 33.33%
            opacityArray[secondIdx] = 0.3333;
            secondIdx = (secondIdx + 1) % arrayLength;
        }

        if (firstIdxSpawned) {
            // Set opacity at firstIdx position to 66.66%
            opacityArray[firstIdx] = 0.6666;
            firstIdx = (firstIdx + 1) % arrayLength;
        }

        // Set opacity at currentIndex position to 100%
        opacityArray[currentIndex] = 1.0;
        currentIndex = (currentIndex + 1) % arrayLength;

        // When currentIndex wraps around, reset spawn flags for next cycle
        if (currentIndex == 0) {
            firstIdxSpawned = false;
            secondIdxSpawned = false;
        }

        // Render the graph
        renderGraph();
    }

    /**
     * Renders the entire graph based on current opacity values
     */
    private void renderGraph() {
        view.clearCanvas();

        // Draw lines between consecutive points for smooth curves
        for (int i = 0; i < fullPeriodPoints.length - 1; i++) {
            double opacity = opacityArray[i];

            if (opacity > 0.0) {
                Point2D p1 = fullPeriodPoints[i];
                Point2D p2 = fullPeriodPoints[i + 1];

                // Skip if points are invalid
                if (Double.isNaN(p1.getY()) || Double.isInfinite(p1.getY()) ||
                        Double.isNaN(p2.getY()) || Double.isInfinite(p2.getY())) {
                    continue;
                }

                view.drawLine(p1, p2, opacity);
            }
        }
    }

    /**
     * Starts the animation
     */
    public void start() {
        if (currentFunction != null) {
            lastUpdateTime = System.nanoTime();
            timer.start();
        }
    }

    /**
     * Stops the animation
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Updates amplitude parameter (called from slider)
     */
    public void setAmplitude(double amplitude) {
        if (currentFunction != null) {
            currentFunction.setAmplitude(amplitude);
            fullPeriodPoints = currentFunction.calculatePoints(fullPeriodPoints.length);
            updateViewCoordinates();
        }
    }

    /**
     * Updates frequency parameter (called from slider)
     */
    public void setFrequency(double frequency) {
        if (currentFunction != null) {
            currentFunction.setParameterA(frequency);
            fullPeriodPoints = currentFunction.calculatePoints(fullPeriodPoints.length);
            updateViewCoordinates();
        }
    }
}
