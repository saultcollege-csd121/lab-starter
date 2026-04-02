package part1.logging;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MemoryLogger implements Logger, Exportable{

    // Stores all log messages in memory
    private final List<String> logs = new ArrayList<>();

    @Override
    public void log(String message, LogLevel level){
        // Add the formatted message to the list
        logs.add(formatMessage(message, level));
    }

    @Override
    public void exportTo(OutputStream out) {

        // PrintWriter helps write text to the output easily
        // like using println() to print each line
        PrintWriter writer = new PrintWriter(out);
        for (String entry : logs) {
            writer.println(entry);
        }
        // flush() makes sure all the text is actually sent to the stream
        // It forces all the text still waiting in memory to be actually written to the output
        writer.flush();
    }
}
