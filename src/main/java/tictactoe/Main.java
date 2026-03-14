package tictactoe;

import tictactoe.game.TicTacToeGame;
import tictactoe.game.Token;
import tictactoe.ui.Console;

import static tictactoe.game.TicTacToeGame.Status.*;

class Main {

    /**
     * The entry point of the Tic Tac Toe application.
     *
     * This method starts the console-based Tic Tac Toe game. It:
     * <ul>
     *   <li>Shows a welcome message and instructions.</li>
     *   <li>Prompts the user to choose a player for X and O.</li>
     *   <li>Runs the main game loop.</li>
     * </ul>
     *
     * During each turn the method:
     * <ul>
     *   <li>Asks for the next move from the current player.</li>
     *   <li>Shows the move that was played.</li>
     *   <li>Prints the updated board.</li>
     *   <li>Checks if the game ended (win or draw).</li>
     * </ul>
     *
     * Players may be human players or computer players.
     * Computer players can be selected by entering: @linus or @optimus
     *
     * linus: simple player that choose the first available move.
     * optimus: advanced AI that uses the minimax algorithm to win or draw.
     */
    static void main() {

        Console.println("🕹️ Welcome to Tic Tac Toe! 🕹️");
        Console.println("You can play as a human 👤 or a computer player 🤖.");
        Console.println("To choose a computer player, type: @linus or @optimus ");
        Console.println("Otherwise type your name");
        Console.println("");

        var playerX = Console.promptForPlayer(Token.X);
        var playerO = Console.promptForPlayer(Token.O);

        var game = new TicTacToeGame(playerX, playerO);

        while (game.getStatus() == InProgress) {

            var turnData = game.doNextTurn();

            Console.println("%s plays %s at %s %s".formatted(
                    turnData.whoseTurn().name(),
                    turnData.whoseTurn().token(),
                    turnData.positionPlayed().row(),
                    turnData.positionPlayed().col()
            ));

            Console.showBoard(turnData.newBoardState());

            switch (game.getStatus()) {
                case Draw -> Console.println("It's a draw! 🤝");
                case XWins, OWins -> Console.println("%s wins! 🎉".formatted(turnData.whoseTurn().name()));
            }
        }
    }
}