package io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for saving conversion results to a text file called history.txt
 *
 * Every time the user performs a conversion, this class appends one line
 * to the file with a timestamp, the conversion name, and the result
 * If the file does not exist yet it is created automatically
 */
public class HistoryFileWriter {

    // The path of the log file, relative to the working directory
    private static final String FILE_PATH = "history.txt";

    // Formatter for timestamps on each log entry
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Appends one conversion result as a new line to history.txt.
     * The file is opened in append mode, meaning existing content is never
     * overwritten: each call just adds a new line at the bottom
     *
     * Example line written:
     * [2024-01-15 10:30:45]  km → miles  |  input: 10.0000  →  result: 6.2137
     */
    public void appendEntry(String conversionName, double input, double result) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) { // Opens history.txt in append mode
            String timestamp = LocalDateTime.now().format(FORMATTER); // // Capture the current date and time and format it as a readable string
            writer.printf("[%s]  %s  |  input: %.4f  →  result: %.4f%n", timestamp, conversionName, input, result);
        }
    }
}