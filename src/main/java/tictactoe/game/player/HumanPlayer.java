package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

/**
 * Represents a human player in the TicTacToe game
 *
 * The human player selects moves by entering board
 * coordinates through the console interface.
 */
public class HumanPlayer extends Player {

    /**
     * Constructs a new HumanPlayer with the given name and token
     *
     * @param name The name of the player
     * @param token The token used by the player (X or O)
     */

    public HumanPlayer(String name, Token token) {
        super(name, token);
    }

    /**
     * Prompts the human player to enter a move and checks
     * if the position is empty
     *
     * @param board Current game board
     * @return The position chosen by the player.
     */
    @Override
    public Position getNextMove(Board board) {

        while (true) {
            var prompt = "%s's turn (%s). Please enter your move (row column): "
                    .formatted(this.name, this.token);
            var pos = Console.promptForPosition(prompt, board);
            if (board.isEmptyAt(pos)) {
                return pos;
            }

            Console.printAlert("Position not valid. Please enter a valid position.");
        }
    }
}