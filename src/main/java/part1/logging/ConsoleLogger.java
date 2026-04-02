package part1.logging;

import java.time.Instant;


public class ConsoleLogger implements Logger{

    @Override
    public void log(String message, LogLevel level) {
            System.out.println(formatMessage(message, level));
    }
}
