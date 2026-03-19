package tictactoe.game.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;
class LinusTest {

    private static final Position topLeft  = new Position(Row.Top, Col.Left);
    private static final Position topMid   = new Position(Row.Top, Col.Middle);
    private static final Position topRight = new Position(Row.Top, Col.Right);
    private static final Position midLeft  = new Position(Row.Middle, Col.Left);
    private static final Position midRight = new Position(Row.Middle, Col.Right);
    private static final Position botLeft  = new Position(Row.Bottom, Col.Left);
    private static final Position botRight = new Position(Row.Bottom, Col.Right);

    private Linus linus() {
        return new Linus("Linus", Token.X);
    }

    @Test
    void testEmptyBoardPlaysTopLeft() {
        Board b = new Board("---------");
        assertEquals(topLeft, linus().getNextMove(b));
    }

    @Test
    void testTopLeftTakenPlaysTopMid() {
        Board b = new Board("X--" +
                "---" +
                "---");
        assertEquals(topMid, linus().getNextMove(b));
    }

    @Test
    void testTopRowTakenPlaysMidLeft() {
        Board b = new Board(
                "XOX" +
                "---" +
                "---");
        assertEquals(midLeft, linus().getNextMove(b));
    }

    @Test
    void testTopTwoRowsTakenPlaysBotLeft() {
        Board b = new Board(
                        "XOX" +
                        "OXO" +
                        "---");
        assertEquals(botLeft, linus().getNextMove(b));
    }

    @Test
    void testOnlyLastCellEmpty() {
        Board b = new Board(
                "XOX" +
                "OXO" +
                "XO-");
        assertEquals(botRight, linus().getNextMove(b));
    }

    @Test
    void testDoesNotTakeImmediateWin() {
        // X's winning move is bot left, but first empty cell is mid-right
        Board b = new Board(
                "OXX" +
                "OX-" +
                "---");
        assertEquals(midRight, linus().getNextMove(b));
    }

    @Test
    void testDoesNotBlockOpponent() {
        // O threatens mid-right, but first empty cell for X is top-left
        Board b = new Board("-X-OO-X--");
        assertEquals(topLeft, linus().getNextMove(b));
    }

    // Input and State Validation

    @Test
    void testDoesNotPlayOccupiedCell() {
        Board b = new Board("XOX-O-X--");
        assertTrue(b.isEmptyAt(linus().getNextMove(b)));
    }

    @Test
    void testDoesNotMutateBoard() {
        Board b = new Board("XO-------");
        Board snapshot = new Board(b);
        linus().getNextMove(b);
        assertEquals(snapshot, b);
    }

    // Determinism

    @Test
    void testDeterministicForSameBoard() {
        Board b = new Board("XO-OX----");
        Linus l = linus();
        assertEquals(l.getNextMove(b), l.getNextMove(b));
    }
}