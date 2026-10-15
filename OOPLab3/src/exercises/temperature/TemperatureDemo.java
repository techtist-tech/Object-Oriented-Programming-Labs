package exercises.temperature;

public class TemperatureDemo {
    public static void main(String[] args) {
        System.out.println(Temperature.ofCelsius(25));
        System.out.println(Temperature.ofFahrenheit(212));
        System.out.println(Temperature.ofKelvin(0));

        try {
            Temperature.ofCelsius(-300);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected -300 C: " + e.getMessage());
        }
        try {
            Temperature.ofKelvin(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected -1 K: " + e.getMessage());
        }
    }
}
