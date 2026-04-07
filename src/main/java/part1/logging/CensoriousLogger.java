package part1.logging;

import part1.util.Messages;

import java.util.List;

public class CensoriousLogger implements Logger {
    private final List<String> censorThese;

    public CensoriousLogger(List<String> censorThese){
        this.censorThese = censorThese;
    }
    @Override
    public void log(String message, LogLevel level) {
        message = formatMessage(message, level);
        for (String word : censorThese) {
            message = message.replaceAll("\\b" + word + "\\b", "*".repeat(word.length()));
        }
        IO.println(message);
    }



}
