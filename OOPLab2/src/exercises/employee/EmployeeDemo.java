package exercises.employee;

public class EmployeeDemo {
    public static void main(String[] args) {
        Employee e1 = new Employee("Tendai", "IT", 2500);
        Employee e2 = new Employee("Rudo", "HR");
        Employee e3 = new Employee("Farai");
        Employee e4 = new Employee(e1);        // copy constructor

        e4.setSalary(9999);                   // changing the copy does not affect the original

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4 + "   <- copy of e1, own id, own salary");
        System.out.println("Same object? " + (e1 == e4));
    }
}
