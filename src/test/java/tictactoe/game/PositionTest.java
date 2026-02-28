package tictactoe.game;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static tictactoe.game.Position.parse;

class TestPosition {

    @Test
    public void testTwoCharacterPos() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), parse("tl"));
        assertEquals(new Position(Row.Top, Col.Middle), parse("tm"));
        assertEquals(new Position(Row.Top, Col.Right), parse("tr"));
        assertEquals(new Position(Row.Middle, Col.Left), parse("ml"));
        assertEquals(new Position(Row.Middle, Col.Middle), parse("mc"));
        assertEquals(new Position(Row.Middle, Col.Right), parse("mr"));
        assertEquals(new Position(Row.Bottom, Col.Left), parse("bl"));
        assertEquals(new Position(Row.Bottom, Col.Middle), parse("bm"));
        assertEquals(new Position(Row.Bottom, Col.Right), parse("br"));
    }
    @Test
    public void testNumberPosition() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), parse("11"));
        assertEquals(new Position(Row.Top, Col.Middle), parse("12"));
        assertEquals(new Position(Row.Top, Col.Right), parse("13"));
        assertEquals(new Position(Row.Middle, Col.Left), parse("21"));
        assertEquals(new Position(Row.Middle, Col.Middle), parse("22"));
        assertEquals(new Position(Row.Middle, Col.Right), parse("23"));
        assertEquals(new Position(Row.Bottom, Col.Left), parse("31"));
        assertEquals(new Position(Row.Bottom, Col.Middle), parse("32"));
        assertEquals(new Position(Row.Bottom, Col.Right), parse("33"));
    }
    @Test
    public void testCommaSeparation() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("top,left"));
        assertEquals(new Position(Row.Bottom, Col.Right), Position.parse("bottom,right"));
    }
    @Test
    public void testSemicolonSeparation() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("top;left"));
        assertEquals(new Position(Row.Bottom, Col.Right), Position.parse("bottom;right"));
    }
    @Test
    public void testSpaceSeparation() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), parse("top left"));
        assertEquals(new Position(Row.Bottom, Col.Right), parse("bottom right"));
    }
    @Test
    public void testCaseInsensitive() throws ParseException {
        assertEquals(parse("tl"), parse("TL"));
        assertEquals(parse("tl"), parse("Tl"));
        assertEquals(parse("tl"), parse("tL"));
        assertEquals(parse("top, left"), parse("TOP, LEFT"));
        assertEquals(parse("top, left"), parse("Top, Left"));
    }
    @Test
    public void testExtraSpaceSeparation() throws ParseException {
        assertEquals(parse("tr"), parse("  tr  "));
        assertEquals(parse("tr"), parse("top  ,  right"));
        assertEquals(parse("tr"), parse("  top  ,  right  "));
    }

    @Test
    public void testEmptyString() {
        assertThrows(ParseException.class, () -> parse(""));
    }
    @Test
    public void testStringTooShort() {
        assertThrows(ParseException.class, () -> parse("t"));
    }
    @Test
    public void testStringTooLong() {
        assertThrows(ParseException.class, () -> parse("top,right,pickle"));
    }
    @Test
    public void testRowColSwap() {
        assertThrows(ParseException.class, () -> parse("left, bottom"));
        assertThrows(ParseException.class, () -> parse("right, top"));
        assertThrows(ParseException.class, () -> parse("left, middle"));
    }
    @Test
    public void testWrongRowColInput() {
        assertThrows(ParseException.class, () -> parse("lasagna, pizza")); // im hungry LOL
        assertThrows(ParseException.class, () -> parse("xx"));
    }
    @Test
    public void testWrongSeparator() {
        assertThrows(ParseException.class, () -> parse("top.left"));
        assertThrows(ParseException.class, () -> parse("top|left"));
        assertThrows(ParseException.class, () -> parse("top-left"));

    }
}