package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.*;

import static org.junit.jupiter.api.Assertions.*;

class LinusTest {

    @Test
    public void testGetNextMove() {
        //test that the board places on the first empty square
        var testboard = new Board("XOX-XO--O");
        var linus = new Linus(Token.X);
        var nextMove = linus.getNextMove(testboard);
        var expected = new Position(Row.Middle, Col.Left);
        assertEquals(expected, nextMove);

        var testboard2 = new Board("XOXXXOO-O");
        var linus2 = new Linus(Token.X);
        var nextMove2 = linus2.getNextMove(testboard2);
        var expected2 = new Position(Row.Bottom, Col.Middle);
        assertEquals(expected2, nextMove2);

        var testboard3 = new Board("---------");
        var linus3 = new Linus(Token.X);
        var nextMove3 = linus3.getNextMove(testboard3);
        var expected3 = new Position(Row.Top, Col.Left);
        assertEquals(expected3, nextMove3);
    }
}