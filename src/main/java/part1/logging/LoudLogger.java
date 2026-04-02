package part1.logging;

import java.time.Instant;

public class LoudLogger implements Logger{
    @Override
    public void log(String message, LogLevel level){
        String loudMessage = message.toUpperCase();
        //  then replace periods with !!!
        // The first parameter is what will be replaced, the second parameter is what it will be replaced with
        loudMessage = loudMessage.replace(".", "!!!");
        System.out.println(formatMessage(loudMessage, level));
    }
}
