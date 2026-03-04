package tictactoe;

import tictactoe.game.TicTacToeGame;
import tictactoe.game.Position;
import tictactoe.ui.Console;
import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;
import static tictactoe.game.TicTacToeGame.Status.*;

class Main {
    static void main() {

        Console.println(colorize(
                "=== TIC TAC TOE ===",
                MAGENTA_TEXT(), BOLD()
        ));

        var nameX = Console.prompt(colorize("Enter Player X name: ", YELLOW_TEXT()));
        var nameO = Console.prompt(colorize("Enter Player O name: ", YELLOW_TEXT()));

        var game = new TicTacToeGame(nameX, nameO);

        while (game.getStatus() == InProgress) {

            var player = game.whoseTurn();
            var board = game.getBoard();

            Position pos;
            while (true) {

                var prompt = colorize(
                        "%s's turn (%s) → Enter move (row column): "
                                .formatted(player.name(), player.token()),
                        GREEN_TEXT(), UNDERLINE()
                );

                pos = Console.promptForPosition(prompt, board);

                if (game.isValidPosition(pos)) {
                    break;
                }

                Console.printAlert(colorize(
                        "Invalid position! Try again.",
                        RED_TEXT(), BOLD()
                ));
            }

            Console.println(colorize(
                    "%s placed %s at (%s,%s)"
                            .formatted(player.name(), player.token(), pos.row(), pos.col()),
                    CYAN_TEXT()
            ));

            game.placeTokenAt(pos);

            Console.println(game.getBoard().toString());

            switch (game.getStatus()) {
                case Draw -> Console.println(colorize(
                        "Game ended in a draw!",
                        MAGENTA_TEXT(), BOLD()
                ));

                case XWins, OWins -> Console.println(colorize(
                        "%s is the WINNER!"
                                .formatted(game.whoseTurn().name()),
                        TEXT_COLOR(0, 255, 120), BOLD()
                ));
            }
        }
    }
}