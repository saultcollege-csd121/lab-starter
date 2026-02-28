package tictactoe;
import com.diogonunes.jcolor.AnsiFormat;

import com.diogonunes.jcolor.Attribute;
import tictactoe.game.TicTacToeGame;
import tictactoe.game.Position;
import tictactoe.ui.Console;


import static com.diogonunes.jcolor.Ansi.colorize;
import static tictactoe.game.TicTacToeGame.Status.*;

class Main {
    static void main() {

        Console.println(colorize("Welcome to Tic Tac Toe!", Attribute.GREEN_TEXT()));
        var nameX = Console.prompt(colorize("Player X name: ", Attribute.RED_TEXT()));
        var nameO = Console.prompt(colorize("Player O name: ", Attribute.BLUE_TEXT()));
        var game = new TicTacToeGame(nameX, nameO);

        while (game.getStatus() == InProgress) {

            var player = game.whoseTurn();

            var board = game.getBoard();

            Position pos;
            while (true) {
                var prompt = "%s's turn (%s). Enter your move (row column): ".formatted(player.name(), player.token());
                pos = Console.promptForPosition(prompt, board);

                if (game.isValidPosition(pos)) {
                    break;
                }
                Console.printAlert("That position is not valid. Please enter a valid position.");
            }

            Console.println("%s plays %s at %s %s".formatted(player.name(), player.token(), pos.row(), pos.col()));

            game.placeTokenAt(pos);

            Console.println(game.getBoard().toString());

            switch (game.getStatus()) {
                case Draw -> Console.println("It's a draw!");
                case XWins, OWins -> Console.println(colorize("%s wins!".formatted(game.whoseTurn().name()), Attribute.GREEN_TEXT(), Attribute.WHITE_BACK()));
            }

        }
    }
}