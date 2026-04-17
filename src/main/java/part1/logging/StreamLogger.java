package part1.logging;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class StreamLogger implements Logger {

    PrintWriter stream;

    public StreamLogger(OutputStream out) {

        this.stream = new PrintWriter(out,true);

    }

    @Override
    public void log(String message, LogLevel level) {




           this.stream.println(formatMessage(message, level));



    }
}
