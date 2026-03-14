package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LinusPlayer class.
 *
 * Linus is a simple computer player that always chooses
 * the first available cell.
 */
class LinusPlayerTest {

    /**
     * Verifies that Linus chooses the first available empty cell
     * when making a move.
     */
    @Test
    void linusChoosesFirstAvailableCell() {

        Board board = new Board("""
                                XOX
                                OX-
                                ---
                                """);

        LinusPlayer linus = new LinusPlayer(Token.O);

        Position move = linus.getNextMove(board);

        Position expected = new Position(Row.Middle, Col.Right);

        assertEquals(expected, move);
    }

    /**
     * Verifies that Linus selects a position that is empty.
     */
    @Test
    void linusAlwaysReturnsEmptyCell() {

        Board board = new Board("""
                                    XO-
                                    ---
                                    ---
                                    """);
        LinusPlayer linus = new LinusPlayer(Token.O);

        Position move = linus.getNextMove(board);

        assertTrue(board.isEmptyAt(move));
    }
}

