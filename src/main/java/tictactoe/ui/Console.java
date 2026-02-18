package tictactoe.ui;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;


import java.text.ParseException;
import java.util.Scanner;

import tictactoe.game.Board;
import tictactoe.game.Position;

/**
 * Helper methods for doing console-based user interaction
 */
public class Console {


    public static void println(String message) {
        IO.println(message);
    }

    /**
     /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        IO.print(colorize(promptMessage, BLACK_TEXT(), YELLOW_BACK(), BOLD()));
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Display an alert message to the user
     * @param message The message to display
     */
    public static void printAlert(String message) {
        IO.println(colorize(message, BOLD(), BRIGHT_YELLOW_TEXT(), GREEN_BACK()));
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
