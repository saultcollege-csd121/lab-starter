package part1;

import part1.logging.*;
import part1.util.Messages;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            run(new ConsoleLogger(), 10);
            run(new LoudLogger(), 10);
            run(new CensoriousLogger(List.of("hungrish", "drabblex", "clorphed", "snorvish", "grumblet", "flonky", "blarfish")), 10);

            MemoryLogger logger = new MemoryLogger();
            run(logger, 10);

            export(logger, new FileOutputStream("logs.txt"));

        } catch (Exception e) {
            System.out.println("Could not create log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void run(Logger logger, int n) {
        for (int i = 0; i < n; i++) {
            var randomLevel = LogLevel.values()[(int) (Math.random() * LogLevel.values().length)];
            var message = Messages.getRandomMessage();
            logger.log(message, randomLevel);
        }
    }

    public static void export(Exportable exporter, OutputStream stream) {
        exporter.exportTo(stream);
    }
}