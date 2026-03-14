package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;

class OmolaPlayerTest {

    @Test
    void omolaBlocksOpponent(){

        Board board = new Board("""
                OO-
                X--
                ---
                """);

        Player omola = new OmolaPlayer(Token.X);

        Position move = omola.getNextMove(board);

        Position expected = new Position(Row.Top, Col.Right);

        assertEquals(expected, move);
    }

}