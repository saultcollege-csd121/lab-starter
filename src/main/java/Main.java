import controllers.StudySessionController;
import core.StudySession;
import io.CSVStudySession;
import io.StudySessionInterface;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import ui.AddSessionHandler;

import java.io.IOException;
import java.nio.file.Path;

public class Main extends Application {

    private Label statsLabel;
    private Label messageLabel;

    @Override
    public void start(Stage stage) {

        StudySessionInterface repo = new CSVStudySession(Path.of("sessions.csv"));
        StudySessionController controller = new StudySessionController(repo);

        TextField subjectField = new TextField();
        subjectField.setPromptText("Subject");

        TextField minutesField = new TextField();
        minutesField.setPromptText("Minutes");

        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Medium");

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");

        ListView<StudySession> listView = new ListView<>();
        listView.setItems(controller.getSessions());

        statsLabel = new Label("Stats...");
        messageLabel = new Label("Ready");

        Circle circle = new Circle(8, Color.BLUE);

        Runnable refresh = () -> {
            statsLabel.setText("Sessions: " + controller.getSessionCount() +
                    " | Minutes: " + controller.getTotalMinutes());
        };

        addBtn.setOnAction(new AddSessionHandler(
                controller, subjectField, minutesField,
                difficultyBox, messageLabel, refresh
        ));

        deleteBtn.setOnAction(e -> {
            try {
                controller.deleteSession(listView.getSelectionModel().getSelectedItem());
                refresh.run();
            } catch (IOException ex) {
                messageLabel.setText("Error deleting");
            }
        });

        VBox left = new VBox(10,
                subjectField, minutesField,
                difficultyBox, addBtn, deleteBtn
        );

        VBox right = new VBox(10,
                listView, statsLabel, messageLabel
        );

        BorderPane root = new BorderPane();
        root.setLeft(left);
        root.setCenter(right);
        root.setTop(circle);
        root.setPadding(new Insets(10));

        try {
            controller.load();
            refresh.run();
        } catch (IOException e) {
            messageLabel.setText("Could not load file");
        }

        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Study Tracker");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}