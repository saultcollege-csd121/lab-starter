package core;

/**
 * Manages the logic and state for a two-player High Card game
 *
 * <p>In this game:</p>
 * <ol>
 *   <li>A standard 52-card deck is shuffled and split evenly between two players</li>
 *   <li>Each player receives 26 cards</li>
 *   <li>Players draw one card per round and compare them</li>
 *   <li>The player with the higher-ranked card wins the round</li>
 *   <li>If cards are equal rank, the round is a tie</li>
 * </ol>
 *
 * <p>The game continues until one or both players run out of cards.</p>
 */
public class HighCardGame {

    /**
     * The first player in the game.
     * Initialized in the constructor and cannot be changed.
     */
    private final Player player1;

    /**
     * The second player in the game.
     * Initialized in the constructor and cannot be changed.
     */
    private final Player player2;

    /**
     * Constructs a new High Card game for two players
     *
     * <p>This constructor performs the following setup:</p>
     * <ol>
     *   <li>Creates a shuffled 52-card deck using {@link CardStack#shuffled52CardsDeck()}</li>
     *   <li>Deals 26 cards to player 1</li>
     *   <li>Deals the remaining 26 cards to player 2</li>
     *   <li>Creates Player objects with the given names and their respective hands</li>
     * </ol>
     *
     * <p><b>Note:</b> The deck is automatically shuffled, so each game will have
     * a different card distribution.</p>
     */
    public HighCardGame(String name1, String name2){
        CardStack deck = CardStack.shuffled52CardsDeck();

        // Deal 26 cards for each player
        CardStack hand1 = deck.deal(26);
        CardStack hand2 = deck.deal(26);

        player1 = new Player(name1, hand1);
        player2 = new Player(name2, hand2);
    }

    /**
     * Checks if another round can be played
     *
     * <p>A round can be played if and only if <b>both</b> players still have
     * at least one card in their hands. If either player runs out of cards,
     * the game is over</p>
     *
     * @return true if both players have cards remaining, false otherwise
     */
    public boolean canPlayRound() {
        return player1.hasCards() && player2.hasCards();
    } // hasCards() from Player

    /**
     * Plays one round of the High Card game and returns the result.
     *
     * <p>This method performs the following steps:</p>
     * <ol>
     *   <li>Each player draws one card from their hand</li>
     *   <li>The ranks of both cards are compared using their ordinal values</li>
     *   <li>The player with the higher-ranked card wins the round</li>
     *   <li>If both cards have the same rank, the round is a tie (winner is null)</li>
     * </ol>
     *
     * @return a RoundResult record containing both cards drawn and the winner
     */
    public RoundResult playRound() {

        // Draw one card from each player hand
        Card c1 = player1.drawCard();
        Card c2 = player2.drawCard();

        int r1 = c1.rank().ordinal(); // ordinal() return the position
        int r2 = c2.rank().ordinal();

        Player winner;

        if (r1 > r2) {
            winner = player1;
        } else if (r2 > r1) {
            winner = player2;
        } else {
            winner = null;
        }
        return new RoundResult(c1, c2, winner);
    }

    /**
     * Returns the first player in this game.
     *
     * @return the Player object representing player 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Returns the second player in this game.
     *
     * @return the Player object representing player 2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Represents the outcome of a single round in the High Card game
     *
     * <p>This record stores:</p>
     * <ul>
     *   <li><b>card1</b> - the card drawn by player 1</li>
     *   <li><b>card2</b> - the card drawn by player 2</li>
     *   <li><b>winner</b> - the Player who won (null if tied)</li>
     * </ul>
     */
    public record RoundResult(Card card1, Card card2, Player winner) {} // Used to store the result of one round
}
