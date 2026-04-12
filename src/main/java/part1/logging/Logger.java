package part1.logging;

import java.time.Instant;

public interface Logger {
    void log(String message, LogLevel level);

    default String formatMessage(String message, LogLevel level) {
        return Instant.now().toString() + " [" + level + "] " + message;
    }
}