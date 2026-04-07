package part1.logging;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MemoryLogger implements Logger, Exportable {
    private List<String> outputLog = new ArrayList<>();

    @Override
    public void exportTo(OutputStream out) {
        PrintWriter wrapper = new PrintWriter(out);
        for (String s: outputLog) {
            wrapper.println(s);
        }
        wrapper.flush();
    }

    @Override
    public void log(String message, LogLevel level) {
        message = formatMessage(message, level);
        outputLog.add(message);
    }
}
