package tictactoe.game.player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Col;
import tictactoe.game.Position;
import tictactoe.game.Row;
import tictactoe.game.Token;

class RandyPlayerTest {

    @Test
    void getNextMove_returnsEmptyCell() {
        Board board = new Board("XOXOXO---");
        RandyPlayer player = new RandyPlayer(Token.X);

        Position move = player.getNextMove(board);

        Assertions.assertTrue(board.isEmptyAt(move));
    }

    @Test
    void getNextMove_returnsOnlyRemainingCell_whenOneCellLeft() {
        Board board = new Board("XOXOXOOX-");
        RandyPlayer player = new RandyPlayer(Token.O);

        Position move = player.getNextMove(board);

        Assertions.assertEquals(new Position(Row.Bottom, Col.Right), move);
    }

    @Test
    void constructor_setsFixedName() {
        RandyPlayer player = new RandyPlayer(Token.X);
        Assertions.assertEquals("Randy", player.name());
    }
}
