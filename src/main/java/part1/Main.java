package part1;

import part1.logging.*;
import part1.logging.LogLevel;
import part1.util.Messages;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.List;




public class Main {

    static void main(String[] args) {

//        Logger logger = new CensoriousLogger(List.of("hungrish", "drabblex", "clorphed", "snorvish", "grumblet", "flonky", "blarfish"));
        MemoryLogger logger = new MemoryLogger();

        try {
            // TODO: Implement MemoryLogger

            // TODO: (Optional) Implement a JDBC logger
//            Logger logger = new JdbcLogger("jdbc:sqlite:log.db");

            // TODO: you can change the first argument here to one of the loggers above,
            //       or to a different constructor call based on the examples above.
            run(new CensoriousLogger((List.of("hungrish", "drabblex", "clorphed", "snorvish", "grumblet", "flonky", "blarfish"))),50);

            // TODO: uncomment this while you are trying out your MemoryLogger
            ColorLogger.export(logger, new FileOutputStream("logs.txt"));  // OR try System.out as the second parameter!

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

}