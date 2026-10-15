package programs;

/** Program 5.2: Run-time polymorphism (overriding) */
class Shape {
    String name() { return "Generic shape"; }
    double area() { return 0; }
}

class Circle extends Shape {
    private final double r;
    Circle(double r) { this.r = r; }

    @Override String name() { return "Circle"; }
    @Override double area() { return Math.PI * r * r; }
}

class Rectangle extends Shape {
    private final double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }

    @Override String name() { return "Rectangle"; }
    @Override double area() { return w * h; }
}

class Triangle extends Shape {
    private final double b, h;
    Triangle(double b, double h) { this.b = b; this.h = h; }

    @Override String name() { return "Triangle"; }
    @Override double area() { return 0.5 * b * h; }
}

public class Shapes {
    public static void main(String[] args) {
        // EXPLAIN: upcasting -> one array type holds many different subclass objects
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 8)
        };

        double total = 0;
        for (Shape s : shapes) {
            // EXPLAIN: JVM picks name()/area() from the ACTUAL object at run time
            System.out.printf("%-10s area = %.2f%n", s.name(), s.area());
            total += s.area();
        }
        System.out.printf("Total area = %.2f%n", total);
    }
}
