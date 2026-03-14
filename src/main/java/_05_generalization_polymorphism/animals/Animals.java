package _05_generalization_polymorphism.animals;

import java.util.List;

public class Animals {

    public static class Animal {
        public void makeSound() {
            IO.println("...");
        }
    }
    public static class Cat extends Animal {
        @Override
        public void makeSound() {
            IO.println("Meow");
        }
    }
    public static class Dog extends Animal {
        @Override
        public void makeSound() {
            IO.println("Woof");
        }
    }
    public static class Lion extends Cat {
        @Override
        public void makeSound() {
            IO.println("Roar");
        }
    }
    public static class Wolf extends Dog {
        @Override
        public void makeSound() {
            IO.println("Owwwooooooooooooooooooooooo!");
        }
    }
    public static class Chihuahua extends Dog {
        @Override
        public void makeSound() {
            IO.println("YippityyaappitytyI'mannoying yipyhipypi");
        }
    }

    static void main() {

        var animals = List.of(
            new Cat(),
            new Dog(),
            new Lion(),
            new Wolf(),
            new Chihuahua()
        );

        for ( Animal animal : animals) {
            animal.makeSound();
        }
    }
}
