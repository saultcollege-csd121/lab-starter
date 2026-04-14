package ui; // This file belongs to the ui package

import core.Conversion;
import core.ConversionCategory;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The main UI panel for the Unit Converter application
 *
 * This class builds and displays all the visual elements the user interacts with:
 * a category dropdown, a conversion dropdown, a number input, a convert button,
 * and labels to show the result and status.
 *
 * It extends VBox, meaning all its children are stacked vertically on screen
 */

public class ConverterView extends VBox {

    // Instance variables
    private final ComboBox<ConversionCategory> categoryBox;
    private final ComboBox<Conversion> conversionBox;
    private final TextField inputField;
    private final Button convertButton;
    private final Label resultLabel;
    private final Label statusLabel;

    /**
     * Builds and lays out every UI element.
     * Called once when Main creates a new ConverterView().
     */
    public ConverterView() {

        // CONFIGURE VBOX
        setSpacing(14); // 14px vertical gap between each child node
        setPadding(new Insets(36, 48, 36, 48));  // space around the inside edge: top=36, right=48, bottom=36, left=48
        setAlignment(Pos.TOP_CENTER); // children are centered horizontally, anchored to the top
        setStyle("-fx-background-color: #F5F7FA;"); // sets the background colour using a CSS string

        // TITLE LABEL
        Label titleLabel = new Label("Unit Converter"); // creates a label with this text
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 26)); // Georgia font, bold, 26pt
        titleLabel.setTextFill(Color.web("#1A1A2E")); // very dark navy text colour

        // ACCENT BAR
        Rectangle accentBar = new Rectangle(320, 4); // 320px wide, 4px tall
        accentBar.setFill(Color.web("#4A90D9")); // filled with blue
        accentBar.setArcWidth(4); // rounds the corners horizontally
        accentBar.setArcHeight(4); // rounds the corners vertically

        //PICKER SECTION
        Label categoryLabel = new Label("Category"); // small label above the dropdown
        categoryLabel.setFont(Font.font("Georgia", 13)); // Georgia font, 13pt, normal weight
        categoryLabel.setTextFill(Color.web("#555555")); // medium grey colour

        categoryBox = new ComboBox<>(
            FXCollections.observableArrayList(ConversionCategory.values())
        );
        categoryBox.setPromptText("Select a category..."); // greyed-out placeholder text when nothing is selected
        categoryBox.setPrefWidth(320); // preferred width of 320px

        // CONVERSION TYPE PICKER
        Label conversionLabel = new Label("Conversion"); // small label above the second dropdown
        conversionLabel.setFont(Font.font("Georgia", 13));
        conversionLabel.setTextFill(Color.web("#555555"));

        // HOLDS CONVERSION RECORDS
        // filled later by loadConversionsFor()
        conversionBox = new ComboBox<>();
        conversionBox.setPromptText("Select a conversion...");
        conversionBox.setPrefWidth(320);
        conversionBox.setDisable(true); // greyed out — user must pick a category first

        // NUMBER INPUT
        Label inputLabel = new Label("Value to convert");
        inputLabel.setFont(Font.font("Georgia", 13));
        inputLabel.setTextFill(Color.web("#555555"));

        inputField = new TextField(); // single-line text box
        inputField.setPromptText("Enter a number..."); // placeholder text
        inputField.setPrefWidth(320);
        inputField.setDisable(true); // greyed out — user must pick a conversion first

        // --- Convert button ---
        convertButton = new Button("Convert"); // button with this label
        convertButton.setPrefWidth(320); // same width as the other controls
        convertButton.setPrefHeight(40); // taller than default for easier clicking
        convertButton.setDisable(true); // greyed out until input is ready
        convertButton.setFont(Font.font("Georgia", FontWeight.BOLD, 14));
        // Multi-line CSS string. It styles the button like a web stylesheet would
        convertButton.setStyle("""
            -fx-background-color: #4A90D9;  
            -fx-text-fill: white
            -fx-background-radius: 6;   
            -fx-cursor: hand; 
            """);

        // DRIVER LINE (SHAPE NODE)
        Rectangle divider = new Rectangle(320, 1); // very thin horizontal line
        divider.setFill(Color.web("#DDDDDD")); // light grey

        // RESULT LABEL
        resultLabel= new Label(""); // starts empty
        resultLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 22)); // large bold text for the result
        resultLabel.setTextFill(Color.web("#1A1A2E"));
        resultLabel.setWrapText(true); // if text is too long, wrap to next line instead of cutting off

        // STATUS LABEL
        statusLabel = new Label(""); // starts empty
        statusLabel.setFont(Font.font("Georgia", 12)); // smaller, lighter text
        statusLabel.setTextFill(Color.web("#888888"));// light grey

        // ADD ALL CHILDREN TO THIS VBOX
        // getChildren() returns the VBox's list of child nodes
        // addAll() appends every argument to that list, in order from top to bottom
        getChildren().addAll(
            titleLabel, accentBar, // title + blue bar
            categoryLabel, categoryBox, // Category: label + dropdown
            conversionLabel, conversionBox, // Conversion: label + dropdown
            inputLabel, inputField, // Value to convert: label + text box
            convertButton, // the Convert button
            divider, // thin grey line divider
            resultLabel,// result text
            statusLabel  // status text
        );
    }

    // GETTERS

    /** @return the category dropdown */
    public ComboBox<ConversionCategory> getCategoryBox(){
        return categoryBox; // returns the reference to the private field
    }

    /** @return the conversion type dropdown */
    public ComboBox<Conversion> getConversionBox() {
        return conversionBox;
    }

    /** @return the number input text field */
    public TextField getInputField() {
        return inputField;
    }

    /** @return the Convert button */
    public Button getConvertButton() {
        return convertButton;
    }

    // DISPLAY UPDATE METHODS
    // The controller calls these to change what the user sees
    // The view never calls these itself - it just provides them

    /**
     * Populates the conversion ComboBox with the conversions for the chosen
     * category, and enables it for use.
     */
    public void loadConversionsFor(ConversionCategory category) {
        conversionBox.setItems( // replaces the dropdown's content
            FXCollections.observableArrayList(category.getConversions()) // wraps the category's conversion list
        );
        conversionBox.getSelectionModel().clearSelection(); // deselects whatever was previously chosen
        conversionBox.setPromptText("Select a conversion...");
        conversionBox.setDisable(false); // now that a category is chosen, unlock this dropdown

        // Reset everything below - so data from a previous selection is cleared
        inputField.setDisable(true);// re-lock input until a conversion is chosen
        inputField.clear(); // erase any previously typed number
        convertButton.setDisable(true);
        resultLabel.setText(""); // clear the old result
        statusLabel.setText(""); // clear the old status message
    }

    /**
     * Enables the input field and convert button once a conversion is chosen
     */
    public void enableInput() {
        inputField.setDisable(false); // unlock the text field
        convertButton.setDisable(false); // unlock the button
        inputField.clear(); // clear any leftover text
        resultLabel.setText(""); // clear old result
        statusLabel.setText("");
    }

    /**
     * Shows a successful conversion result
     * @param text the formatted result string to display
     */
    public void showResult(String text) {
        resultLabel.setTextFill(Color.web("#1A1A2E"));
        resultLabel.setText(text); // display the result string
    }

    /**
     * Shows an error message (e.g. invalid input or IO failure).
     * @param message the error text to display
     */
    public void showError(String message) {
        resultLabel.setTextFill(Color.web("#DC3545"));
        resultLabel.setText(message);
        statusLabel.setText(""); // clear the status line so it doesn't contradict the error
    }

    /**
     * Sets the small status line below the result (e.g. "Saved to history.txt").
     * @param message the status text
     */
    public void setStatus(String message) {
        statusLabel.setText(message); // updates the grey label at the bottom
    }
}