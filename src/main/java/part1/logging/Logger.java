package part1.logging;

import java.time.Instant;

public interface Logger {
    void log(String message, LogLevel level);

     default void formatMessage(String message, LogLevel level) {
        System.out.println(Instant.now().toString() + " [" + level + "] " + message);

    }
}
