package _05_generalization_polymorphism.optional;

import java.awt.*;
import java.util.Optional;

public class Main {


    public static void main() {

        var rect = getTheRectangleMaybe();

        IO.println(rect.orElseThrow());
        IO.println("The area of the rectangle is " + rect.get().getWidth() * rect.get().getHeight());

    }


    static Optional<Rectangle> getTheRectangleMaybe() {
        if (Math.random() > 0.5) {
            return Optional.of(new Rectangle(10, 20));
        } else {
            return Optional.empty();
        }
    }


}
