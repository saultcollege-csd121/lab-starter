package part1.logging;

import java.time.Instant;

public interface Logger {
    public abstract void log(String message, LogLevel level);
    public default String formatMessage(String message, LogLevel level){
        return (Instant.now().toString() + " [" + level + "] " + "I don't know what, but some sort of" + level + "error happened");
    }
}
