package part1.logging;

import java.util.List;

/**
 * A logger that replaces specific words with a string of ‘*’ symbols of the same length as the censored word.
 */
public class CensoriousLogger implements Logger {

    private final List<String> bannedWords; //stores the list of hidden words in a constructor

    /**
     * Creates a CensoriousLogger with the given list of banned words.
     * @param bannedWords the words to sensor inside a list
     */
    public CensoriousLogger(List<String> bannedWords) {
        this.bannedWords = bannedWords; //we customize the constructor to tell it which words to censor.
    }

    /**
     * Logs a message after replacing the banned words with asterisks with the same length as the censored word.
     * The replacement is case-insensitive.
     * @param message the message to log
     * @param level the severity level of each message
     */
    @Override
    public void log(String message, LogLevel level) {
        for (String word : bannedWords) { //loop through every banned word and replace it in the message.
            message = message.replaceAll("(?i)" + word, "*".repeat(word.length())); //"(?i)" makes the replacement case-insensitive.
        }                                                     // "*".repeat(word.length()) creates the right number of * symbols.
        System.out.println(formatMessage(message, level));
    }
}