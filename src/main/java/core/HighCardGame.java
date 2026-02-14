/*
    NOTE:

    Add to this type any variables and methods required
    to represent and manipulate the overall state of the high card game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

/**
 * A high card game between two players
 *
 * each rounds:
 * Both players draw one card
 * the higher ranked card wins
 * if the ranks are equal the round is a tie
 */

public class HighCardGame {

    private final Player p1;
    private final Player p2;

    /**
     * Constructs a HighCardGame with two players
     * @param a the first player
     * @param b the second player
     */

    public HighCardGame(Player a, Player b) {
        p1 = a;
        p2 = b;

    }

    /**
     * Determines whether both players still have cards remaining
     * @return true if both players can continue playing
     */

    public boolean canPlay() {
        return p1.hasCards() && p2.hasCards();

    }

    /**
     * Plays one round of the game
     *
     * each player draws one card. Crads are stored in the array ands the winner is determined by comparing ranks.
     * @param drawn an array used to store the two drawn cards
     * @return 1 if player 1 wins
     * 2 if player 2 wins
     * 0 if the round is a tie
     */

    public int play (Card[] drawn) {
        Card c1 = p1.draw();
        Card c2 = p2.draw();

        drawn[0] = c1;
        drawn[1] = c2;

        int cmp = Integer.compare(
                c1.rank().value(),
                c2.rank().value()
        );
        if (cmp > 0) return 1;
        if (cmp < 0) return 2;
        return 0;
    }
}
