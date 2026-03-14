package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import static org.junit.jupiter.api.Assertions.*;

class LinusPlayerTest {
    @Test
    void randyChoosesEmptyCell(){

        Board board = new Board("""
                XOX
                -XO
                --O
                """);

        Player randy = new RandiPlayer(Token.X);

        Position move = randy.getNextMove(board);

        assertTrue(board.getEmptyCells().contains(move));
    }


}