package io;

import core.FileWrite;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


// a nice little class to handle the file saving method ~ ENCAPSULATION WOWOWOWOW ~
public class FileSubmission implements EventHandler<MouseEvent> {
    private final int count;
    private final String fileName;

    public FileSubmission(int count, String fileName) {
        this.count = count;
        this.fileName = fileName;
    }

    @Override
    public void handle (MouseEvent event) {
        FileWrite.main(fileName, count);
    }
}
