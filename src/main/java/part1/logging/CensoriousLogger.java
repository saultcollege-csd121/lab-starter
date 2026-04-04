package part1.logging;

import java.util.List;

public class CensoriousLogger implements Logger {

    private final List<String> bannedWords;

    /**
     * Creates a logger that censors specific words
     * @param bannedWords list of words to replace with asterisks
     */
    public CensoriousLogger(List<String> bannedWords) {
        this.bannedWords = bannedWords;
    }

    /**
     * Logs the message after replacing any banned words with * symbols
     */
    @Override
    public void log(String message, LogLevel level) {
        String censored = message;
        for (String word : bannedWords) {
            // Replace each banned word with * symbols of the same length
            String stars = "*".repeat(word.length());
            // makes the replacement case-insensitive
            censored = censored.replaceAll("(?i)" + word, stars);
        }
        System.out.println(formatMessage(censored, level));
    }
}