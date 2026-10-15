package exercises.car;

/** Creates three cars, prints them, and tests isAntique(). */
public class CarDemo {
    public static void main(String[] args) {
        Car c1 = new Car();
        c1.brand = "Toyota";
        c1.year = 2020;
        c1.mileage = 45_000.5;

        Car c2 = new Car();
        c2.brand = "Mercedes-Benz";
        c2.year = 1985;
        c2.mileage = 210_300;

        Car c3 = new Car();
        c3.brand = "Honda";
        c3.year = 2008;
        c3.mileage = 120_750.25;

        Car[] cars = {c1, c2, c3};
        for (Car c : cars) {
            c.display();
            System.out.println("  Antique? " + c.isAntique());
        }
    }
}
