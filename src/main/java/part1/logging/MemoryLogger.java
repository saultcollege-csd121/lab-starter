package part1.logging;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MemoryLogger implements Logger, Exportable {

    private List<String> messages = new ArrayList<>();

    @Override
    public void log(String message, LogLevel level) {
        messages.add(formatMessage(message, level));
    }

    @Override
    public void exportTo(OutputStream out) {
        PrintWriter writer = new PrintWriter(out, true);

        for (String message : messages) {
            writer.println(message);
        }
    }
}