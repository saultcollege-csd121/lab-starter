package part1.logging;

public class LoudLogger implements Logger {
    @Override
    public void log(String message, LogLevel level) {
        message = formatMessage(message, level);
        IO.println(message.toUpperCase().replace(".", "!!!"));
    }

}
