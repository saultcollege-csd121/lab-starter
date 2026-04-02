package part1.logging;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MemoryLogger implements Logger,Exportable{

    private final List <String> messages = new ArrayList<>();

    @Override
    public void log(String message, LogLevel level) {

        String formatted = formatMessage(message, level);
        messages.add(formatted);


    }

    @Override
    public void exportTo(OutputStream out) {
        PrintWriter writer = new PrintWriter(out);


        for (String msg : messages){
            writer.println(msg);
        }

        writer.flush();



    }

}
