package _06_interfaces.functional_interfaces;

import java.util.List;

public class StringExamples {

    @FunctionalInterface
    public interface StringPredicate {
        boolean test(String s);
    }

    @FunctionalInterface
    public interface StringProcessor {
        void process(String s);
    }

    public static void printIf(String s, StringPredicate predicate) {
        if (predicate.test(s)) {
            System.out.println(s);
        }
    }

    public static void processAll(List<String> strs, StringProcessor processor) {
        for ( String s : strs ) {
            processor.process(s);
        }
    }


    static void main() {

        var strs = List.of("Hello, world", "I like Java", "Java is the best", "I don't like Java");

        for ( String s : strs ) {
            // This...
            printIf(s, (String str) -> { return str.contains("Java"); });
            // ...can be simplified to this...
            printIf(s, str -> str.contains("Java"));
            /*
                - No return type necessary because it's inferred from the function interface's abstract method
                - Parameter types are not required because they are inferred from the function interface's abstract method
                - If there is only a single parameter, the parentheses around the parameter are optional
                - If the body of the lambda is a single expression, only the expression itself is necessary, and the return statement and curly braces are optional
             */

        }

        // This...
        processAll(strs, str -> IO.println(str));
        // ...can be simplified to this because...
        //    - IO.println is a method that matches the signature of the abstract method of StringProcessor, AND
        //    - We are simply 'forwarding' the parameter to that method, so we can use a method reference instead of a lambda expression
        processAll(strs, IO::println);

    }

}
