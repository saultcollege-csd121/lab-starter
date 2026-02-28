package tictactoe.game;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

/* =========================================================
       4.1 Parameterless Board constructor
       ========================================================= */

    @Test
    void constructor_createsEmptyBoard() {
        Board board = new Board();

        assertTrue(board.isEmptyAt(new Position(Row.Top, Col.Left)));
        assertTrue(board.isEmptyAt(new Position(Row.Middle, Col.Middle)));
        assertTrue(board.isEmptyAt(new Position(Row.Bottom, Col.Right)));
    }
/* =========================================================
       4.2 isEmptyAt
       ========================================================= */
    @Test
    void isEmptyAt_returnsTrueForEmptyPosition() {
        Board board = new Board();
        assertTrue(board.isEmptyAt(new Position(Row.Top, Col.Right)));
    }

    @Test
    void isEmptyAt_returnsFalseAfterPlace() {
        Board board = new Board();
        Position pos = new Position(Row.Middle, Col.Left);

        board.place(pos, Token.X);

        assertFalse(board.isEmptyAt(pos));
    }

    /* =========================================================
       4.3 place
       ========================================================= */

    @Test
    void place_putsTokenAtCorrectPosition() {
        Board board = new Board();
        Position pos = new Position(Row.Bottom, Col.Middle);

        board.place(pos, Token.O);

        assertFalse(board.isEmptyAt(pos));
    }
/* =========================================================
       4.4 getWinner — ALL WIN CONDITIONS
       ========================================================= */

    // ----- ROW WINS -----

    @Test
    void getWinner_detectsTopRowWin() {
        Board board = new Board("XXX......");

        assertEquals(Optional.of(Token.X), board.getWinner());
    }

    @Test
    void getWinner_detectsMiddleRowWin() {
        Board board = new Board("...OOO...");

        assertEquals(Optional.of(Token.O), board.getWinner());
    }

    @Test
    void getWinner_detectsBottomRowWin() {
        Board board = new Board("......XXX");

        assertEquals(Optional.of(Token.X), board.getWinner());
    }
    // ----- COLUMN WINS -----

    @Test
    void getWinner_detectsLeftColumnWin() {
        Board board = new Board("X..X..X..");

        assertEquals(Optional.of(Token.X), board.getWinner());
    }
    @Test
    void getWinner_detectsMiddleColumnWin() {
        Board board = new Board(".O..O..O.");

        assertEquals(Optional.of(Token.O), board.getWinner());
    }

    @Test
    void getWinner_detectsRightColumnWin() {
        Board board = new Board("..X..X..X");

        assertEquals(Optional.of(Token.X), board.getWinner());
    }

    // ----- DIAGONAL WINS -----

    @Test
    void getWinner_detectsMainDiagonalWin() {
        Board board = new Board("X...X...X");

        assertEquals(Optional.of(Token.X), board.getWinner());
    }
    @Test
    void getWinner_detectsAntiDiagonalWin() {
        Board board = new Board("..O.O.O..");

        assertEquals(Optional.of(Token.O), board.getWinner());
    }

}