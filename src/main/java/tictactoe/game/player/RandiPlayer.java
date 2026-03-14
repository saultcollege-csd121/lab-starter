package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import java.util.Random;

public class RandiPlayer extends Player{

    Random random = new Random();
    public RandiPlayer(Token token) {
        super("Randy", token);
    }

    @Override
    public Position getNextMove(Board board) {
        var emptyCells = board.getEmptyCells();

        return emptyCells.get(random.nextInt(emptyCells.size()));
    }
}
