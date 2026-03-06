package _05_generalization_polymorphism.abstract_classes;

public class Main {

    static void main() {
        Storage s1 = new Disk(100);
        s1.write("Hello, World!");
        Storage s2 = new Cloud(200);
        s2.write("This is some data to store in the cloud.");
    }
}
