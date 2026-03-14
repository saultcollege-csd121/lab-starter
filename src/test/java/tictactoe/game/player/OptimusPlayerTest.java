package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the OptimusPlayer class.
 *
 * Optimus is an advanced computer player that uses the minimax
 * algorithm to determine the optimal move.
 *
 * This text verifies that Optimus always win or draw.
 */
class OptimusPlayerTest {

    /**
     * Verifies that Optimus selects a winning move every time it can do it.
     */
    @Test
    void optimusTakesWinningMove() {

        Board board = new Board("""
                                    XX-
                                    OO-
                                    ---
                                    """);

        OptimusPlayer optimus = new OptimusPlayer(Token.X);
        Position move = optimus.getNextMove(board);
        Position winningMove = new Position(Row.Top, Col.Right);
        assertEquals(winningMove, move);
    }

    /**
     * Verifies that Optimus blocks the opponent when they are close to win.
     */
    @Test
    void optimusBlocksOpponentWin() {

        Board board = new Board("""
                                    OO-
                                    X--
                                    ---
                                    """);

        OptimusPlayer optimus = new OptimusPlayer(Token.X);
        Position move = optimus.getNextMove(board);
        Position blockingMove = new Position(Row.Top, Col.Right);
        assertEquals(blockingMove, move);
    }

    /**
     * Verifies that Optimus always returns a position that is empty.
     */
    @Test
    void optimusReturnsEmptyCell() {

        Board board = new Board("""
                                    XO-
                                    ---
                                    ---
                                    """);

        OptimusPlayer optimus = new OptimusPlayer(Token.O);
        Position move = optimus.getNextMove(board);
        assertTrue(board.isEmptyAt(move));
    }

    /**
     * Verifies that Optimus selects a valid position.
     */
    @Test
    void optimusChoosesValidMoveOnEmptyBoard() {

        Board board = new Board();
        OptimusPlayer optimus = new OptimusPlayer(Token.X);
        Position move = optimus.getNextMove(board);
        assertTrue(board.isEmptyAt(move));
    }

    /**
     * Verifies that Optimus returns a valid move when only one cell remains.
     */
    @Test
    void optimusPlaysLastAvailableMove() {

        Board board = new Board("""
                                    XOX
                                    XXO
                                    OX-
                                    """);

        OptimusPlayer optimus = new OptimusPlayer(Token.O);
        Position move = optimus.getNextMove(board);
        Position lastCell = new Position(Row.Bottom, Col.Right);
        assertEquals(lastCell, move);
    }
}