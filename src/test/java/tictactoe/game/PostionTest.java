package tictactoe.game;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void parsesTwoCharacterFormat() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("tl"));
        assertEquals(new Position(Row.Middle, Col.Middle), Position.parse("mc"));
        assertEquals(new Position(Row.Bottom, Col.Right), Position.parse("br"));
    }

    @Test
    void parsesSeparatedFormat() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Middle), Position.parse("1 2"));
        assertEquals(new Position(Row.Middle, Col.Left), Position.parse("middle,left"));
        assertEquals(new Position(Row.Bottom, Col.Middle), Position.parse("bottom;centre"));
    }

    @Test
    void ignoresCaseAndWhitespace() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), Position.parse(" TL "));
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("tL"));
    }

    @Test
    void throwsExceptionForInvalidInput() {
        assertThrows(ParseException.class, () -> Position.parse(""));
        assertThrows(ParseException.class, () -> Position.parse("x y"));
        assertThrows(ParseException.class, () -> Position.parse("topright"));
    }
}