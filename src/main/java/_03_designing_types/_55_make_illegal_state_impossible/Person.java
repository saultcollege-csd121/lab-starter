package _03_designing_types._55_make_illegal_state_impossible;

public record Person(String name, int age) {

    /*
    This constructor (which extends the canonical constructor defined in the record header)
    is call a "compact constructor".
    We can use it to perform validation on the input parameters.
    This ensures that any instance of Person created will always be in a valid state,
    making illegal states impossible.
    I.e., if we have a Person object, we can be sure that its name is not null or blank, and its age is not negative.
     */
    public Person {
        if ( name == null || name.isBlank() ) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if ( age < 0 ) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}
