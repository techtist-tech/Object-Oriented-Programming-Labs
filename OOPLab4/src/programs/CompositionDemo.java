package programs;

/** Program 4.3: final, Object methods and composition */
class Engine {
    void start() { System.out.println("Engine started"); }
}

// EXPLAIN: final class -> cannot be extended
final class Car {
    private final Engine engine = new Engine();   // HAS-A relationship (composition)

    void drive() {
        engine.start();                            // delegate to the part
        System.out.println("Car is moving");
    }

    // EXPLAIN: every class inherits toString() from Object; we override it
    @Override
    public String toString() { return "Car with an engine"; }
}

public class CompositionDemo {
    public static void main(String[] args) {
        Car c = new Car();
        c.drive();
        System.out.println(c);          // implicitly calls c.toString()
    }
}
