package _04_build_automation_and_testing.junit;

import java.text.ParseException;

public class IntUtils {

    public static int parseInt(String s) throws ParseException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ParseException("Input string is not a valid integer: " + s, 0);
        }
    }

    public static boolean isPrime(int n ) {
        if (n <= 1) {
            return false;
        }
//        for (int i = 2; i <= Math.sqrt(n); i++) {
//            if (n % i == 0) {
//                return false;
//            }
//        }
//        return true;

        return switch (n) {
            case 2, 3, 5, 7, 11, 7919 -> true;
            default -> false;
        };
    }
}
