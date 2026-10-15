package exercises.temperature;

/**
 * Challenge: Temperature that stores KELVIN internally but offers getCelsius()/getFahrenheit().
 *
 * WHY CHANGING THE INTERNAL UNIT WOULD NOT BREAK CALLERS:
 * Callers only ever see getCelsius()/getFahrenheit()/getKelvin() and the static factories. The private field
 * 'kelvin' is invisible to them. If we later stored Celsius instead, we would change only the field and the
 * bodies of the methods in THIS class; every signature stays identical, so no calling code needs editing.
 * That is the practical payoff of encapsulation.
 */
public class Temperature {
    private static final double ABSOLUTE_ZERO_C = -273.15;

    private final double kelvin;   // internal representation (private: callers cannot depend on it)

    private Temperature(double kelvin) {
        if (kelvin < 0) {
            throw new IllegalArgumentException("Below absolute zero is impossible");
        }
        this.kelvin = kelvin;
    }

    public static Temperature ofKelvin(double k)     { return new Temperature(k); }
    public static Temperature ofCelsius(double c)    { return new Temperature(c - ABSOLUTE_ZERO_C); }
    public static Temperature ofFahrenheit(double f) { return ofCelsius((f - 32) * 5.0 / 9.0); }

    public double getKelvin()     { return kelvin; }
    public double getCelsius()    { return kelvin + ABSOLUTE_ZERO_C; }
    public double getFahrenheit() { return getCelsius() * 9.0 / 5.0 + 32; }

    @Override
    public String toString() {
        return String.format("%.2f K = %.2f C = %.2f F", getKelvin(), getCelsius(), getFahrenheit());
    }
}
