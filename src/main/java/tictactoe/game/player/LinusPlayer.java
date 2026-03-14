package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

public class LinusPlayer extends Player{
    public LinusPlayer(Token token) {
        super("Linus", token);
    }

    @Override
    public Position getNextMove(Board board) {
        var emptyCells = board.getEmptyCells();

        return emptyCells.get(0);

    }
}
