package ui;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import controllers.DashboardController;
import core.RateSnapshot;
import core.SearchRecord;
import io.MarketApiService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;


/**
 * Builds all the JavaFX UI and handles button clicks.
 * When something changes it calls the controller, then updates the screen.
 */
public class DashboardView {

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private DashboardController controller;


    private ComboBox<String> basePicker;      // top bar
    private Button refreshBtn;
    private Label updatedLabel;
    private Circle statusDot;

    private ListView<String> ratesList;      // live rates tab

    private TextField amountField;            // converter tab
    private ComboBox<String> targetPicker;
    private Label resultLabel;

    private ListView<String> historyList;      // history tab

    public DashboardView(DashboardController controller) {
        this.controller = controller;
    }

    public BorderPane buildRoot() {       // builds the whole layout, called the  Main
        BorderPane root = new BorderPane();
        root.setTop(buildTopBar());
        root.setCenter(buildTabPane());
        return root;
    }

    private HBox buildTopBar() {        // this is the top bar: title, base currency, refresh button, status dot
        Label titleLabel = new Label("Market Dashboard");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        Label baseLabel = new Label("Base currency:");

        basePicker = new ComboBox<>(FXCollections.observableArrayList("CAD", "USD", "EUR", "GBP"));
        basePicker.setValue("CAD");

        refreshBtn = new Button("Refresh Rates");

        refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleRefresh();
            }
        });

        statusDot = new Circle(7, Color.GRAY); // color based on output (gray = not loaded. green = ok. red = error)
        updatedLabel = new Label("Not loaded");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bar = new HBox(12, titleLabel, spacer, baseLabel, basePicker, refreshBtn, statusDot, updatedLabel);
        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setPadding(new Insets(12, 16, 12, 16));
        bar.setStyle("-fx-background-color: #dce8f5;");
        return bar;
    }

    private TabPane buildTabPane() {        // 3 tabs: live rates, converter, history
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabs.getTabs().addAll(
                new Tab("Live Rates", buildRatesTab()),
                new Tab("Converter",  buildConverterTab()),
                new Tab("History",    buildHistoryTab())
        );
        return tabs;
    }

    private VBox buildRatesTab() {      // tab 1 - list of current exchange rates
        ratesList = new ListView<>();  //ListView that fills up after refresh
        ratesList.setPlaceholder(new Label("Press 'Refresh Rates' to load data."));

        VBox box = new VBox(10, new Label("Current rates (1 unit of base currency):"), ratesList);
        box.setPadding(new Insets(14));
        VBox.setVgrow(ratesList, Priority.ALWAYS);
        return box;
    }

    private GridPane buildConverterTab() {   // tab 2 - convert an amount from base currency to a target
        Label amtLabel    = new Label("Amount:");
        Label targetLabel = new Label("Convert to:");
        Label answerLabel = new Label("Result:");

        amountField = new TextField("1.00");
        targetPicker = new ComboBox<>(FXCollections.observableArrayList(MarketApiService.CURRENCIES));
        targetPicker.setValue("USD");

        Button convertBtn = new Button("Convert");
        convertBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleConvert();
            }
        });

        resultLabel = new Label("--");
        resultLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        GridPane grid = new GridPane();   //GridPane with input field, dropdown, result label
        grid.setHgap(12);
        grid.setVgap(14);
        grid.setPadding(new Insets(24));

        grid.add(amtLabel,    0, 0);
        grid.add(amountField, 1, 0);
        grid.add(targetLabel, 0, 1);
        grid.add(targetPicker,1, 1);
        grid.add(convertBtn,  1, 2);
        grid.add(answerLabel, 0, 3);
        grid.add(resultLabel, 1, 3);

        return grid;
    }

    private VBox buildHistoryTab() {     // tab 3 - load and clear the search history saved in the history.
        historyList = new ListView<>();  //ListView loaded from history
        historyList.setPlaceholder(new Label("No history loaded."));

        Button loadBtn = new Button("Load History");
        loadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLoadHistory();
            }
        });

        Button clearBtn = new Button("Clear History");
        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleClearHistory();
            }
        });

        HBox buttons = new HBox(10, loadBtn, clearBtn);

        VBox box = new VBox(10, buttons, historyList);
        box.setPadding(new Insets(14));
        VBox.setVgrow(historyList, Priority.ALWAYS);
        return box;
    }

    private void handleRefresh() {          // event handlers
        String base = basePicker.getValue(); //EventHandler will be written as anonymous classes on purpose
        try {
            RateSnapshot snapshot = controller.refreshRates(base);
            updateRatesList(snapshot);
            updatedLabel.setText("Updated: " + snapshot.getFetchedAt().format(TIME_FMT));
            statusDot.setFill(Color.GREEN);
        } catch (Exception ex) {
            statusDot.setFill(Color.RED);
            showAlert("Could not fetch rates:\n" + ex.getMessage());
        }
    }

    private void handleConvert() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            String target = targetPicker.getValue();
            double result = controller.convert(amount, target);

            if (result < 0) {
                resultLabel.setText("Load rates first!");
            } else {
                resultLabel.setText(String.format("%.2f %s = %.4f %s",
                        amount, basePicker.getValue(), result, target));
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Enter a valid number.");
        }
    }

    private void handleLoadHistory() {
        try {
            List<SearchRecord> records = controller.loadHistory();
            historyList.getItems().clear();

            if (records.isEmpty()) {
                historyList.getItems().add("No history found.");
            } else {
                for (SearchRecord r : records) {
                    historyList.getItems().add(r.toString());
                }
            }
        } catch (Exception ex) {
            showAlert("Could not load history:\n" + ex.getMessage());
        }
    }

    private void handleClearHistory() {
        try {
            controller.clearHistory();
            historyList.getItems().clear();
        } catch (Exception ex) {
            showAlert("Could not clear history:\n" + ex.getMessage());
        }
    }

    private void updateRatesList(RateSnapshot snapshot) {       // fills the rates list after a successful refresh
        ratesList.getItems().clear();
        for (Map.Entry<String, Double> entry : snapshot.getRates().entrySet()) {
            ratesList.getItems().add(
                    String.format("%-6s  =  %.4f", entry.getKey(), entry.getValue())
            );
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Market Dashboard");
        alert.showAndWait();
    }
}