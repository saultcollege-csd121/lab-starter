package tictactoe.game;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    // Constructor Tests

    @Test
    void constructor_createsEmptyBoard() {
        Board board = new Board();

        assertTrue(board.isEmptyAt(new Position(Row.Top, Col.Left)));
        assertTrue(board.isEmptyAt(new Position(Row.Middle, Col.Middle)));
        assertTrue(board.isEmptyAt(new Position(Row.Bottom, Col.Right)));
    }

    // isEmptyAt Tests

    @Test
    void isEmptyAt_returnsTrue_whenEmpty() {
        Board board = new Board();
        assertTrue(board.isEmptyAt(new Position(Row.Top, Col.Left)));
    }

    @Test
    void isEmptyAt_returnsFalse_afterPlace() {
        Board board = new Board();
        Position p = new Position(Row.Top, Col.Left);

        board.place(p, Token.X);

        assertFalse(board.isEmptyAt(p));
    }

    // place Tests

    @Test
    void place_putsTokenOnBoard() {
        Board board = new Board();
        Position p = new Position(Row.Middle, Col.Middle);

        board.place(p, Token.O);

        assertFalse(board.isEmptyAt(p));
    }

    @Test
    void place_doesNotChangeOtherCells() {
        Board board = new Board();
        Position p = new Position(Row.Middle, Col.Middle);

        board.place(p, Token.X);

        assertTrue(board.isEmptyAt(new Position(Row.Top, Col.Left)));
    }

    // getWinner Tests


    @Test
    void getWinner_rowWin_X() {
        Board board = new Board("XXX......");
        assertEquals(Optional.of(Token.X), board.getWinner());
    }

    @Test
    void getWinner_columnWin_X() {
        Board board = new Board("X..X..X..");
        assertEquals(Optional.of(Token.X), board.getWinner());
    }

    @Test
    void getWinner_diagonalWin_X() {
        Board board = new Board("X...X...X");
        assertEquals(Optional.of(Token.X), board.getWinner());
    }

    @Test
    void getWinner_otherDiagonalWin_X() {
        Board board = new Board("..X.X.X..");
        assertEquals(Optional.of(Token.X), board.getWinner());
    }

    @Test
    void getWinner_noWinner_isEmpty() {
        Board board = new Board("XOXOXOOXO");
        assertTrue(board.getWinner().isEmpty());
    }
}