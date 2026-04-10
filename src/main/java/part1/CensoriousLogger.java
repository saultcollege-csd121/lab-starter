package part1;

import part1.logging.LogLevel;
import part1.logging.Logger;

import java.util.List;

public class CensoriousLogger implements Logger {

    private final List<String> bannedWords;

    public CensoriousLogger(List<String> bannedWords) {
        this.bannedWords = bannedWords;
    }


    @Override
    public void log(String message, LogLevel level) {
        for (String word : bannedWords) {
            message = message.replace(word, "*".repeat(word.length()));
        }

        System.out.println(message);
    }
}