package exercises.vehicle;

/** Intermediate 1: multilevel inheritance Vehicle -> Car -> ElectricCar. Each constructor prints its name. */
public class Vehicle {
    protected final String brand;

    public Vehicle(String brand) {
        this.brand = brand;
        System.out.println("1. Vehicle constructor (brand=" + brand + ")");
    }
}
