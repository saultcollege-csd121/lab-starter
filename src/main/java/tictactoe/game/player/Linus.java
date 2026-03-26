package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

public class Linus extends Player {
        public Linus(Token token) {
            super("Linus", token);
        }

        @Override
        public Position getNextMove(Board board) {
            return board.getEmptyCells().getFirst();
        }
    }
