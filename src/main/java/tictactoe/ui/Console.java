package tictactoe.ui;

import java.text.ParseException;
import java.util.Scanner;

import com.diogonunes.jcolor.AnsiFormat;
import tictactoe.game.Board;
import tictactoe.game.Position;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

/**
 * Helper methods for doing console-based user interaction
 */
public class Console {


    public static void println(String message) {
        var fPrint = TEXT_COLOR(172, 172, 172); // "Silver chalice"
        IO.println(colorize(message, fPrint));
    }

     /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        var fPrompt = new AnsiFormat(BRIGHT_CYAN_TEXT(), BLUE_BACK());
        IO.print(colorize(promptMessage, fPrompt));
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Display an alert message to the user
     * @param message The message to display
     */
    public static void printAlert(String message) {
        var fAlert = new AnsiFormat(BRIGHT_WHITE_TEXT(), RED_BACK());
        IO.println(colorize(message, fAlert));
    }

    /**
     * Repeatedly prompt the user for a position on which to place their next token.
     * If they enter an invalid response or an already-taken position they are re-prompted.
     * @param prompt The prompt to display to the user
     * @param board The current state of the game board
     * @return The position selected by the user
     */
    public static Position promptForPosition(String prompt, Board board) {

        final String helpMessage = "Input must be in the format 'row column', e.g., '1 2' or 't m' for the top middle cell.";

        while ( true ) {
            var input = IO.readln(prompt).trim();

            // The .parse method may throw if the user entered invalid location text, so we try/catch
            try {

                var pos = Position.parse(input);

                if (! board.isEmptyAt(pos)) {
                    printAlert("That position is already taken.");
                    continue;
                }

                return pos;
            } catch ( ParseException e ) {
                printAlert(helpMessage);
            }
        }
    }
}
