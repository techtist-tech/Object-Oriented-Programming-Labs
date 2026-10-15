package exercises.circle;

public class CircleDemo {
    public static void main(String[] args) {
        System.out.println("Circles before: " + Circle.getCount());
        Circle c1 = new Circle(2.5);
        Circle c2 = new Circle();          // default radius 1.0
        Circle c3 = new Circle(10);
        System.out.println("c1 radius = " + c1.getRadius());
        System.out.println("c2 radius = " + c2.getRadius());
        System.out.printf("c3 area   = %.2f%n", c3.area());
        System.out.println("Circles created: " + Circle.getCount());
    }
}
