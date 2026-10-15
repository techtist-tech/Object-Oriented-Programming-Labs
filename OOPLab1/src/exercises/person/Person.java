package exercises.person;

/** Challenge: a Person HAS-AN Address (object holding another object). */
public class Person {
    String name;
    Address address;   // may be null

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
