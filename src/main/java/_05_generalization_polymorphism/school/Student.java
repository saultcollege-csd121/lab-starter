package _05_generalization_polymorphism.school;

import java.util.List;

public class Student extends Person {
    Program program;
    List<Course> enrolledClasses;

    public void blah() {
        System.out.println(this.name);
    }
}
