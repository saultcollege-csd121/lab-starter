package part1;

import part1.logging.*;
import part1.logging.LogLevel;
import part1.util.Messages;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Main {

    static void main(String[] args) {

        try {
            MemoryLogger logger = new MemoryLogger();
            run(logger, 50);
            export(logger, new FileOutputStream("logs.txt")); // Export all stored messages to a file

        } catch (Exception e) {
            IO.println("Could not create log file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Any logger works here
    public static void run(Logger logger, int n) {
        for ( int i = 0; i < n; i++ ) {
            var randomLevel = LogLevel.values()[(int) (Math.random() * LogLevel.values().length)];
            var message = Messages.getRandomMessage();
            logger.log(message, randomLevel);
        }
    }

    public static void export(Exportable exporter, OutputStream stream) {
        exporter.exportTo(stream);
    }
}
