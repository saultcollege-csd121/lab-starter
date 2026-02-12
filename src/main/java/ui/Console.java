package ui;

import core.CardStack;

import java.util.Scanner;


public class Console {
    static String playerOne;
    static String playerTwo;

    //outlines some starting greetings to introduce the game, fully equipped with a shuffled deck and two hands
    public static void GameIntro() {
        System.out.println("Welcome to blackjack. Names, please.");
        Scanner nameOne = new Scanner(System.in);
        playerOne = nameOne.next();
        System.out.println("And who else?");
        Scanner nameTwo = new Scanner(System.in);
        playerTwo = nameTwo.next();

        System.out.println("You will both be dealt a hand of two cards to start with, and you will both know each other's hands");
        System.out.println("Currently, the hands are as follows:");
        CardStack.shuffleDeck();
        CardStack.makeHands();
        System.out.println(CardStack.playerDeck.toString() + "--> "+playerOne+" Deck");
        System.out.println(CardStack.dealerDeck.toString() + "--> "+playerTwo+" Deck");
    }

    //pulls together all the necessary methods for the player (player one) to fully play out their turn
    public static String PlayerTurn() {
        String playerDecisionStr = "";
        boolean desc;
        while (true) {
            if (CardStack.playerStackValue() == 21) {
                break;
            }
            if (playerDecisionStr.equals("h")) {
                CardStack.addPlayerCard();
                if (CardStack.playerStackValue() > 21) {
                    break;
                }
                System.out.println(CardStack.playerDeck);
                desc = true;
            } else if (playerDecisionStr.equals("s")) {
                break;
            }
            System.out.println(playerOne +", please choose: h/s");
            Scanner playerDecision = new Scanner(System.in);
            playerDecisionStr = playerDecision.next();
        }
        System.out.println(playerOne+", your deck is now as follows:");
        System.out.println(CardStack.playerDeck);
        return playerDecisionStr;
    }

    //pulls together all the necessary methods for the dealer (player 2) to fully play out their turn
    public static String DealerTurn() {
        System.out.println(playerTwo +", it is your turn.");
        System.out.println("Here is your current deck "+playerTwo+":");
        System.out.println(CardStack.dealerDeck);

        String dealerDecisionStr = "";
        boolean desc;
        while (true) {
            if (CardStack.dealerStackValue() == 21) {
                break;
            }
            if (dealerDecisionStr.equals("h")) {
                CardStack.addDealerCard();
                if (CardStack.dealerStackValue() > 21) {
                    break;
                }
                System.out.println(CardStack.dealerDeck);
                desc = true;
            } else if (dealerDecisionStr.equals("s")) {
                break;
            }
            System.out.println(playerTwo+", please choose: h/s");
            Scanner dealerDecision = new Scanner(System.in);
            dealerDecisionStr = dealerDecision.next();
        }
        System.out.println(playerTwo+", your deck is now as follows:");
        System.out.println(CardStack.dealerDeck);
        return dealerDecisionStr;
    }


    //statically holds the Player's deck value
    public static int playerHandValue() {
        CardStack.playerStackValue();
        return CardStack.playerHandValue;
    }


    //statically holds the dealer's deck value
    public static int dealerHandValue() {
        CardStack.dealerStackValue();
        return CardStack.dealerHandValue;
    }

    //compare's the two players' hands and appropriately prints the proper win results
    public static void Results() {
        int playerPoints = CardStack.playerStackValue();
        int dealerPoints = CardStack.dealerStackValue();
        if (playerPoints > 21) {
            System.out.println(playerOne+" has bust. "+playerTwo+" wins.");
        } else if (dealerPoints > 21) {
            System.out.println("The "+playerTwo+" has bust. "+playerOne+" wins.");
        } else if (playerPoints > dealerPoints) {
            System.out.println("The "+playerOne+" has ended with more points - "+playerOne+" wins. Here is the score:");
            System.out.println(playerPoints + " - " + dealerPoints);
        } else if (dealerPoints > playerPoints) {
            System.out.println("The "+playerTwo+" has ended with more points - "+playerTwo+" wins. Here is the score:");
            System.out.println(playerPoints + " - " + dealerPoints);
        } else if (dealerPoints == playerPoints) {
            System.out.println("What a bore, it seems you two have tied.");
        } else {
            System.out.println("I have no idea what you did, but you broke it. Good job.");
        }
    }
}