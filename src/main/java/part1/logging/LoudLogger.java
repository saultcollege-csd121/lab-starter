package part1.logging;

import part1.util.Messages;

import java.time.Instant;

public class LoudLogger implements Logger {
    public void log(String message, LogLevel level) {
        System.out.println(formatMessage(message, level).toUpperCase());
    }

}
