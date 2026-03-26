package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

public abstract class Player {
    private String name;
    private Token token;

    public Player(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    // Match what TicTacToeGame.java expects
    public String name() { return name; }
    public Token token() { return token; }

    public abstract Position getNextMove(Board board);

    public static class HumanPlayer extends Player {
        public HumanPlayer(String name, Token token) {
            super(name, token);
        }

        @Override
        public Position getNextMove(Board board) {
            while (true) {
                var prompt = "%s's turn (%s). Enter your move (row column): ".formatted(name(), token());
                var pos = Console.promptForPosition(prompt, board);

                if (board.isEmptyAt(pos)) return pos;

                Console.printAlert("That position is not valid. Please enter a valid position.");
            }
        }
    }
}