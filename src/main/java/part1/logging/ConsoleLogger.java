package part1.logging;

public class ConsoleLogger implements Logger {

    @Override
    public void log(String message, LogLevel level) {
        System.out.println(formatMessage(message, level));
    }
}