package controllers;

import core.Conversion;
import io.HistoryFileWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui.ConverterView;

import java.io.IOException;

/**
 * TODO
 */
public class ConvertHandler implements EventHandler<ActionEvent> {

    // The view this handler reads input from and writes results to
    private final ConverterView view;

    // The IO component that logs results to a file
    private final HistoryFileWriter fileWriter;

    /**
     * Constructs a ConvertHandler with its required dependencies injected
     * TODO
     */
    public ConvertHandler(ConverterView view, HistoryFileWriter fileWriter) {
        this.view = view;
        this.fileWriter = fileWriter;
    }

    /**
     * Called by JavaFX when the "Convert" button fires an {@link ActionEvent}
     * TODO
     */
    @Override
    public void handle(ActionEvent event) {
        // 1. Get the selected conversion from the view
        Conversion conversion = view.getConversionBox().getValue();
        if (conversion == null) {
            view.showError("Please select a conversion");
            return;
        }

        // Read and validate the input number
        String inputText = view.getInputField().getText().trim();
        double inputValue;
        try {
            inputValue = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            view.showError("Please enter a valid number");
            return;
        }

        // Perform the conversion
        double result = conversion.convert(inputValue);

        // Show the result in the view
        String resultText = String.format("%.4f  →  %.4f", inputValue, result);
        view.showResult(conversion.name() + "\n" + resultText);

        // Log the result to the history file
        try {
            fileWriter.appendEntry(conversion.name(), inputValue, result);
            view.setStatus("Saved to history.txt");
        } catch (IOException e) {
            view.setStatus("Could not write to history.txt: " + e.getMessage());
        }
    }
}