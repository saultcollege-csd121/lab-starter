package tictactoe.game.player;

import tictactoe.game.*;

import java.util.List;
import java.util.Random;

public class Randy extends Player {

    private static final Random rand = new Random();

    public Randy(Token token) {
        super("Randy", token);
    }

    @Override
    public Position getNextMove(Board board) {
        List<Position> empty = board.getEmptyCells();
        return empty.get(rand.nextInt(empty.size()));
    }
}