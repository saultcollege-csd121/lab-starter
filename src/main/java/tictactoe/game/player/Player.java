package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

/**
 * Represents a player in the TicTacToe game.
 *
 * This is an abstract base class that defines the common
 * properties and behavior shared by all player types.
 * Subclasses implement the strategy used to select the
 * next move on the board.
 */
public abstract class Player {

    /**
     * The display name of the player.
     */
    protected String name;

    /**
     * The token used by the player (X or O).
     */
    protected Token token;

    /**
     * Constructs a new Player with the given name and token.
     *
     * @param name The player's name
     * @param token The token used by the player (X or O)
     */
    public Player(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    /**
     * @return The name of this player
     */
    public String name() {
        return name;
    }

    /**
     * @return The token used by this player
     */
    public Token token() {
        return token;
    }

    /**
     * Determines the next move the player wishes to make.
     * Different player types implement different strategies
     * for selecting a move.
     *
     * @param board The current game board
     * @return The position where the player wants to place their token
     */
    public abstract Position getNextMove(Board board);
}