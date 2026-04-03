package part1.logging;

import java.io.OutputStream;
import java.io.PrintWriter;

public class StreamLogger implements Logger {

    private final PrintWriter writer;

    // Store the stream as a PrintWriter so we can easily write text lines to it
    public StreamLogger(OutputStream out) {
        this.writer = new PrintWriter(out);
    }

    @Override
    public void log(String message, LogLevel level) {
        writer.println(formatMessage(message, level));
        // makes sure the message is actually sent right away
        writer.flush();
    }
}