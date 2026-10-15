package exercises.vehicle;

public class VehicleDemo {
    public static void main(String[] args) {
        System.out.println("Creating: new ElectricCar(\"Tesla\", 4, 75)");
        ElectricCar e = new ElectricCar("Tesla", 4, 75);
        System.out.println("Result: " + e);
        System.out.println("Order: Vehicle -> Car -> ElectricCar (superclass constructors always run first).");
    }
}
