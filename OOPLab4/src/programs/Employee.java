package programs;

/** Program 4.1: Employee hierarchy (superclass) */
public class Employee {

    private final String name;
    protected double baseSalary;      // EXPLAIN: protected -> visible to subclasses

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
        System.out.println("Employee constructor: " + name);
    }

    public String getName() { return name; }

    public double calculatePay() {
        return baseSalary;
    }

    public void describe() {
        System.out.printf("%s earns %.2f%n", name, calculatePay());
    }
}
