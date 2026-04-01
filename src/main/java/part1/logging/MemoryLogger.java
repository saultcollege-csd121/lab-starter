package part1.logging;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;

public class MemoryLogger implements Logger, Exportable {
    ArrayList<String> errorMsgs = new ArrayList<String>();

    public void log(String message, LogLevel level) {
        String ErrorMsg = (Instant.now().toString() + "[" + level + "]" + message);
        errorMsgs.add(ErrorMsg);
    }
    public void exportTo(OutputStream out){
        PrintWriter printer = new PrintWriter(out);
        printer.println(errorMsgs);
    };
}
