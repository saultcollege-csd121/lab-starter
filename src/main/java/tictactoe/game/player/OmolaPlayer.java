package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

public class OmolaPlayer extends Player {
    public OmolaPlayer(Token token) {
        super("Omola", token);
    }

    @Override
    public Position getNextMove(Board board) {
        var empty = board.getEmptyCells();

        // check winning move
        for (var pos : empty) {

            Board copy = new Board(board);
            copy.place(pos, token);

            if (copy.getWinner().isPresent() && copy.getWinner().get() == token) {
                return pos;
            }
        }

        // block opponent
        Token opponent = token == Token.X ? Token.O : Token.X;

        for (var pos : empty) {

            Board copy = new Board(board);
            copy.place(pos, opponent);

            if (copy.getWinner().isPresent() && copy.getWinner().get() == opponent) {
                return pos;
            }
        }

        return empty.get(0);
    }
}

