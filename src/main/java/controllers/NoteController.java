package controller;


import core.Note;
import file.FileService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.util.ArrayList;
import java.util.List;

public class NoteController implements EventHandler<ActionEvent> {

    private ListView<Note> listView;
    private TextField inputField;
    private List<Note> notes;
    private FileService fileService;

    public NoteController(ListView<Note> listView, TextField inputField) {
        this.listView = listView;
        this.inputField = inputField;
        this.notes = new ArrayList<>();
        this.fileService = new FileService();
    }




    @Override
    public void handle(ActionEvent event) {
        Button source = (Button) event.getSource();

        switch (source.getText()) {
            case "Add":
                addNote();
                break;
            case "Save":
                fileService.saveNotes(notes);
                break;
            case "Load":
                loadNotes();
                break;
        }
    }

    private void addNote() {
        String text = inputField.getText();
        if (!text.isEmpty()) {
            Note note = new Note(text);
            notes.add(note);
            listView.getItems().add(note);
            inputField.clear();
        }
    }

    private void loadNotes() {
        notes = fileService.loadNotes();
        listView.getItems().clear();
        listView.getItems().addAll(notes);
    }
}