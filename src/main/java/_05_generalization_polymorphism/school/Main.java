package _05_generalization_polymorphism.school;

import java.util.List;

public class Main {

    static void main() {

        var people = List.of(
                new Instructor(),
                new Employee(),
                new Person(),
                new Student()
        );

        showPeople(people);

    }

    public static void showPeople(List<Person> people) {
        for ( Person person : people) {
            System.out.println(person.letterhead());
        }
    }
}
