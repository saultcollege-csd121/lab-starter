package part1.logging;

import java.util.List;
import java.util.Random;

public class InDenialLogger implements Logger{

    // A fixed list of denial random messages
    private static final List<String> DENIAL_MESSAGES = List.of(
            "Everything is fine, probably.",
            "No errors here, move along.",
            "That definitely didn't happen.",
            "I'm sure it'll sort itself out.",
            "Errors? What errors?"
    );

    @Override
    public void log(String message, LogLevel level) {
        if (level == LogLevel.ERROR) {
            // Pick a random denial message instead of showing a error
            String denial = DENIAL_MESSAGES.get(new Random().nextInt(DENIAL_MESSAGES.size()));
            System.out.println(formatMessage(denial, LogLevel.INFO));
        } else {
            System.out.println(formatMessage(message, level));
        }
    }
}
