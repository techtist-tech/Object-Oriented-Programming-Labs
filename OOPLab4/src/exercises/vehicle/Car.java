package exercises.vehicle;

public class Car extends Vehicle {
    protected final int doors;

    public Car(String brand, int doors) {
        super(brand);
        this.doors = doors;
        System.out.println("2. Car constructor (doors=" + doors + ")");
    }
}
