package part1;

import part1.logging.*;
import part1.logging.LogLevel;
import part1.util.Messages;

import java.io.OutputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            LoudLogger logger = new LoudLogger();
            run(logger, 5);

            run(new CensoriousLogger(List.of("fluffly", "smorange", "clibbled", "drindled")), 20);

            MemoryLogger loggerBrain = new MemoryLogger();
            run(loggerBrain, 5);
            export(loggerBrain, System.out);

            run(new ConsoleLogger(), 5);

        } catch (Exception e) {
            IO.println("Could not create log file: " + e.getMessage());
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