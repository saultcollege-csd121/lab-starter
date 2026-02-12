/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent one playing card.

    You MAY change this to a record/enum as you see fit.
 */
package core;


public class Card {
    String suit;
    String rank;


    //card constructor
    public Card(String Suit, String Rank) {
        this.suit = Suit;
        this.rank = Rank;
    }

    //generates a proper string value for a card's rank
    public String getRank() {
        String rankStr = "";
        switch (rank) {
            case "TWO":
                rankStr += "2";
                break;
            case "THREE":
                rankStr += "3";
                break;
            case "FOUR":
                rankStr += "4";
                break;
            case "FIVE":
                rankStr += "5";
                break;
            case "SIX":
                rankStr += "6";
                break;
            case "SEVEN":
                rankStr += "7";
                break;
            case "EIGHT":
                rankStr += "8";
                break;
            case "NINE":
                rankStr += "9";
                break;
            case "TEN":
                rankStr += "10";
                break;
            case "JACK":
                rankStr += "J";
                break;
            case "KING":
                rankStr += "K";
                break;
            case "QUEEN":
                rankStr += "Q";
                break;
            case "ACE":
                rankStr += "A";
                break;
        }
        return rankStr;
    }

    ;

    // processes a proper suit string for a card
    public String getSuit() {
        String suitStr = "";
        switch (suit) {
            case "HEARTS":
                suitStr += "♥";
                break;
            case "SPADES":
                suitStr += "♠";
                break;
            case "CLUBS":
                suitStr += "♣";
                break;
            case "DIAMONDS":
                suitStr += "♦";
                break;
        }
        return suitStr;
    }

    //process an integer value for a card's corresponding rank
    public int getValue(int handValue) {
        int rankValue = 0;
        switch (rank) {
            case "TWO":
                rankValue = 2;
                break;
            case "THREE":
                rankValue = 3;
                break;
            case "FOUR":
                rankValue = 4;
                break;
            case "FIVE":
                rankValue = 5;
                break;
            case "SIX":
                rankValue = 6;
                break;
            case "SEVEN":
                rankValue = 7;
                break;
            case "EIGHT":
                rankValue = 8;
                break;
            case "NINE":
                rankValue = 9;
                break;
            case "TEN":
                rankValue = 10;
            case "JACK":
                rankValue = 10;
                break;
            case "KING":
                rankValue = 10;
                break;
            case "QUEEN":
                rankValue = 10;
                break;
            case "ACE":
                if ((handValue + 11) > 21) {
                    rankValue = 1;
                }
                if ((handValue + 11) == 21) {
                    rankValue = 11;
                }
                if ((handValue + 11) < 21) {
                    rankValue = 11;
                }
                ;
        }
        return rankValue;
    }

    //to string method for the card object
    @Override
    public String toString() {
        String cardStr = "";
        cardStr += getRank();
        cardStr += getSuit();
        return cardStr;
    }

}
