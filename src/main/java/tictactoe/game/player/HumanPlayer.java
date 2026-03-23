package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

/**
 * HumanPlayer represents a real person playing the game.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name, Token token) {
        super(name, token);
    }

    @Override
    public Position getNextMove(Board board) {
        return Console.promptForPosition("Enter your move.",board);
    }
}