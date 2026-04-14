package ui;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * GraphView - View class responsible for rendering the graph
 *
 * This class handles all the visual rendering on a Canvas.
 * It receives drawing instructions from the controller and displays them.
 *
 * Responsibilities:
 * - Maintain the Canvas object
 * - Clear the canvas
 * - Draw points with specified opacity
 * - Transform mathematical coordinates to screen coordinates
 */
public class GraphView {

    private final Canvas canvas;
    private final GraphicsContext gc;

    private final double width;
    private final double height;

    // Coordinate transformation parameters
    private double scaleX;
    private double scaleY;
    private double offsetX;
    private double offsetY;

    /**
     * Constructor
     */
    public GraphView(double width, double height) {
        this.width = width;
        this.height = height;

        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();

        // Default scale and offset (will be updated based on function domain)
        scaleX = 30.0;
        scaleY = 30.0;
        offsetX = width / 2.0;
        offsetY = height / 2.0;

        clearCanvas();
    }

    /**
     * Clears the entire canvas
     */
    public void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        // Draw axes
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1.0);

        // X-axis
        gc.strokeLine(0, offsetY, width, offsetY);
        // Y-axis
        gc.strokeLine(offsetX, 0, offsetX, height);
    }

    /**
     * Draws a single point with the specified opacity
     *
     * @param mathPoint The point in mathematical coordinates
     * @param opacity The opacity value (0.0 to 1.0)
     */
    public void drawPoint(Point2D mathPoint, double opacity) {
        // Transform from math coordinates to screen coordinates
        double screenX = mathPointToScreenX(mathPoint.getX());
        double screenY = mathPointToScreenY(mathPoint.getY());

        // Set color with opacity
        gc.setFill(Color.color(0.2, 0.4, 0.8, opacity)); // Blue with variable alpha
        gc.setStroke(Color.color(0.2, 0.4, 0.8, opacity));

        // Draw small circle for the point
        double pointSize = 3.0;
        gc.fillOval(screenX - pointSize/2, screenY - pointSize/2, pointSize, pointSize);
    }

    /**
     * Draws a line between two points with specified opacity
     * This creates a smoother visual than individual points
     */
    public void drawLine(Point2D mathPoint1, Point2D mathPoint2, double opacity) {
        double screenX1 = mathPointToScreenX(mathPoint1.getX());
        double screenY1 = mathPointToScreenY(mathPoint1.getY());
        double screenX2 = mathPointToScreenX(mathPoint2.getX());
        double screenY2 = mathPointToScreenY(mathPoint2.getY());

        gc.setStroke(Color.color(0.2, 0.4, 0.8, opacity));
        gc.setLineWidth(2.0);
        gc.strokeLine(screenX1, screenY1, screenX2, screenY2);
    }

    /**
     * Updates the coordinate transformation based on function domain
     */
    public void setCoordinateSystem(double xMin, double xMax, double yMin, double yMax) {
        // Calculate scale to fit the function in the canvas
        double xRange = xMax - xMin;
        double yRange = yMax - yMin;

        scaleX = (width * 0.8) / xRange;  // Use 80% of canvas width
        scaleY = (height * 0.8) / yRange; // Use 80% of canvas height

        // Center the graph
        offsetX = width / 2.0 - (xMin + xMax) / 2.0 * scaleX;
        offsetY = height / 2.0 + (yMin + yMax) / 2.0 * scaleY;
    }

    /**
     * Converts mathematical X coordinate to screen X coordinate
     */
    private double mathPointToScreenX(double x) {
        return offsetX + x * scaleX;
    }

    /**
     * Converts mathematical Y coordinate to screen Y coordinate
     * Note: Y is inverted because screen coordinates increase downward
     */
    private double mathPointToScreenY(double y) {
        return offsetY - y * scaleY;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
