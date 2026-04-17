package ui;

import controllers.StudySessionController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSessionHandler implements EventHandler<ActionEvent> {

    private final StudySessionController controller;
    private final TextField subjectField;
    private final TextField minutesField;
    private final ComboBox<String> difficultyBox;
    private final Label messageLabel;
    private final Runnable refreshCallback;

    public AddSessionHandler(
            StudySessionController controller,
            TextField subjectField,
            TextField minutesField,
            ComboBox<String> difficultyBox,
            Label messageLabel,
            Runnable refreshCallback
    ) {
        this.controller = controller;
        this.subjectField = subjectField;
        this.minutesField = minutesField;
        this.difficultyBox = difficultyBox;
        this.messageLabel = messageLabel;
        this.refreshCallback = refreshCallback;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            controller.addSession(
                    subjectField.getText(),
                    minutesField.getText(),
                    difficultyBox.getValue()
            );

            subjectField.clear();
            minutesField.clear();
            difficultyBox.setValue("Medium");

            messageLabel.setText("Session added!");
            refreshCallback.run();

        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }
}