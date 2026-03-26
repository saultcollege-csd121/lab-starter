package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

public  class Omola extends Player {
    public Omola(Token token) {
        super("Omola", token);
    }

    @Override
    public Position getNextMove(Board board) {
        Board omolaBoard = new Board(board);
        var emptyCells = board.getEmptyCells();

        for (Position pos : emptyCells) {
            omolaBoard.place(pos, token());
            Token opponent = (token() == Token.X) ? Token.O : Token.X;

            if (omolaBoard.getWinner().isPresent() &&
                    (omolaBoard.getWinner().get() == token() ||
                            omolaBoard.getWinner().get() == opponent)) {
                board.place(pos, token());
                return pos;
            }
        }
        return emptyCells.getFirst();
    }
}
