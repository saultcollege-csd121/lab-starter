package tictactoe.ui;

import java.text.ParseException;
import java.util.Scanner;

import tictactoe.game.Board;
import tictactoe.game.Position;
import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.colorize;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    // Normal info messages
    public static void println(String message) {
        System.out.println(colorize(message, Attribute.TEXT_COLOR(15))); // White
    }

    // Alerts or errors
    public static void printAlert(String message) {
        System.out.println(colorize(message, Attribute.TEXT_COLOR(196), Attribute.BOLD())); // Red bold
    }

    // Success messages
    public static void printSuccess(String message) {
        System.out.println(colorize(message, Attribute.TEXT_COLOR(46))); // Green
    }

    // Prompt for user input
    public static String prompt(String promptMessage) {
        System.out.print(colorize(promptMessage, Attribute.TEXT_COLOR(33))); // Yellow prompt
        return scanner.nextLine();
    }

    // Prompt repeatedly for a valid Position
    public static Position promptForPosition(String prompt, Board board) {
        final String helpMessage = "Input must be in the format 'row column', e.g., '1 2' or 't m' for top middle.";

        while (true) {
            var input = prompt(prompt).trim();

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