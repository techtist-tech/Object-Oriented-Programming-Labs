package programs;

/** Program 5.3: Casting, instanceof and what is NOT polymorphic */
class Animal {
    String type = "Animal";                       // field
    void speak() { System.out.println("..."); }
    static void info() { System.out.println("Animal info"); }
}

class Dog extends Animal {
    String type = "Dog";                          // hides the field, does not override it
    @Override void speak() { System.out.println("Woof"); }
    void fetch() { System.out.println("Fetching..."); }
    static void info() { System.out.println("Dog info"); }   // hides, not overrides
}

public class CastingDemo {
    public static void main(String[] args) {
        Animal a = new Dog();          // upcast (implicit, always safe)

        a.speak();                     // Woof   -> method IS polymorphic
        System.out.println(a.type);    // Animal -> field follows the DECLARED type
        a.info();                      // Animal info -> static follows declared type

        // a.fetch();                  // COMPILE ERROR: Animal has no fetch()

        // EXPLAIN: downcast needs a check, otherwise ClassCastException is possible
        if (a instanceof Dog d) {      // pattern matching (Java 16+): test AND cast
            d.fetch();
        }

        Animal plain = new Animal();
        if (!(plain instanceof Dog)) {
            System.out.println("plain is not a Dog; downcast avoided");
        }
    }
}
