package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import java.util.List;

public class Omola extends Player{

    /**
     * Creates an Omola player with the given token.
     * Omola always looks one move ahead to win or block before picking randomly
     */
    public Omola(Token token){
        // Omolas name is always "Omola", but her token (X or O) is decided when the game starts
        super("Omola", token);
    }

    @Override
    public Position getNextMove(Board board) {
        List<Position> emptyCells = board.getEmptyCells();
        Token myToken = this.token();
        Token opponentToken = (myToken == Token.X) ? Token.O : Token.X; // switch based on token

        // Check for a winning move
        for (Position position : emptyCells){
            Board copy = new Board(board);
            copy.place(position, myToken);
            if (copy.getWinner().isPresent() && copy.getWinner().get() == myToken){
                return position;
            }
        }

        // Check for a blocking move
        for (Position position : emptyCells){
            Board copy = new Board(board);
            copy.place(position, opponentToken);
            if (copy.getWinner().isPresent() && copy.getWinner().get() == opponentToken){
                return position;
            }
        }

        // Pick any available position
        return emptyCells.getFirst();
    }
}