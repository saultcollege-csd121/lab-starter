package core;

import java.util.Objects;

/**
 * Represents a player in a card game
 *
 * <p>Each player has:</p>
 * <ul>
 *   <li>A name to identify them</li>
 *   <li>A hand (CardStack) containing their cards</li>
 * </ul>
 *
 * <p>Players are immutable in terms of their identity - once created,
 * a player's name and hand reference cannot be changed. However, the
 * contents of their hand can be modified through drawing cards.</p>
 */
public class Player {

    /**
     * The player's name.
     * This is immutable and cannot be changed after the player is created.
     */
    private final String name;

    /**
     * The player's hand of cards.
     * While the reference is final (can't point to a different CardStack),
     * the contents can be modified by drawing or adding cards.
     */
    private final CardStack hand;

    /**
     * Constructs a new Player with the given name and hand of cards.
     *
     * <p>This constructor is typically called by the game controller
     * (e.g., HighCardGame) during game initialization. The game deals
     * cards from the main deck and passes them to create each player.</p>
     */
    public Player (String name, CardStack hand) { // HighCardGame > Player > CardStack
        this.name = name;
        this.hand = hand;
    }

    /**
     * Returns the player's name
     *
     * <p>This is useful for displaying who won a round, showing player
     * information in the UI, or debugging game state.</p>
     *
     * @return the player's name as a String
     */
    public String getName() {
        return name;
    } //Return the name of the player

    /**
     * Checks if the player has any cards remaining in their hand.
     *
     * <p>This method is commonly used to determine:</p>
     * <ul>
     *   <li>Whether the player can continue playing</li>
     *   <li>If the game should end (when a player runs out of cards)</li>
     *   <li>Game state validation before drawing cards</li>
     * </ul>
     *
     * @return true if the player has at least one card, false if the hand is empty
     */
    public boolean hasCards() {
        return hand.size() > 0;
    }

    /**
     * Draws (removes and returns) one card from the player's hand.
     *
     * <p>This method:</p>
     * <ol>
     *   <li>Removes the top card from the player's hand</li>
     *   <li>Returns that card to the caller (usually the game controller)</li>
     *   <li>Reduces the player's hand size by 1</li>
     * </ol>
     *
     * @return the Card drawn from the top of the player's hand
     */
    public Card drawCard() {
        return hand.draw();
    }

    /**
     * Returns the player's hand of cards
     *
     * <p>This provides access to the player's CardStack, which can be used to:</p>
     * <ul>
     *   <li>Check how many cards the player has: {@code getHand().size()}</li>
     *   <li>View all cards in the hand: {@code getHand().toString()}</li>
     *   <li>Perform advanced operations on the hand</li>
     * </ul>
     *
     * @return the CardStack representing the player's hand
     */
    public CardStack getHand() {
        return hand;
    } //Return the hand of the player

    /**
     * Returns a string representation of this player
     *
     * <p>This override allows Player objects to be printed directly,
     * displaying the player's name instead of the default object notation.</p>
     *
     * @return the player's name
     */
    public String toString() {
        return name;
    } //Convert player to String. Allow printing Player object
}
