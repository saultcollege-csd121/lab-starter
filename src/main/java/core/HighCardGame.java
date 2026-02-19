/*
    NOTE:

    Add to this type any variables and methods required
    to represent and manipulate the overall state of the high card game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.Objects;

/**
 * Represents a high card game between two players.
 * The game manages two players and provides methods to play rounds.
 */
public class HighCardGame {
    private final Player player1;
    private final Player player2;


    /**
     * Constructs a HighCardGame with two players.
     *
     * @param player1 the first player
     * @param player2 the second player
     */
    public HighCardGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Gets the first player.
     *
     * @return player 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Gets the second player.
     *
     * @return player 2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Checks if both players have cards remaining.
     *
     * @return true if both players have cards, false otherwise
     */
    public boolean canPlayRound() {
        return player1.hasCards() && player2.hasCards();
    }

    /**
     * Creates a new game with two players and a shuffled deck split between them.
     *
     * @param player1Name the name of the first player
     * @param player2Name the name of the second player
     * @return a new HighCardGame instance
     */
    public static HighCardGame createGame(String player1Name, String player2Name) {
        CardStack deck = CardStack.createFullDeck();
        deck.shuffle();
        CardStack[] hands = deck.split();

        Player player1 = new Player(player1Name, hands[0]);
        Player player2 = new Player(player2Name, hands[1]);

        return new HighCardGame(player1, player2);
    }

    /**
     * Checks if this HighCardGame is equal to another object.
     * Two games are equal if they have the same players.
     *
     * @param obj the object to compare to
     * @return true if the games are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HighCardGame that = (HighCardGame) obj;
        return Objects.equals(player1, that.player1) && Objects.equals(player2, that.player2);
    }

    /**
     * Returns a hash code for this HighCardGame.
     *
     * @return hash code based on both players
     */
    @Override
    public int hashCode() {
        return Objects.hash(player1, player2);
    }
}