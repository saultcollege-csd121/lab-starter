package tictactoe.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.ui.Console;

import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board boardTest;
    Board boardTest1;
    Board boardTest2;
    Board boardTest3;
    Board boardTest4;
    Board boardTest5;
    Board boardTest6;
    Board boardTest7;
    Board boardTest8;

    @BeforeEach
    void setUp() {
        boardTest = new Board();
        boardTest1 = new Board("""
                    O-X
                    OX-
                    XO-
                    """);
        boardTest2 = new Board("""
                   X-O
                   XO-
                   XO-
                   """);
        boardTest3 = new Board("""
                   OXO
                   -X-
                   -XO
                   """);
        boardTest4 = new Board("""
                   OOX
                   -OX
                   --X
                   """);
        boardTest5 = new Board("""
                   XO-
                   OXO
                   --X
                   """);
        boardTest6 = new Board("""
                   XXX
                   O-O
                   -O-
                   """);
        boardTest7 = new Board("""
                   OO-
                   XXX
                   -O-
                   """);
        boardTest8 = new Board("""
                   OO-
                   -O-
                   XXX
                   """);
    }

    @Test
    void Board(){
        assertNotNull(boardTest);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    assertTrue(boardTest.isEmptyAt(Position.parse("%d%d".formatted(i, j))));
                } catch (ParseException e) {
                    Console.printAlert(e.getMessage());
                }
            }
        }
    }

    @Test
    void getWinner() {
        assertEquals(boardTest1.getWinner(), Optional.of(Token.X));
        assertEquals(boardTest2.getWinner(), Optional.of(Token.X));
        assertEquals(boardTest3.getWinner(), Optional.of(Token.X));
        assertEquals(boardTest4.getWinner(), Optional.of(Token.X));
        assertEquals(boardTest5.getWinner(), Optional.of(Token.X));
        assertEquals(boardTest6.getWinner(), Optional.of(Token.X));
        assertEquals(boardTest7.getWinner(), Optional.of(Token.X));
        assertEquals(boardTest8.getWinner(), Optional.of(Token.X));

        assertNotEquals(boardTest1.getWinner(), Optional.of(Token.O));
        assertNotEquals(boardTest2.getWinner(), Optional.of(Token.O));
        assertNotEquals(boardTest3.getWinner(), Optional.of(Token.O));
        assertNotEquals(boardTest4.getWinner(), Optional.of(Token.O));
        assertNotEquals(boardTest5.getWinner(), Optional.of(Token.O));
        assertNotEquals(boardTest6.getWinner(), Optional.of(Token.O));
        assertNotEquals(boardTest7.getWinner(), Optional.of(Token.O));
        assertNotEquals(boardTest8.getWinner(), Optional.of(Token.O));
    }

    @Test
    void isEmptyAt() {
        assertTrue(boardTest1.isEmptyAt(new Position(Row.Top,Col.Middle)));
        assertFalse(boardTest6.isEmptyAt(new Position(Row.Bottom, Col.Middle)));
        assertTrue(boardTest6.isEmptyAt(new Position(Row.Middle, Col.Middle)));
        assertFalse(boardTest4.isEmptyAt(new Position(Row.Top, Col.Right)));
    }

    @Test
    void place() {
        boardTest.place(new Position(Row.Top, Col.Middle), Token.X);
        assertEquals(".X.\n...\n...\n", boardTest.toString());
        boardTest.place(new Position(Row.Bottom, Col.Left), Token.O);
        assertEquals(".X.\n...\nO..\n", boardTest.toString());
        boardTest.place(new Position(Row.Top, Col.Left), Token.X);
        assertEquals("XX.\n...\nO..\n", boardTest.toString());
        boardTest.place(new Position(Row.Top, Col.Right), Token.O);
        assertEquals("XXO\n...\nO..\n", boardTest.toString());
    }
}