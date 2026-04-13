package app;

import controllers.ConvertHandler;
import core.ConversionCategory;
import io.HistoryFileWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.ConverterView;

/**
 * Entry point for the Unit Converter JavaFX application.
 * <p>This class is intentionally thin — it only assembles the pieces.
 * All real logic lives in the other packages.</p>
 */
public class Main extends Application {

    /**
     * Called by JavaFX after the platform is ready.
     * Builds the scene, wires up the controller, and shows the window.
     *
     * @param stage the primary window provided by JavaFX
     */
    @Override
    public void start(Stage stage) {

        // Create the view
        ConverterView view = new ConverterView();

        // Create the IO helper
        HistoryFileWriter fileWriter = new HistoryFileWriter();

        // Create the event handler, injecting its dependencies
        ConvertHandler convertHandler = new ConvertHandler(view, fileWriter);

        // Wire up the Convert button to the handler
        view.getConvertButton().setOnAction(convertHandler);

        // Wire up the category ComboBox when the user picks a category, reload the conversion list
        view.getCategoryBox().setOnAction(event -> {
            ConversionCategory selected = view.getCategoryBox().getValue();
            if (selected != null) {
                view.loadConversionsFor(selected);
            }
        });

        // Wire up the conversion ComboBox when the user picks a conversion, enable the input field
        view.getConversionBox().setOnAction(event -> {
            if (view.getConversionBox().getValue() != null) {
                view.enableInput();
            }
        });

        // Build and show the scene
        Scene scene = new Scene(view, 420, 480);
        stage.setTitle("Unit Converter");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * JVM entry point. Calls {@link Application#launch} to start JavaFX
     */
    public static void main(String[] args) {
        launch(args);
    }
}