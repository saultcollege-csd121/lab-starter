package _03_designing_types._60_blackjack.core;

public class BlackJackGame {

    private CardStack deck;
    private CardStack playerHand;
    private CardStack dealerHand;

    public BlackJackGame() {
        this.deck = CardStack.shuffled52CardDeck();

        this.playerHand = deck.deal(2);
        this.dealerHand = deck.deal(2);
    }

    public CardStack getDealerHand() {
        return dealerHand;
    }

    public CardStack getPlayerHand() {
        return playerHand;
    }

    public void playerHits() {
        var newCard = deck.draw();
        playerHand.add(newCard);
    }

    public static int getHandValue(CardStack cardStack) {
        int value = 0;
        int numberOfAces = 0;

        for (Card card : cardStack.getCards()) {
            switch (card.rank()) {
                case TWO -> value += 2;
                case THREE -> value += 3;
                case FOUR -> value += 4;
                case FIVE -> value += 5;
                case SIX -> value += 6;
                case SEVEN -> value += 7;
                case EIGHT -> value += 8;
                case NINE -> value += 9;
                case TEN, JACK, QUEEN, KING -> value += 10;
                case ACE -> {
                    value += 11;
                    numberOfAces++;
                }
            }
        }

        while (value > 21 && numberOfAces > 0) {
            value -= 10;
            numberOfAces--;
        }

        return value;
    }

}
