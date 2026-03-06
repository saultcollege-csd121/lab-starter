package _05_generalization_polymorphism.abstract_classes;

public class Disk extends Storage {

    public Disk(double capacity) {
        super(capacity);
    }

    @Override
    public void write(String data) {
        if (hasEnoughCapacity(data.length())) {
            this.used += data.length();
            IO.println("Data written to disk: " + data);
        } else {
            IO.println("Not enough capacity to write data: " + data);
        }
    }
}
