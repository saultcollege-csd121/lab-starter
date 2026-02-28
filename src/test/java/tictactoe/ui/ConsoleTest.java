package tictactoe.ui;

import org.junit.jupiter.api.Test;
import tictactoe.game.Position;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {
    @Test
    public void checkIfPromptsAreValid() {
        // Invalid Characters
        assertThrows(ParseException.class, () -> Position.parse("M h"));
        assertThrows(ParseException.class, () -> Position.parse("T G"));
        assertThrows(ParseException.class, () -> Position.parse("B V"));

        // numbers out of bounds / negative
        assertThrows(ParseException.class, () -> Position.parse("1 5"));
        assertThrows(ParseException.class, () -> Position.parse("-1 2"));
        assertThrows(ParseException.class, () -> Position.parse("90 -5"));

        // characters fit in requirements
        assertDoesNotThrow(() -> Position.parse("T M"));
        assertDoesNotThrow(() -> Position.parse("2 C"));
        assertDoesNotThrow(() -> Position.parse("M 3"));

    }

}