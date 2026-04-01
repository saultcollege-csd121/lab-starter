package part1.logging;

import java.time.Instant;

public class ConsoleLogger implements Logger{
    public void log(String message, LogLevel level) {
        System.out.println(FormatMessage(message, level));
    }
    private String FormatMessage(String message, LogLevel level){
        return (Instant.now().toString() + " [" + level + "] " + message);

    }
}
