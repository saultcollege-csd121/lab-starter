package part1.logging;

/**
 * A logger that prints log messages to the console.
 */
public class ConsoleLogger implements Logger {

    /**
     * Logs a message with different severity levels.
     * @param message the message to log
     * @param level the severity level of each message
     */
    @Override
    public void log(String message, LogLevel level) {
        System.out.println(formatMessage(message, level));
    }
}