package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import javax.management.loading.ClassLoaderRepository;
import java.util.List;
import java.util.Random;

public class Randy extends Player {
    private final Random random = new Random();

    public Randy(Token token){
        super("Randy", token);
    }

    @Override
    public Position getNextMove(Board board) {
        List<Position> emptyCells = board.getEmptyCells();
            return emptyCells.get(random.nextInt(emptyCells.size()));
    }
}
