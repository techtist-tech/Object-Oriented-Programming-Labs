package programs;

/** Program 6.1: Abstract class */
abstract class Vehicle {
    private final String plate;

    Vehicle(String plate) { this.plate = plate; }   // EXPLAIN: abstract classes CAN have constructors

    // EXPLAIN: abstract -> no body; every concrete subclass MUST provide one
    abstract double fuelCostPerKm();

    // concrete method shared by all vehicles (template of common behaviour)
    double tripCost(double km) {
        return km * fuelCostPerKm();
    }

    String getPlate() { return plate; }
}

class Truck extends Vehicle {
    Truck(String plate) { super(plate); }
    @Override double fuelCostPerKm() { return 0.80; }
}

class Motorbike extends Vehicle {
    Motorbike(String plate) { super(plate); }
    @Override double fuelCostPerKm() { return 0.15; }
}

public class VehicleDemo {
    public static void main(String[] args) {
        // Vehicle v = new Vehicle("X");   // COMPILE ERROR: Vehicle is abstract
        Vehicle[] fleet = { new Truck("TRK-1"), new Motorbike("MB-7") };

        for (Vehicle v : fleet) {
            System.out.printf("%s: 100 km costs %.2f%n", v.getPlate(), v.tripCost(100));
        }
    }
}
