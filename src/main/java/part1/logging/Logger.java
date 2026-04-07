package part1.logging;

public interface Logger {
    abstract void log(String message, LogLevel level);

    default String formatMessage(String message, LogLevel level) {
        String time = java.time.LocalDateTime.now().toString();
        return time + " [" + level + "] " + message;
    }
}
