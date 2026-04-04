package part1.logging;

import java.io.OutputStream;
import java.io.PrintWriter;

public class StreamLogger implements Logger {

    private final PrintWriter writer;

    /**
     * Creates a logger that writes to the given output stream
     * @param out the stream to write log messages to System.out or a file
     */
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