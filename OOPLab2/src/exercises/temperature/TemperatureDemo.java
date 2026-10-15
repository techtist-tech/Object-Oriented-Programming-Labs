package exercises.temperature;

public class TemperatureDemo {
    public static void main(String[] args) {
        Temperature body = Temperature.fromCelsius(37);
        Temperature freezing = Temperature.fromFahrenheit(32);
        Temperature boiling = Temperature.fromFahrenheit(212);

        System.out.println("Body temperature : " + body);
        System.out.println("Freezing point   : " + freezing);
        System.out.println("Boiling point    : " + boiling);

        // new Temperature(5.0);   // COMPILE ERROR: constructor is private
    }
}
