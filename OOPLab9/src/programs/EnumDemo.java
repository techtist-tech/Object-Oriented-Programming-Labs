package programs;

/** Program 9.2: Enums */
enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }

// EXPLAIN: an enum can have fields, a private constructor and methods
enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    EARTH(5.976e+24, 6.37814e6);

    private final double mass;      // kg
    private final double radius;    // m

    Planet(double mass, double radius) { this.mass = mass; this.radius = radius; }

    static final double G = 6.67300E-11;

    double surfaceGravity() { return G * mass / (radius * radius); }
}

// EXPLAIN: each constant may override an abstract method (constant-specific body)
enum Operation {
    ADD("+") { double apply(double a, double b) { return a + b; } },
    MULTIPLY("*") { double apply(double a, double b) { return a * b; } };

    private final String symbol;
    Operation(String symbol) { this.symbol = symbol; }

    abstract double apply(double a, double b);
    String symbol() { return symbol; }
}

public class EnumDemo {
    static String kind(Day d) {
        return switch (d) {                    // EXPLAIN: switch expression; compiler checks all cases
            case SAT, SUN -> "Weekend";
            default -> "Weekday";
        };
    }

    public static void main(String[] args) {
        Day today = Day.WED;
        System.out.println(today + " is a " + kind(today) + ", ordinal " + today.ordinal());

        for (Day d : Day.values()) System.out.print(d + " ");
        System.out.println();

        System.out.printf("Earth gravity: %.2f m/s^2%n", Planet.EARTH.surfaceGravity());
        System.out.println(Day.valueOf("FRI"));

        for (Operation op : Operation.values()) {
            System.out.println("6 " + op.symbol() + " 7 = " + op.apply(6, 7));
        }
    }
}
