package part1.logging;

public class LoudLogger implements Logger {

    @Override
    public void log(String message, LogLevel level){

        String loudMessage = message.toUpperCase().replace(".","!!!");

        System.out.println(formatMessage(loudMessage,level));
    }

}
