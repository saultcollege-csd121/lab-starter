package part1.logging;

import java.io.OutputStream;
import java.io.PrintWriter;

public class StreamLogger implements Logger {

    private final PrintWriter writer;

    public StreamLogger(OutputStream outputStream) {
        this.writer = new PrintWriter(outputStream, true);
    }

    @Override
    public void log(String message, LogLevel level) {
        writer.println(formatMessage(message, level));
    }
}