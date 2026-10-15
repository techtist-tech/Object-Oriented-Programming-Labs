package exercises.noargs;

/**
 * Intermediate 2: why does 'class Dog extends Animal' fail when Animal only has Animal(String name)?
 *
 * EXPLANATION:
 *  A subclass constructor must call a superclass constructor as its first statement. If you do not write
 *  super(...) yourself, the compiler silently inserts super() - a call to the NO-ARGUMENT constructor.
 *  Animal only has Animal(String name), so no no-argument constructor exists (writing any constructor removes
 *  the default one). The compiler then reports:
 *      constructor Animal in class Animal cannot be applied to given types;
 *      required: String   found: no arguments
 *
 * BROKEN VERSION (does not compile - shown as a comment):
 *
 *      class Dog extends Animal {
 *          Dog() { System.out.println("Dog created"); }   // implicit super() -> no such constructor
 *      }
 *
 * FIX 1: call super(name) explicitly (done in Dog below).
 * FIX 2: add a no-argument constructor to Animal.
 */
public class Animal {
    protected final String name;

    public Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor: " + name);
    }
}
