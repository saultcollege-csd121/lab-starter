package tictactoe.game;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void testEmptyBoardConstructor() {
        Board b = new Board();

        assertTrue(b.isEmptyAt(new Position(Row.Top, Col.Left)));
        assertTrue(b.isEmptyAt(new Position(Row.Middle, Col.Middle)));
        assertTrue(b.isEmptyAt(new Position(Row.Bottom, Col.Right)));
    }

    @Test
    void testIsEmptyAt() {
        Board b = new Board();
        Position p = new Position(Row.Top, Col.Left);

        b.place(p, Token.X); // If X is there

        assertFalse(b.isEmptyAt(p));
    }

    @Test
    void testPlace() {
        Board b = new Board();
        b.place(new Position(Row.Top, Col.Left), Token.O);

        String boardString = b.toString(); // converts board to text
        assertTrue(boardString.contains("O"));
    }

    /*
        getWinner() tests
     */
    @Test
    void testRowWinX(){
        Board b = new Board(
            "XXX" +
            "..." +
            "..."
        );
        assertEquals(Optional.of(Token.X), b.getWinner()); // it does not return token directly. It returns an Optional<Token>
    }

    @Test
    void testColWinO() {
        Board b = new Board(
            "O.." +
            "O.." +
            "O.."
        );

        assertEquals(Optional.of(Token.O), b.getWinner());
    }

    @Test
    void testDiagonalWin1() {
        Board b = new Board(
            "X.." +
            ".X." +
            "..X"
        );
        assertEquals(Optional.of(Token.X), b.getWinner());
    }

    @Test
    void testDiagonalWin2() {
        Board b = new Board(
            "..X" +
            ".X." +
            "X.."
        );
        assertEquals(Optional.of(Token.X), b.getWinner()); // Bug: board[1][1] == board[1][0] instead of board[1][1] == board[2][0]
    }

    @Test
    void testNoWinner(){
        Board b = new Board(
            "XOX" +
            "OOX" +
            "XXO"
        );

        assertEquals(Optional.empty(), b.getWinner());
    }
}