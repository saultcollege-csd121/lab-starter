package part1;

import part1.logging.LogLevel;
import part1.logging.Logger;

import java.time.Instant;

public class ConsoleLogger implements Logger {
    @Override
    public void log(String message, LogLevel level) {
        System.out.println(Instant.now().toString() + " [" + level + "] " + message);
    }
}