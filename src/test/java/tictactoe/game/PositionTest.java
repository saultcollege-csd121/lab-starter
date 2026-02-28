package tictactoe.game;

import org.junit.jupiter.api.Test;
import tictactoe.ui.Console;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void parseTestNormalValues() {
        Position testPos = null;
        try {
            testPos = Position.parse("mm");
        } catch (ParseException e) {
            Console.printAlert(e.getMessage());
        }
        assertNotNull(testPos);
        assertEquals(Col.Middle, testPos.col());
        assertEquals(Row.Middle, testPos.row());

        try {
            testPos = Position.parse("MM");
        } catch (ParseException e) {
            Console.printAlert(e.getMessage());
        }
        assertNotNull(testPos);
        assertEquals(Col.Middle, testPos.col());
        assertEquals(Row.Middle, testPos.row());

        try {
            testPos = Position.parse("centre;center");
        } catch (ParseException e) {
            Console.printAlert(e.getMessage());
        }
        assertNotNull(testPos);
        assertEquals(Col.Middle, testPos.col());
        assertEquals(Row.Middle, testPos.row());

        try {
            testPos = Position.parse("1 3");
        } catch (ParseException e) {
            Console.printAlert(e.getMessage());
        }
        assertEquals(Col.Right, testPos.col());
        assertEquals(Row.Top, testPos.row());

        try {
            testPos = Position.parse("bt mid");
        } catch (ParseException e) {
            Console.printAlert(e.getMessage());
        }
        assertEquals(Col.Middle, testPos.col());
        assertEquals(Row.Bottom, testPos.row());

        try {
            testPos = Position.parse("tP,cENtRe");
        } catch (ParseException e) {
            Console.printAlert(e.getMessage());
        }
        assertEquals(Col.Middle, testPos.col());
        assertEquals(Row.Top, testPos.row());

        try {
            testPos = Position.parse("1,2");
        } catch (ParseException e) {
            Console.printAlert(e.getMessage());
        }
        assertEquals(Col.Middle, testPos.col());
        assertEquals(Row.Top, testPos.row());

        try {
            testPos = Position.parse("1;rIgHT");
        } catch (ParseException e) {
            Console.printAlert(e.getMessage());
        }
        assertEquals(Col.Right, testPos.col());
        assertEquals(Row.Top, testPos.row());

        try {
            testPos = Position.parse("tOp ; RIGht");
        } catch (ParseException e) {
            Console.printAlert(e.getMessage());
        }
        assertEquals(Col.Right, testPos.col());
        assertEquals(Row.Top, testPos.row());
    }

    @Test
    void parseTestWrongValues(){
        var testPos = assertThrows(ParseException.class, () -> Position.parse("xX"));
        assertEquals("Invalid row: x", testPos.getMessage());

        testPos = assertThrows(ParseException.class, () -> Position.parse("K                                                       WwwwwWlkn"));
        assertEquals("Invalid row: k", testPos.getMessage());

        testPos = assertThrows(ParseException.class, () -> Position.parse("00"));
        assertEquals("Invalid row: 0", testPos.getMessage());

        testPos = assertThrows(ParseException.class, () -> Position.parse(";;;,,,...   kh  hjk   jksm 0 ;; -1203-234jfndkslnf034i20-; -2983902   ; 2783u910-27he,,, ,  uhfnnn0920980-=2w3e"));
        assertEquals("Invalid position: ;;;,,,...   kh  hjk   jksm 0 ;; -1203-234jfndkslnf034i20-; -2983902   ; 2783u910-27he,,, ,  uhfnnn0920980-=2w3e", testPos.getMessage());

        testPos = assertThrows(ParseException.class, () -> Position.parse("-2300;5"));
        assertEquals("Invalid row: -2300", testPos.getMessage());

        testPos = assertThrows(ParseException.class, () -> Position.parse("505,-30606"));
        assertEquals("Invalid row: 505", testPos.getMessage());

        testPos = assertThrows(ParseException.class, () -> Position.parse("; c; lt,"));
        assertEquals("Invalid position: ; c; lt,", testPos.getMessage());

        testPos = assertThrows(ParseException.class, () -> Position.parse("2;5"));
        assertEquals("Invalid column: 5", testPos.getMessage());

        testPos = assertThrows(ParseException.class, () -> Position.parse("1;-300"));
        assertEquals("Invalid column: -300", testPos.getMessage());

        testPos = assertThrows(ParseException.class, () -> Position.parse("1;0"));
        assertEquals("Invalid column: 0", testPos.getMessage());
    }

    @Test
    void parseTestNulls(){
        // Test empty string
        var testPos = assertThrows(ParseException.class, () -> Position.parse(""));
        assertEquals("Invalid position: ", testPos.getMessage());

        // Test whitespace x2
        testPos = assertThrows(ParseException.class, () -> Position.parse("  "));
        assertEquals("Invalid position: ", testPos.getMessage());

        // Test Non-breaking space
        testPos = assertThrows(ParseException.class, () -> Position.parse("\u00A0"));
        assertEquals("Invalid position:  ", testPos.getMessage());

        // Test Zero-width space
        testPos = assertThrows(ParseException.class, () -> Position.parse("\u200B"));
        assertEquals("Invalid position: \u200B", testPos.getMessage());

        // Test null
        // Will fail, actually catches a NullPointerException
        testPos = assertThrows(ParseException.class, () -> Position.parse(null));
        assertEquals("Invalid position: null", testPos.getMessage());
    }
}