package tictactoe.game.player;
import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import java.util.List;

public class Linus extends Player {
    public Linus(String name, Token token) {
        this.name = "Linus";
        this.token = token;
    }

    /**
     *  <li>Linus literally just grabs the first position available from the list of available positions</li>
     * @param board The current state of the board
     * @return Position getNextMove
     */
    @Override
    public Position getNextMove(Board board) {
        Position pos;
        List<Position> spots = board.getEmptyCells();
        pos = spots.getFirst();
        return pos;
    }
}
