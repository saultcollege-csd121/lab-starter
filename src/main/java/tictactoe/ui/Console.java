package tictactoe.ui;

import tictactoe.game.*;
import com.diogonunes.jcolor.AnsiFormat;
import tictactoe.game.player.Player;
import tictactoe.game.player.*;
import java.text.ParseException;

import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * Helper methods for doing console-based user interaction
 */
public class Console {

    // Define some colors and text styles for use in the console
    private static final AnsiFormat fPrompt = new AnsiFormat(GREEN_TEXT(), BOLD());
    private static final AnsiFormat fAlert = new AnsiFormat(YELLOW_TEXT());

    public static void println(String message) {
        IO.println(message);
    }

    public static String prompt(String promptMessage) {
        return IO.readln(fPrompt.format(promptMessage));
    }

    public static void printAlert(String message) {
        IO.println(fAlert.format(message));
    }

    public static void showBoard(Board board) {
        var sb = new StringBuilder();
        for (var c : board.toString().toCharArray()) {
            if (c == 'X') {
                sb.append(colorize("X", BRIGHT_CYAN_TEXT()));
            } else if (c == 'O') {
                sb.append(colorize("O", BRIGHT_MAGENTA_TEXT()));
            } else {
                sb.append(c);
            }
        }
        IO.println(sb.toString());
    }

    public static Player promptForPlayer(Token whichPlayer) {

        while (true) {
            String input = prompt("Who will play " + whichPlayer + "? ");

            if (input.startsWith("@")) {

                input = input.substring(1).toLowerCase();

                if (input.equals("randy")) {
                    return new Randy(whichPlayer);
                }
                else if (input.equals("omola")) {
                    return new Omola(whichPlayer);
                }
                else {
                    printAlert("Unknown player. Try @randy or @omola");
                }

            } else {
                return new HumanPlayer(input, whichPlayer);
            }
        }
    }

    public static Position promptForPosition(String prompt, Board board) {

        final String helpMessage = "Input must be in the format 'row column', e.g., '1 2'.";

        while (true) {
            var input = IO.readln(fPrompt.format(prompt)).trim();

            try {
                var pos = Position.parse(input);

                if (!board.isEmptyAt(pos)) {
                    printAlert("That position is already taken.");
                    continue;
                }

                return pos;

            } catch (ParseException e) {
                printAlert(helpMessage);
            }
        }
    }
}