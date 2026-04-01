package part1.logging;

/**
 * A logger that Always capitalizes all messages, and replaces ‘.’ with ‘!!!’
 */
public class LoudLogger implements Logger {

    /**
     * Logs a message in uppercase with periods replaced by "!!!"
     * @param message the message to log
     * @param level the severity level of each message
     */
    @Override
    public void log(String message, LogLevel level) {
        String loud = message.toUpperCase().replace(".", "!!!");
        System.out.println(formatMessage(loud, level));
    }
}