package tictactoe.game;

import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testEmptyConstructor() {
        Board board = new Board();
        for (Row r : Row.values()) {
            for (Col c : Col.values()) {
                assertTrue(board.isEmptyAt(new Position(r, c)));
            }
        }
    }

    @Test
    void testPlaceAndIsEmptyAt() {
        Board board = new Board();
        Position pos = new Position(Row.Top, Col.Left);
        assertTrue(board.isEmptyAt(pos));

        board.place(pos, Token.X);
        assertFalse(board.isEmptyAt(pos));
    }

    @Test
    void testGetWinnerRows() {

        Board board = new Board("XXX......");
        assertEquals(Optional.of(Token.X), board.getWinner());


        board = new Board("...OOO...");
        assertEquals(Optional.of(Token.O), board.getWinner());


        board = new Board("......XXX");
        assertEquals(Optional.of(Token.X), board.getWinner());
    }

    @Test
    void testGetWinnerColumns() {

        Board board = new Board("O..O..O..");
        assertEquals(Optional.of(Token.O), board.getWinner());


        board = new Board(".X..X..X.");
        assertEquals(Optional.of(Token.X), board.getWinner());


        board = new Board("..O..O..O");
        assertEquals(Optional.of(Token.O), board.getWinner());
    }

    @Test
    void testGetWinnerDiagonals() {

        Board board = new Board("X...X...X");
        assertEquals(Optional.of(Token.X), board.getWinner());


        board = new Board("..X.X.X..");
        assertEquals(Optional.of(Token.X), board.getWinner());
    }

    @Test
    void testNoWinnerDraw() {
        Board board = new Board("XOXOXOOXO");
        assertTrue(board.isFull());
        assertEquals(Optional.empty(), board.getWinner());
    }
}