package part1.logging;

import java.time.Instant;

//Learned that refactor means change the structure of your code without changing what it does.,
public interface Logger {
    void log(String message, LogLevel level);

    default String formatMessage(String message, LogLevel level) {
        return Instant.now().toString() + " [" + level + "] " + message;
    }
}