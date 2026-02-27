package tictactoe.game;

import org.junit.jupiter.api.Test;

import javax.management.loading.ClassLoaderRepository;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    /*
    VALID INPUTS
     */
    @Test
    void testTwoCharacterPosition() throws ParseException {
        Position p = Position.parse("tl");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());

        p = Position.parse("11");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());

        p = Position.parse("bm");
        assertEquals(Row.Bottom, p.row());
        assertEquals(Col.Middle, p.col());
    }

    @Test
    void testSeparatedFormats() throws ParseException {
        Position p = Position.parse("top,left");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());

        p = Position.parse("middle;middle");
        assertEquals(Row.Middle, p.row());
        assertEquals(Col.Middle, p.col());

        p = Position.parse("1 3");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Right, p.col());
    }

    /*
    EDGE CASES
     */
    @Test
    void tesCaseInsensitive() throws ParseException {
        Position p = Position.parse("TL");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());

        p = Position.parse("Top,Left");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void testExtraSpaces() throws ParseException {
        Position p = Position.parse("   top   ,   right   ");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Right, p.col());

        p = Position.parse(" 2 3 ");
        assertEquals(Row.Middle, p.row());
        assertEquals(Col.Right, p.col());

    }

    /*
    INVALID INPUTS
     */
    @Test
    void testTooShortStringException() {
        assertThrows(ParseException.class, () -> Position.parse("a"));
    }

    @Test
    void testInvalidFormatException() {
        assertThrows(ParseException.class, () -> Position.parse("top,left,right"));

        assertThrows(ParseException.class, () -> Position.parse("top|left"));
    }

    @Test
    void testInvalidRowException() {
        assertThrows(ParseException.class, () -> Position.parse("foo,left"));
    }

    @Test
    void testInvalidColException() {
       assertThrows(ParseException.class, () -> Position.parse("top,banana"));
    }

    @Test
    void testEmptyStringException() {
        assertThrows(ParseException.class, () -> Position.parse(""));
        assertThrows(ParseException.class, () -> Position.parse("   "));
    }
}