package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Col;
import tictactoe.game.Position;
import tictactoe.game.Row;
import tictactoe.game.Token;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OptimusTest {
    private static final Position topLeft = new Position(Row.Top, Col.Left);
    private static final Position topMid = new Position(Row.Top, Col.Middle);
    private static final Position topRight = new Position(Row.Top, Col.Right);
    private static final Position midLeft = new Position(Row.Middle, Col.Left);
    private static final Position center = new Position(Row.Middle, Col.Middle);
    private static final Position midRight = new Position(Row.Middle, Col.Right);
    private static final Position botLeft = new Position(Row.Bottom, Col.Left);
    private static final Position botMid = new Position(Row.Bottom, Col.Middle);
    private static final Position botRight = new Position(Row.Bottom, Col.Right);

    private static final Set<Position> corners =
            Set.of(topLeft, topRight, botLeft, botRight);

    private Optimus bot(Token token) {
        return new Optimus("Optimus", token);
    }

    @Test
    void testTopRowWin() {
        Board b = new Board("XX-OO----");
        assertEquals(topRight, bot(Token.X).getNextMove(b));
    }

    @Test
    void testBottomRowWin() {
        Board b = new Board("OX--O-XX-");
        assertEquals(botRight, bot(Token.X).getNextMove(b));
    }

    @Test
    void testColumnWin() {
        Board b = new Board("X-OX-O---");
        assertEquals(botLeft, bot(Token.X).getNextMove(b));
    }


    @Test
    void testOptimusAsOWin() {
        Board b = new Board(
                        "OO-" +
                        "XX-" +
                        "-X-");
        assertEquals(topRight, bot(Token.O).getNextMove(b));
    }


    @Test
    void testBlockHorizontalWin() {
        Board b = new Board(
                "XX-" +
                "O--" +
                "---");
        assertEquals(topRight, bot(Token.O).getNextMove(b));
    }

    @Test
    void testBlockVerticalWin() {
        Board b = new Board(
                        "--X" +
                        "-O-" +
                        "-O-");
        assertEquals(topMid, bot(Token.X).getNextMove(b));
    }

    @Test
    void testBlockDiagonalWin() {
        Board b = new Board(
                "X-O" +
                "-X-" +
                "---");
        assertEquals(botRight, bot(Token.O).getNextMove(b));
    }


    @Test
    void testWinnerOverBlock() {
        Board b = new Board(
                        "OO-" +
                        "-X-" +
                        "XX-");
        assertEquals(topRight, bot(Token.X).getNextMove(b));
    }


    @Test
    void testOptimalFork() {
        Board b = new Board(
                "X-O" +
                "---" +
                "O-X");
        Position move = bot(Token.X).getNextMove(b);
        assertEquals(center, move);
    }

    @Test
    void preventsFork() {
        Board b = new Board(
                "X--" +
                "---" +
                "--X");
        Position move = bot(Token.O).getNextMove(b);
        Set<Position> safeMoves = Set.of(topMid, midLeft, midRight, botMid);
        assertTrue(safeMoves.contains(move),
                "Expected an edge move to prevent fork, got: " + move);
    }


    @Test
    void emptyBoardReturnsValidPosition() {
        Board b = new Board(
                "---" +
                "---" +
                "---");
        Position move = bot(Token.X).getNextMove(b);
        assertNotNull(move);
        assertTrue(b.isEmptyAt(move));
    }

    @Test
    void respondsToOpponentCentreWithCorner() {
        Board b = new Board(
                        "---" +
                        "-O-" +
                        "---");
        Position move = bot(Token.X).getNextMove(b);
        assertTrue(corners.contains(move),
                "Expected a corner response to opponent centre, got: " + move);
    }

    @Test
    void respondsToOpponentCorner() {
        Board b = new Board(
                        "O--" +
                        "---" +
                        "---");
        Position move = bot(Token.X).getNextMove(b);
        assertEquals(center, move);
    }

    @Test
    void testOptimusVsOptimusAlwaysDraw() {
        // Simulate a full game where both sides play Optimus.
        // Result must not be a win for either side.
        Board b = new Board();
        Optimus botX = bot(Token.X);
        Optimus botO = bot(Token.O);
        Token current = Token.X;

        while (b.getWinner().isEmpty() && !b.isFull()) {
            Position move = (current == Token.X)
                    ? botX.getNextMove(b)
                    : botO.getNextMove(b);
            b.place(move, current);
            current = (current == Token.X) ? Token.O : Token.X;
        }
        assertTrue(b.getWinner().isEmpty(),
                "Minimax vs minimax should always draw, but someone won:\n" + b);
    }

    @Test
    void testFullBoardWithNoWinnerDraw() {
        Board b = new Board(
                        "XOX" +
                        "OOX" +
                        "XXO");
        assertTrue(b.isFull());
        assertTrue(b.getWinner().isEmpty());
    }

    @Test
    void testOneCellLeft() {
        Board b = new Board(
                        "XOX" +
                        "OXO" +
                        "XO-");
        Position move = bot(Token.X).getNextMove(b);
        assertEquals(botRight, move);
    }
}