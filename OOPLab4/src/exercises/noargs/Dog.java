package exercises.noargs;

/** The corrected version: Dog explicitly calls super(name). */
public class Dog extends Animal {

    public Dog(String name) {
        super(name);                       // FIX 1: explicit call to the only available constructor
        System.out.println("Dog constructor: " + name);
    }
}
