package part1;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import part1.logging.Exportable;
import part1.logging.LogLevel;
import part1.logging.Logger;

import java.io.OutputStream;
import java.time.Instant;

public class ColorLogger implements Logger {
    @Override
    public void log(String message, LogLevel level) {
        if (level == LogLevel.INFO) {
            System.out.println(Ansi.colorize(Instant.now().toString() + " [" + level + "] " + message, Attribute.GREEN_TEXT())); // make green
        } else if (level == LogLevel.WARNING) {
            System.out.println(Ansi.colorize(Instant.now().toString() + " [" + level + "] " + message, Attribute.YELLOW_TEXT())); // make orange
        } else if (level == LogLevel.ERROR) {
            System.out.println(Ansi.colorize(Instant.now().toString() + " [" + level + "] " + message, Attribute.RED_TEXT())); // make red
        } else
            System.out.println(Instant.now().toString() + " [" + level + "] " + message);
    }


    public static void export(Exportable exporter, OutputStream stream) {
        exporter.exportTo(stream);
    }
}