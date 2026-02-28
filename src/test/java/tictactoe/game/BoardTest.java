package tictactoe.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    void testBoardConstructor() {
        var board = new Board();
        assertTrue(board.isEmptyAt(new Position(Row.Top, Col.Left)));
        assertTrue(board.isEmptyAt(new Position(Row.Top, Col.Middle)));
        assertTrue(board.isEmptyAt(new Position(Row.Top, Col.Right)));
        assertTrue(board.isEmptyAt(new Position(Row.Middle, Col.Left)));
        assertTrue(board.isEmptyAt(new Position(Row.Middle, Col.Middle)));
        assertTrue(board.isEmptyAt(new Position(Row.Middle, Col.Right)));
        assertTrue(board.isEmptyAt(new Position(Row.Bottom, Col.Left)));
        assertTrue(board.isEmptyAt(new Position(Row.Bottom, Col.Middle)));
        assertTrue(board.isEmptyAt(new Position(Row.Bottom, Col.Right)));
    }
    @Test
    void testIsEmptyAt() {
        var board = new Board();
        var position = new Position(Row.Top, Col.Left);
        assertTrue(board.isEmptyAt(position)); // empty
        board.place(position, Token.O);
        assertFalse(board.isEmptyAt(position)); // not empty
    }
    @Test
    void testPlaceAt() {
        var board = new Board();
        var position = new Position(Row.Top, Col.Left);
        board.place(position, Token.O);
        assertFalse(board.isEmptyAt(position));
        assertTrue(board.isEmptyAt(new Position(Row.Bottom, Col.Middle)));
        assertTrue(board.isEmptyAt(new Position(Row.Middle, Col.Right)));
    }

    @Test
    void testRowWin() {
        Board boardTopX = new Board("XXX"
                + "..."
                + "...");
        Board boardMiddleX = new Board("..."
                + "XXX"
                + "...");
        Board boardBottomX = new Board("..."
                + "..."
                + "XXX");
        assertEquals(Optional.of(Token.X), boardTopX.getWinner());
        assertEquals(Optional.of(Token.X), boardMiddleX.getWinner());
        assertEquals(Optional.of(Token.X), boardBottomX.getWinner());

        Board boardTopO = new Board("OOO"
                + "..."
                + "...");
        Board boardMiddleO = new Board("..."
                + "OOO"
                + "...");
        Board boardBottomO = new Board("..."
                + "..."
                + "OOO");
        assertEquals(Optional.of(Token.O), boardTopO.getWinner());
        assertEquals(Optional.of(Token.O), boardMiddleO.getWinner());
        assertEquals(Optional.of(Token.O), boardBottomO.getWinner());
    }
    @Test
    void testColumnWin() {
        Board boardLeftX = new Board("X.."
                + "X.."
                + "X..");
        Board boardMiddleX = new Board(".X."
                + ".X."
                + ".X.");
        Board boardRightX = new Board("..X"
                + "..X"
                + "..X");
        assertEquals(Optional.of(Token.X), boardLeftX.getWinner());
        assertEquals(Optional.of(Token.X), boardMiddleX.getWinner());
        assertEquals(Optional.of(Token.X), boardRightX.getWinner());
        Board boardLeftO = new Board("O.."
                + "O.."
                + "O..");
        Board boardMiddleO = new Board(".O."
                + ".O."
                + ".O.");
        Board boardRightO = new Board("..O"
                + "..O"
                + "..O");
        assertEquals(Optional.of(Token.O), boardLeftO.getWinner());
        assertEquals(Optional.of(Token.O), boardMiddleO.getWinner());
        assertEquals(Optional.of(Token.O), boardRightO.getWinner());
    }
    @Test
    void testDiagonalWin() {
        Board boardDiagonalLeftToRightX = new Board("X.."
                + ".X."
                + "..X");
        Board boardDiagonalRightToLeftX = new Board("..X"
                + ".X."
                + "X..");
        assertEquals(Optional.of(Token.X), boardDiagonalLeftToRightX.getWinner());
        assertEquals(Optional.of(Token.X), boardDiagonalRightToLeftX.getWinner());

        Board boardDiagonalLeftToRightO = new Board("O.."
                + ".O."
                + "..O");
        Board boardDiagonalRightToLeftO = new Board("..O"
                + ".O."
                + "O..");
        assertEquals(Optional.of(Token.O), boardDiagonalLeftToRightO.getWinner());
        assertEquals(Optional.of(Token.O), boardDiagonalRightToLeftO.getWinner());
    }
    @Test
    void testFullBoardTie() {
        Board fullBoard = new Board("XOO"
                + "OOX"
                + "XXO");
        assertEquals(Optional.empty(), fullBoard.getWinner());
    }
    @Test
    void testNoWinner() {
        assertEquals(Optional.empty(), new Board().getWinner());
    }
}