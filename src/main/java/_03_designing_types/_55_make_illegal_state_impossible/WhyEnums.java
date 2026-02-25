package _03_designing_types._55_make_illegal_state_impossible;

public class WhyEnums {

    // This...
    enum Direction { NORTH, EAST, SOUTH, WEST }

    // ...is better than this:
    class DirectionStr {
        public static final String NORTH = "NORTH";
        public static final String EAST = "EAST";
        public static final String SOUTH = "SOUTH";
        public static final String WEST = "WEST";
    }

    // ...or this:
    class DirectionInt {
        public static final int NORTH = 0;
        public static final int EAST = 1;
        public static final int SOUTH = 2;
        public static final int WEST = 3;
    }

    // Because with the enum, we can be sure that any variable of type Direction
    // can only have one of the four valid values (NORTH, EAST, SOUTH, WEST).
    // With the other two approaches, we can easily have variables that are supposed to represent directions
    // but have invalid values (e.g., "UP", "DOWN", 5, -1, etc.), which can lead to bugs and errors in our code.
}
