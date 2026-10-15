package exercises.temperature;

/**
 * Challenge: Temperature with static factory methods and a private constructor.
 *
 * WHY STATIC FACTORIES CAN BE CLEARER THAN OVERLOADED CONSTRUCTORS:
 *  - They have NAMES. new Temperature(25.0) does not say if 25 is Celsius or Fahrenheit, and two
 *    constructors Temperature(double) cannot even coexist (same signature). fromCelsius(25.0) and
 *    fromFahrenheit(77.0) are self-documenting.
 *  - They can validate or convert before an object exists, and may return cached/shared instances.
 *  - The private constructor guarantees every Temperature is created through a controlled route.
 */
public class Temperature {
    private final double celsius;

    private Temperature(double celsius) {
        this.celsius = celsius;
    }

    public static Temperature fromCelsius(double c) {
        return new Temperature(c);
    }

    public static Temperature fromFahrenheit(double f) {
        return new Temperature((f - 32) * 5.0 / 9.0);
    }

    public double toCelsius()    { return celsius; }
    public double toFahrenheit() { return celsius * 9.0 / 5.0 + 32; }

    @Override
    public String toString() {
        return String.format("%.1f C / %.1f F", toCelsius(), toFahrenheit());
    }
}
