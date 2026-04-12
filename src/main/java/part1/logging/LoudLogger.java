package part1.logging;

public class LoudLogger implements Logger {

    @Override
    public void log(String message, LogLevel level) {
        String newMessage = message.toUpperCase().replace(".", "!!!");
        System.out.println(formatMessage(newMessage, level));
    }
}