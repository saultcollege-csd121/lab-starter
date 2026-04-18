package io;

import core.SearchRecord;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves and loads search history from a local CSV file.
 */
public class HistoryFileService {

    private static final String fileName = "history.txt";

    /**
     * Appends one record to the history, creating the file if is not file created.
     */
    public void save(SearchRecord record) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(
                Path.of(fileName),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
                    writer.write(record.toCsvLine());
                    writer.newLine();
        }
    }

    /**
     * Loads all records from the history
     * Returns empty list if file missing
     */
    public List<SearchRecord> loadAll() throws IOException {
        List<SearchRecord> list = new ArrayList<>();
        Path path = Path.of(fileName);

        if (!Files.exists(path)) return list;  // no file yet, just return empty

        for (String line : Files.readAllLines(path)) {
            if (!line.isBlank()) {
                try {
                    list.add(SearchRecord.fromCsvLine(line));
                } catch (IllegalArgumentException e) {   // skip any lines that are messed up
                    System.err.println("Skipping bad line: " + line);
                }
            }
        }

        return list;
    }

    /**
     * Deletes the history file.
     */
    public void clear() throws IOException {
        Files.deleteIfExists(Path.of(fileName));
    }
}