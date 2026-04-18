package ui;

import controller.NoteController;
import core.Note;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {



    @Override
    public void start(Stage stage) {

        ListView<Note> listView = new ListView<>();
        TextField inputField = new TextField();

        Button addBtn = new Button("Add");
        Button saveBtn = new Button("Save");
        Button loadBtn = new Button("Load");

        NoteController controller = new NoteController(listView, inputField);

        addBtn.setOnAction(controller);
        saveBtn.setOnAction(controller);
        loadBtn.setOnAction(controller);

        VBox root = new VBox(10, inputField, addBtn, saveBtn, loadBtn, listView);

        Scene scene = new Scene(root, 300, 400);
        stage.setTitle("Notes Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
