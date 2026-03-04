package tictactoe.game;

import org.junit.jupiter.api.Test;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void parse_twoCharacterFormat() throws Exception {
        Position p = Position.parse("tl"); // shorterm: t=top, l=left

        assertEquals(Row.Top, p.row()); //row must be Top
        assertEquals(Col.Left, p.col()); //col must be Left
    }

    @Test
    void parse_withSeparators() throws Exception {
        Position p1 = Position.parse("top,left"); //full words separated by comma
        Position p2 = Position.parse("middle;center"); //separated by semicolon
        Position p3 = Position.parse("1 3"); //numbers separated by space

        assertEquals(Row.Top, p1.row());
        assertEquals(Col.Left, p1.col());
        assertEquals(Row.Middle, p2.row());
        assertEquals(Col.Middle, p2.col()); //"center" should map to Middle col
        assertEquals(Row.Top, p3.row()); //1 = Top row
        assertEquals(Col.Right, p3.col()); //3 = Right col
    }

    @Test
    void parse_invalidRow_Exception() {
        assertThrows(ParseException.class, () -> {
            Position.parse("invalid,left"); //"invalid" is not a known row token
        });
    }

    @Test
    void parse_invalidFormat_Exception() {
        assertThrows(ParseException.class, () -> {
            Position.parse("top-left-right");// three parts instead of two, ambiguous
        });
    }

    @Test
    void parse_isCaseInsensitive() throws Exception {
        Position p = Position.parse("TL"); //checking if uppercase shorthand still works
        assertEquals(Row.Top, p.row());
        assertEquals(Col.Left, p.col());
    }

    @Test
    void parse_tooShort_Exception() {
        assertThrows(ParseException.class, () -> {
            Position.parse("t"); //single char
        });
    }
}

