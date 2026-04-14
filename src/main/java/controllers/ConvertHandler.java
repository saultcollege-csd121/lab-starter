package controllers;

import core.Conversion;
import io.HistoryFileWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui.ConverterView;

import java.io.IOException;

/**
 * ConvertHandler is responsible for handling the click event of the Convert button.
 *
 * It implements the EventHandler interface from JavaFX, which means it must
 * have a handle() method that JavaFX will call automatically when the button is clicked.
 *
 * In the MVC pattern, this class is the Controller it sits between the view
 * (ConverterView) and the core logic (Conversion) and coordinates them:
 *
 *   1. Reads the user's input from the view
 *   2. Validates it (checks it's actually a number)
 *   3. Calls the conversion formula from the core package
 *   4. Tells the view to display the result
 *   5. Tells the IO layer to save the result to a file
 */
public class ConvertHandler implements EventHandler<ActionEvent> {

    private final ConverterView view;
    private final HistoryFileWriter fileWriter;

    /**
     * Creates a ConvertHandler with its two dependencies injected from outside
     * @param view the ConverterView to read input from and display results to
     * @param fileWriter the HistoryFileWriter to log results with
     */
    public ConvertHandler(ConverterView view, HistoryFileWriter fileWriter) {
        this.view = view;
        this.fileWriter = fileWriter;
    }

    /**
     * Called automatically by JavaFX when the Convert button is clicked
     *
     * This method does not do the math itself it delegates to Conversion.convert()
     * for the calculation and to HistoryFileWriter.appendEntry() for the file logging.
     * Each class is responsible for only its own job
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