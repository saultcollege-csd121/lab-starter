package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;

class OmolaTest {

    @Test
    void getNextMove() {
        var testboard = new Board("XOX-XO--O");
        var omola = new Omola(Token.X);
        var nextMove = omola.getNextMove(testboard);
        var expected = new Position(Row.Bottom, Col.Left);
        assertEquals(expected, nextMove);

        var testboard2 = new Board("XOXXXOO-O");
        var omola2 = new Omola(Token.X);
        var nextMove2 = omola2.getNextMove(testboard2);
        var expected2 = new Position(Row.Bottom, Col.Middle);
        assertEquals(expected2, nextMove2);

        var testboard3 = new Board("---------");
        var omola3 = new Omola(Token.X);
        var nextMove3 = omola3.getNextMove(testboard3);
        var expected3 = new Position(Row.Top, Col.Right);
        assertEquals(expected3, nextMove3);


    }
}