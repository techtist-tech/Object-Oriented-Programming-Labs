package exercises.employee;

/**
 * Intermediate 1: Employee with an auto-incrementing employeeId (static counter),
 * three overloaded constructors and a copy constructor.
 */
public class Employee {
    private static int nextId = 1000;   // static counter: shared by all employees

    private final int employeeId;
    private String name;
    private String department;
    private double salary;

    // Constructor 1: full
    public Employee(String name, String department, double salary) {
        this.employeeId = nextId++;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Constructor 2: name and department, salary defaults to 0
    public Employee(String name, String department) {
        this(name, department, 0.0);
    }

    // Constructor 3: name only
    public Employee(String name) {
        this(name, "Unassigned");
    }

    // Copy constructor: same details, but a NEW employee gets a NEW id
    public Employee(Employee other) {
        this(other.name, other.department, other.salary);
    }

    public int getEmployeeId() { return employeeId; }

    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name=%s, dept=%s, salary=%.2f}", employeeId, name, department, salary);
    }
}
