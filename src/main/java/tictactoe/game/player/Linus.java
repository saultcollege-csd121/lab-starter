package tictactoe.game.player;

import tictactoe.game.*;

public class Linus extends Player {
    /**
     * Constructor method to store name and token of the Linus player.
     * @param name name of the player
     * @param token token of the player
     */
    public Linus(String name, Token token) {
        super(name, token);
    }

    @Override
    public Position getNextMove(Board board) {
        var emptyCells = board.getEmptyCells();
        return emptyCells.get(0); // a few methods work here, get and getFirst both work
                                  // also, this is a lot simpler than I first imagined it to be, seems to be a
                                  // reoccurring theme as I continue learning these concepts
                                  // no need for a loop here since the main program does that already.
    }
}