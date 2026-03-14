package tictactoe.game.player;

import tictactoe.game.*;
import tictactoe.ui.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Player {
    public Token token;
    public String name;
//    name, token, and desired move (desired move done)


    /**
     * Prompts the player to pick their next move.
     * Will continue to prompt until the player picks a valid move (i.e. an empty position on the board)
     * @param board The current state of the board
     * @return The (valid) position on the board where the player wants to place their token
     */
    public abstract Position getNextMove(Board board);

}
