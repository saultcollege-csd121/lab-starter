package tictactoe.game;

import org.junit.jupiter.api.Test;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void parse_twoChars_letters_topLeft() throws ParseException {
        Position p = Position.parse("tl");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void parse_twoChars_numbers_topLeft() throws ParseException {
        Position p = Position.parse("11");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void parse_twoChars_caseInsensitive() throws ParseException {
        Position p = Position.parse("tL");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void parse_twoChars_middleMiddle() throws ParseException {
        Position p = Position.parse("22");
        assertEquals(Row.Middle, p.row());
        assertEquals(Col.Middle, p.col());
    }

    @Test
    void parse_commaSeparated_words() throws ParseException {
        Position p = Position.parse("top,left");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void parse_semicolonSeparated_words() throws ParseException {
        Position p = Position.parse("middle;center");
        assertEquals(Row.Middle, p.row());
        assertEquals(Col.Middle, p.col());
    }

    @Test
    void parse_spaceSeparated_numbers() throws ParseException {
        Position p = Position.parse("1 3");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Right, p.col());
    }

    @Test
    void parse_extraSpaces_allowed() throws ParseException {
        Position p = Position.parse("   top    ,   left   ");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void parse_multipleSeparators_allowed() throws ParseException {
        Position p = Position.parse("top;;;left");
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void parse_empty_throwsParseException() {
        assertThrows(ParseException.class, () -> Position.parse(""));
    }

    @Test
    void parse_tooShort_throwsParseException() {
        assertThrows(ParseException.class, () -> Position.parse("t"));
    }

    @Test
    void parse_wrongNumberOfParts_throwsParseException() {
        assertThrows(ParseException.class, () -> Position.parse("top,left,right"));
    }

    @Test
    void parse_invalidRow_throwsParseException() {
        assertThrows(ParseException.class, () -> Position.parse("x1"));
    }

    @Test
    void parse_invalidCol_throwsParseException() {
        assertThrows(ParseException.class, () -> Position.parse("1x"));
    }

    @Test
    void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Position.parse(null));
    }
}