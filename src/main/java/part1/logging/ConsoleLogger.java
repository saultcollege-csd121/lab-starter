package part1.logging;

import java.time.Instant;

public class ConsoleLogger implements Logger { // implements means this class promises to have a log() method

    @Override
    public void log(String message, LogLevel level) {
        System.out.println(formatMessage(message, level));
    }
}