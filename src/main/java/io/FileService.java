package file;

import core.Note;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private static final String FILE_NAME = "notes.txt";


    // method to save notes
    public void saveNotes(List<Note> notes) { // here we are using a list to enable us save multiple notes

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){

            for (Note note : notes) {
                writer.write(note.getContent());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // method to load notes
    public List<Note> loadNotes() {
        List<Note> notes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;

            while((line = reader.readLine()) != null){
                notes.add(new Note(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return notes;
    }




}