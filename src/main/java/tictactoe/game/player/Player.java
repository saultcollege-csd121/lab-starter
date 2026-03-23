package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

/**
 * The Player class is an abstract base class for all types of players.
 * A player has a name and a token (X or O), and must be able to choose
 * their next move on the board.
 * This class is extended by HumanPlayer and computer players like Randy.
 */
public abstract class Player {

    protected String name;
    protected Token token;

    /**
     * Creates a new Player with a name and token.
     *
     * @param name  the name of the player
     * @param token the token used by the player (X or O)
     */
    public Player(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    /**
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's token.
     *
     * @return the player's token
     */
    public Token getToken() {
        return token;
    }

    /**
     * Gets the next move the player wants to make.
     * Each subclass must implement this differently.
     *
     * @param board the current game board
     * @return the position the player chooses
     */
    public abstract Position getNextMove(Board board);
}
