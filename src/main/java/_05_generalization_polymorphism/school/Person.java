package _05_generalization_polymorphism.school;

public class Person {

    protected String name;
    private String address;

    public String letterhead() {
        return name + "\n" + address;
    }

}
