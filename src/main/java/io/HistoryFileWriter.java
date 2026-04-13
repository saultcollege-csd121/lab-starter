package io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles writing conversion results to a plain text history log file
 *
 * <p>Each time the user performs a conversion, one line is appended to
 * {@code history.txt} in the working directory.</p>
 */
public class HistoryFileWriter {

    // The path of the log file, relative to the working directory
    private static final String FILE_PATH = "history.txt";

    // Formatter for timestamps on each log entry
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Appends one conversion result as a new line to {@code history.txt}.
     * TODO
     */
    public void appendEntry(String conversionName, double input, double result)
            throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.printf("[%s]  %s  |  input: %.4f  →  result: %.4f%n",
                    timestamp, conversionName, input, result);
        }
    }
}