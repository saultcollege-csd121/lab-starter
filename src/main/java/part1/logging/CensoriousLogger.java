package part1.logging;

import java.util.List;

public class CensoriousLogger implements Logger {

    private List<String> badWords;

    public CensoriousLogger(List<String> badWords) {
        this.badWords = badWords;
    }

    @Override
    public void log(String message, LogLevel level) {
        String newMessage = message;

        for (String word : badWords) {
            String stars = "*".repeat(word.length());
            newMessage = newMessage.replace(word, stars);
        }

        System.out.println(formatMessage(newMessage, level));
    }
}