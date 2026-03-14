package _05_generalization_polymorphism.school;

import java.util.List;

public class Instructor extends Employee {
    List<Course> teachingAssignments;

    @Override
    public String letterhead() {
        return "Instructor letterhead";
    }
}
