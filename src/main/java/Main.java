import com.sun.jdi.event.ThreadDeathEvent;
import core.CardStack;
import ui.Console;

public class Main {

    //fully lays out the format of the game, switching between players and printing results
    public static void main(String[] args) {
        Console.GameIntro();
        Console.PlayerTurn();
        if (CardStack.playerStackValue() >= 21) {
            Console.Results();
        } else if (CardStack.playerStackValue() < 21) {
            Console.DealerTurn();
            Console.Results();
        }
    }
}







// ignore this, it is just a flip ton of code that i took out bc it wasn't affecting anything but like what if i need it

//    //initiates the player's turn while constantly prompting for proper input and checking results
//    public static void PlayerTurn() {
//        int playerHand = Console.playerHandValue();
//        String playerDecision = Console.PlayerTurn();
//        if (playerDecision.equals("h")) {
//            while (playerHand >= 0) {
//                if (playerHand < 21) {
//                    Console.PlayerTurn();
//                    playerHand = Console.playerHandValue();
//                }
//                while (playerHand >= 21) {
//                    if (playerHand == 21) {
//                        System.out.println("Player has reached 21, and won");
//                        playerHand = Console.playerHandValue();
//                        System.exit(0);
//                    } else if (playerHand > 21) {
//                        System.out.println("Player has bust. Dealer wins.");
//                        playerHand = Console.playerHandValue();
//                        System.exit(0);
//                    }
//                }
//            }
//        }
/// /        else if (playerDecision.equals("s")){
/// /            DealerTurn();
/// /        }
//    }
//
//
//    //initiates the dealers turn while constantly prompting for user input and checking results
//    public static void DealerTurn() {
//        Console.DealerTurn();
//        int dealerHand = Console.dealerHandValue();
//        String dealerDecision = Console.DealerTurn();
//        if (dealerDecision.equals("h")) {
//            while (dealerHand >= 0) {
//                if (dealerHand < 21) {
//                    Console.DealerTurn();
//                    dealerHand = Console.dealerHandValue();
//                }
//                while (dealerHand >= 21) {
//                    if (dealerHand == 21) {
//                        System.out.println("Dealer has reached 21, and won.");
//                        dealerHand = Console.dealerHandValue();
//                        System.exit(0);
//                    } else if (dealerHand > 21) {
//                        System.out.println("Dealer has bust. Player wins.");
//                        dealerHand = Console.dealerHandValue();
//                        System.exit(0);
//                    }
//                }
//            }
//        }
////        else if (dealerDecision.equals("s")) {
////            PlayerTurn();
////        }
//    }

