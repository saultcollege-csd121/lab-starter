import controllers.AnimationController;
import core.MathFunction;
import io.FunctionLoader;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.GraphView;

/**
 * MyMathPlotter - Main JavaFX Application
 *
 * This app animates mathematical functions with a trailing fade effect.
 * Functions are loaded from a remote JSON file (network IO requirement).
 *
 * Architecture: MVC Pattern
 * - Model: MathFunction (represents function data)
 * - View: GraphView (Canvas rendering)
 * - Controller: AnimationController (manages animation loop and logic)
 */
public class Main extends Application{

    private AnimationController controller;
    private GraphView graphView;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the view (canvas)
        graphView = new GraphView(800, 600);

        // Initialize the controller
        controller = new AnimationController(graphView);

        // Create UI controls
        VBox controlPanel = createControlPanel();

        // Status label to show loading state
        statusLabel = new Label("Loading functions from network...");
        statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");

        // Layout
        BorderPane root = new BorderPane();
        root.setCenter(graphView.getCanvas());
        root.setTop(controlPanel);
        root.setBottom(statusLabel);
        BorderPane.setAlignment(statusLabel, Pos.CENTER);
        BorderPane.setMargin(statusLabel, new Insets(10));

        // Load functions from network (asynchronously)
        loadFunctionsAsync();

        // Scene setup
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("MyMathPlotter - Animated Function Grapher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates the control panel with function buttons and parameter sliders
     */
    private VBox createControlPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(15));
        panel.setStyle("-fx-background-color: #f0f0f0;");

        // Function selection buttons (will be populated after loading)
        Label functionLabel = new Label("Select Function:");
        HBox buttonBox = new HBox(10);
        buttonBox.setId("buttonBox"); // For later population

        // Parameter sliders
        Label paramLabel = new Label("Adjust Parameters:");

        // Amplitude slider
        Label ampLabel = new Label("Amplitude: 1.0");
        Slider amplitudeSlider = new Slider(0.1, 3.0, 1.0);
        amplitudeSlider.setShowTickLabels(true);
        amplitudeSlider.setShowTickMarks(true);
        amplitudeSlider.setMajorTickUnit(0.5);
        amplitudeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            ampLabel.setText(String.format("Amplitude: %.2f", newVal.doubleValue()));
            controller.setAmplitude(newVal.doubleValue());
        });

        // Frequency slider (Parameter A in complex wave)
        Label freqLabel = new Label("Frequency: 1.0");
        Slider frequencySlider = new Slider(0.1, 50.0, 1.0);
        frequencySlider.setShowTickLabels(true);
        frequencySlider.setShowTickMarks(true);
        frequencySlider.setMajorTickUnit(10.0);
        frequencySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            freqLabel.setText(String.format("Frequency: %.2f", newVal.doubleValue()));
            controller.setFrequency(newVal.doubleValue());
        });

        VBox sliderBox = new VBox(5, ampLabel, amplitudeSlider, freqLabel, frequencySlider);

        panel.getChildren().addAll(functionLabel, buttonBox, paramLabel, sliderBox);

        return panel;
    }

    /**
     * Loads functions from remote JSON file asynchronously
     * This demonstrates the Network IO requirement
     */
    private void loadFunctionsAsync() {
        // Run network operation in background thread to avoid blocking UI
        Thread loadThread = new Thread(() -> {
            try {
                // Use FunctionLoader to fetch from GitHub Gist
                FunctionLoader loader = new FunctionLoader();
                MathFunction[] functions = loader.loadFunctionsFromNetwork();

                // Update UI on JavaFX Application Thread
                javafx.application.Platform.runLater(() -> {
                    populateFunctionButtons(functions);
                    statusLabel.setText("✓ Loaded " + functions.length + " functions from network");

                    // Start with first function
                    if (functions.length > 0) {
                        controller.setFunction(functions[0]);
                        controller.start();
                    }
                });

            } catch (Exception e) {
                javafx.application.Platform.runLater(() -> {
                    statusLabel.setText("✗ Error loading functions: " + e.getMessage());
                    statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: red;");
                });
                e.printStackTrace();
            }
        });

        loadThread.setDaemon(true);
        loadThread.start();
    }

    /**
     * Populates the button box with function selection buttons
     */
    private void populateFunctionButtons(MathFunction[] functions) {
        VBox panel = (VBox) graphView.getCanvas().getParent().getParent();
        HBox buttonBox = (HBox) panel.lookup("#buttonBox");

        for (MathFunction function : functions) {
            Button btn = new Button(function.getName());
            btn.setOnAction(e -> {
                controller.stop();
                controller.setFunction(function);
                controller.start();
            });
            buttonBox.getChildren().add(btn);
        }
    }

    @Override
    public void stop() {
        // Clean up when application closes
        if (controller != null) {
            controller.stop();
        }
    }

    static void main(String[] args) { Application.launch(args); }
}
