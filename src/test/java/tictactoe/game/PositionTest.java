package tictactoe.game;

import org.junit.jupiter.api.Test;


import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void testNumbers() throws ParseException {

        Position pos = (Position.parse("12"));
        assertSame(Row.Top, pos.row(), "string 12 row should be parsed as Top");
        assertSame(Col.Middle, pos.col(), "string 12 col should be parsed as Middle");

        Position pos_2 = (Position.parse("2,3"));
        assertSame(Row.Middle, pos_2.row(), "row for String 2,3 should be parsed as Middle");
        assertSame(Col.Right, pos_2.col(), "column for string 2,3 should be parsed as Right");

    }

    @Test
    public void testInputPosition() throws ParseException {
        Position middleLeft = new Position(Row.Middle,Col.Left);

        assertEquals(Position.parse("ml"), middleLeft,"mL Should be parsed as Middle Left");
        assertEquals(Position.parse("mL"), middleLeft, "mL should be parsed as Middle Left");
        assertEquals(Position.parse("ML"),middleLeft, " ML should be parsed as Middle Left");
        assertEquals(Position.parse("Middle Left"),middleLeft,"Middle Left should be parsed as Middle Left");
        assertEquals(Position.parse("MiDDle LeFT"), middleLeft,"MiDDle LeFT should be parsed as Middle Left");

    }
//I hate Tests, but it's the easiest lab so far (Subatomic marks here I come)
   // @Test
   // public void

}
