package _05_generalization_polymorphism.abstract_classes;

public abstract class Storage {
    double capacity;
    double used;

    public Storage(double capacity) {
        this.capacity = capacity;
        this.used = 0; // Initially empty
    }

    public boolean hasEnoughCapacity(double amount) {
        return this.capacity - this.used >= amount;
    }

    public abstract void write(String data);
}
