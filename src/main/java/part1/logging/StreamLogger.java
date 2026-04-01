package part1.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;

public class StreamLogger implements Logger{
    @Override
    public void log(String message, LogLevel level) {
    }


    public void formatMessage(String message, LogLevel level, OutputStream Stream) throws IOException {
        String byteMessage = (Instant.now().toString() + "[" + level + "]" + message);
        byte[] messageBytes = byteMessage.getBytes();
        Stream.write(messageBytes);
    }
    // I am making sacrifices hoping this is right. It feels to simple??? But I looked online and on of the sites
    // YOU gave us to look at, and it says the write function! and i formatted it how I am supposed to for that function!
    // but still....four lines of code? REALLY? I don't believe it. and i'm scared. but even after looking around for an hour
    // i don't see any OTHER way to send the message to the ouput stream. i write() it!! that's the only way right????
    // uuuuuuuuuuuuuuuuuuuuuuuuuggggggggggggggggggggggggggggggggggghhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
}
