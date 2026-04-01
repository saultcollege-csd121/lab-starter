package part1.logging;

import java.time.Instant;

/**
 * Defines the contract for all logger implementations.
 * Any class that implements Logger must provide a log() method.
 */
public interface Logger { //This interface allows me to treat ConsoleLogger and LoudLogger as the same "type"

    /**
     * Logs a message with different severity levels.
     * @param message the message to log
     * @param level the severity level of each message
     */
    void log(String message, LogLevel level); //abstract method implemented different for each logger

    /**
     * Formats a log message into a standard format.
     * @param message the message to log
     * @param level the severity level of each message
     * @return a formatted string in the format: <timestamp> [<level>] <message>
     */
    default String formatMessage(String message, LogLevel level) { //default that is inherited for all loggers.
        return Instant.now() + " [" + level + "] " + message;
    }
}