package tictactoe;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;
import tictactoe.game.player.Omola;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OmolaTest {
    @Test
    void testOmolaTakesWinningMove() {
        var omola = new Omola(Token.X);
        var board = new Board(
            "XX-" +
            "OO-" +
            "---");
        var pos = omola.getNextMove(board);
        assertEquals(new Position(Row.Top, Col.Right), pos);
    }
    @Test
    void testOmolaBlocksOpponentWin() {
        var omola = new Omola(Token.X);
        var board = new Board(
            "OX-" +
            "O--" +
            "---");
        var pos = omola.getNextMove(board);
        assertEquals(new Position(Row.Bottom, Col.Left), pos);
    }
    @Test
    void testOmolaPicksAnyMoveWhenNoWinOrBlock() {
        var omola = new Omola(Token.X);
        var board = new Board(
            "X--" +
            "-X-" +
            "--O");
        var pos = omola.getNextMove(board);
        assertTrue(board.isEmptyAt(pos));
    }
    @Test
    void testOmolaPrefersWinOverBlock() {
        var omola = new Omola(Token.X);
        var board = new Board(
            "XX-" +
            "OO-" +
            "---");
        var pos = omola.getNextMove(board);
        assertEquals(new Position(Row.Top, Col.Right), pos);
    }

}
