package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import java.util.Random;

public class RandyPlayer extends Player {
    private static final Random RNG = new Random();

    public RandyPlayer(Token token) {
        super("Randy", token);
    }

    @Override
    public Position getNextMove(Board board) {
        var emptyCells = board.getEmptyCells();
        return emptyCells.get(RNG.nextInt(emptyCells.size()));
    }
}