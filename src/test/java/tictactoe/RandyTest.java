package tictactoe;

import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.game.player.Randy;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandyTest {
    @Test
    void testRandyPicksAnEmptyCell(){
        var randy = new Randy(Token.X);
        var board = new Board(
                "X--" +
                "---" +
                "---");
        var pos = randy.getNextMove(board);
        assertTrue(board.isEmptyAt(pos));
    }
    @Test
    void testRandyPicksFromAvailableCells() {
        var randy = new Randy(Token.X);
        var board = new Board(
                "XOX" +
                "---" +
                "---");
        while(!board.isFull()){
            var pos = randy.getNextMove(board);
            assertTrue(board.isEmptyAt(pos));
        }
    }
}
