package exercises.circle;

/** Basic 1 & 2: Circle with a radius constructor, a no-arg constructor (radius 1.0) and a static counter. */
public class Circle {
    private static int count = 0;   // one copy shared by every Circle

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
        count++;
    }

    public Circle() {
        this(1.0);      // constructor chaining: counter is incremented exactly once, in the main constructor
    }

    public double getRadius() { return radius; }
    public double area()      { return Math.PI * radius * radius; }

    public static int getCount() { return count; }
}
