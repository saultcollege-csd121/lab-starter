package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

/**
 * A player that always selects the
 * first available position on the board.
 */
public class LinusPlayer extends Player {

    public LinusPlayer(Token token) {
        super("Linus", token);
    }

    /**
     * Selects the next move by choosing the first empty
     * position returned by the board.
     *
     * @param board Current game board
     */
    @Override
    public Position getNextMove(Board board) {
        var emptyCells = board.getEmptyCells();
        return emptyCells.get(0);
    }
}