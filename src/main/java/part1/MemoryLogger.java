package part1;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import part1.logging.Exportable;
import part1.logging.LogLevel;
import part1.logging.Logger;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MemoryLogger implements Logger, Exportable {

    private List<String> memory = new ArrayList<>();

    @Override
    public void log(String message, LogLevel level) {
        memory.add(message);
    }

    @Override
    public void exportTo(OutputStream out) {
        PrintWriter writer = new PrintWriter(out);

        for (String message : memory) {
            writer.println(message);
        }

        writer.flush();
    }
}
