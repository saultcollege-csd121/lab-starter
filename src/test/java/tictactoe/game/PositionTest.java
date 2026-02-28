package tictactoe.game;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    /* =========================================================
VALID INPUTS — two-character format
========================================================= */

    @Test
    void parse_twoCharacterAbbreviations_work() throws ParseException, ParseException {
        Position p = Position.parse("tl");

        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void parse_twoCharacterNumericFormat_work() throws ParseException {
        Position p = Position.parse("11");

        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }


    @Test
    void parse_twoCharacter_middleCenter() throws ParseException {
        Position p = Position.parse("mc");

        assertEquals(Row.Middle, p.row());
        assertEquals(Col.Middle, p.col());

    }


    /* =========================================================
VALID INPUTS — separated formats
========================================================= */

    @Test
    void parse_spaceSeparatedWords_work() throws ParseException {
        Position p = Position.parse("top left");

        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void parse_commaSeparatedWords_work() throws ParseException {
        Position p = Position.parse("middle,center");

        assertEquals(Row.Middle, p.row());
        assertEquals(Col.Middle, p.col());
    }

    @Test
    void parse_semicolonSeparatedWords_work() throws ParseException {
        Position p = Position.parse("bottom;right");

        assertEquals(Row.Bottom, p.row());
        assertEquals(Col.Right, p.col());
    }
    @Test
    void parse_numericSeparatedFormat_work() throws ParseException {
        Position p = Position.parse("1 3");

        assertEquals(Row.Top, p.row());
        assertEquals(Col.Right, p.col());
    }
/* =========================================================
CASE INSENSITIVITY & WHITESPACE
========================================================= */
@Test
void parse_isCaseInsensitive() throws ParseException {
    Position p = Position.parse("TL");

    assertEquals(Row.Top, p.row());
    assertEquals(Col.Left, p.col());
}

    @Test
    void parse_ignoresExtraSpaces() throws ParseException {
        Position p = Position.parse(" middle ; center ");

        assertEquals(Row.Middle, p.row());
        assertEquals(Col.Middle, p.col());
    }

    /* =========================================================
INVALID INPUTS
========================================================= */

    @Test
    void parse_emptyString_throwsParseException() {
        assertThrows(ParseException.class,
                () -> Position.parse(""));
    }

    @Test
    void parse_singleCharacter_throwsParseException() {
        assertThrows(ParseException.class,
                () -> Position.parse("t"));
    }

    @Test
    void parse_tooManyParts_throwsParseException() {
        assertThrows(ParseException.class,
                () -> Position.parse("top left right"));
    }
    @Test
    void parse_invalidRow_throwsParseException() {
        assertThrows(ParseException.class,
                () -> Position.parse("x left"));
    }

    @Test
    void parse_invalidColumn_throwsParseException() {
        assertThrows(ParseException.class,
                () -> Position.parse("top x"));
    }

    @Test
    void parse_onlySeparators_throwsParseException() {
        assertThrows(ParseException.class,
                () -> Position.parse(" , ; "));
    }
}