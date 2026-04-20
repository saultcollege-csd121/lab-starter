package tictactoe.game;

import java.text.ParseException;

public record Position(Row row, Col col) {

    public static Position parse(String positionStr) throws ParseException {
        positionStr = positionStr.trim().toLowerCase();

        if (positionStr.length() < 2) {
            throw new ParseException("Invalid position: " + positionStr, 0);
        }

        Row row;
        Col col;

        if (positionStr.length() == 2) {
            row = Row.parse(positionStr.substring(0, 1));
            col = Col.parse(positionStr.substring(1));
        } else {
            var parts = positionStr.split("[,; ]+");
            if (parts.length != 2) {
                throw new ParseException("Invalid position: " + positionStr, 0);
            }
            row = Row.parse(parts[0]);
            col = Col.parse(parts[1]);
        }

        return new Position(row, col);
    }
}