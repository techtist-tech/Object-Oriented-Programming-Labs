package exercises.car;

import java.time.Year;

/** Basic 1 & 2: Car class with brand, year, mileage, display() and isAntique(). */
public class Car {
    String brand;
    int year;
    double mileage;   // in kilometres

    void display() {
        System.out.println("Brand: " + brand + ", Year: " + year + ", Mileage: " + mileage + " km");
    }

    /** A car is an antique when it is more than 25 years old. */
    boolean isAntique() {
        int age = Year.now().getValue() - year;
        return age > 25;
    }
}
