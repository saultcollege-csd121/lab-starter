package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

import java.util.List;
import java.util.Optional;
import java.util.Random;


public class Omola extends Player {

    public Omola(Token token) {
        super("Omola", token);
    }

    @Override
    public Position getNextMove(Board board) {

        List<Position> emptyCells = board.getEmptyCells();


        for (int i = 0; i < emptyCells.size(); i++) {
            Position pos = emptyCells.get(i);

            Board copy = new Board(board);
            copy.place(pos, getToken());

            Optional<Token> winner = copy.getWinner();

            if (winner.isPresent() && winner.get() == getToken()) {
                return pos;
            }
        }


        Token opponent;

        if (getToken() == Token.X) {
            opponent = Token.O;
        } else {
            opponent = Token.X;
        }

        for (int i = 0; i < emptyCells.size(); i++) {
            Position pos = emptyCells.get(i);

            Board copy = new Board(board);
            copy.place(pos, opponent);

            Optional<Token> winner = copy.getWinner();

            if (winner.isPresent() && winner.get() == opponent) {
                return pos;
            }
        }


        Random random = new Random();
        int index = random.nextInt(emptyCells.size());

        return emptyCells.get(index);
    }
}