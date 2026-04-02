package part1.logging;

import java.time.Instant;

public interface Logger {
    // Any class that implements Logger MUST have this method
    void log(String message, LogLevel level);

    // Default method so all loggers can use the same timestamp format
    default String formatMessage(String message, LogLevel level) {
        return Instant.now().toString() + " [" + level + "] " + message;
    }
}
