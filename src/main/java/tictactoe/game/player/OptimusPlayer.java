package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

public class OptimusPlayer extends Player {

    public OptimusPlayer(Token token) {
        super("Optimus", token);
    }

    @Override
    public Position getNextMove(Board board) {
        // 1. Take a winning move if available
        for (var pos : board.getEmptyCells()) {
            Board copy = new Board(board);
            copy.place(pos, token());

            if (copy.getWinner().isPresent() && copy.getWinner().get() == token()) {
                return pos;
            }
        }

        // 2. Block opponent winning move if available
        Token opponent = token() == Token.X ? Token.O : Token.X;

        for (var pos : board.getEmptyCells()) {
            Board copy = new Board(board);
            copy.place(pos, opponent);

            if (copy.getWinner().isPresent() && copy.getWinner().get() == opponent) {
                return pos;
            }
        }

        // 3. Otherwise take the first empty cell
        return board.getEmptyCells().get(0);
    }
}