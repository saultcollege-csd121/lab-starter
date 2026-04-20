package tictactoe.game.player;

import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Col;
import tictactoe.game.Position;
import tictactoe.game.Row;
import tictactoe.game.Token;

import static org.junit.jupiter.api.Assertions.*;

class OptimusPlayerTest {

    @Test
    void getNextMove_takesWinningMove() {
        Board board = new Board("XX-OO----");
        OptimusPlayer player = new OptimusPlayer(Token.X);

        Position move = player.getNextMove(board);

        assertEquals(new Position(Row.Top, Col.Right), move);
    }

    @Test
    void getNextMove_blocksOpponentWinningMove() {
        // O can win at Top Right, but X has NO immediate winning move
        Board board = new Board("OO-X-----");
        OptimusPlayer player = new OptimusPlayer(Token.X);

        Position move = player.getNextMove(board);

        assertEquals(new Position(Row.Top, Col.Right), move);
    }

    @Test
    void getNextMove_takesFirstEmptyCell_whenNoWinOrBlock() {
        Board board = new Board("XO-O-----");
        OptimusPlayer player = new OptimusPlayer(Token.X);

        Position move = player.getNextMove(board);

        assertEquals(new Position(Row.Top, Col.Right), move);
    }

    @Test
    void constructor_setsFixedName() {
        OptimusPlayer player = new OptimusPlayer(Token.O);

        assertEquals("Optimus", player.name());
    }
}