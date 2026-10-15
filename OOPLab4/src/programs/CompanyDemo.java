package programs;

public class CompanyDemo {
    public static void main(String[] args) {
        Employee e = new Employee("Kudzai", 2000);
        Manager m = new Manager("Nyasha", 3500, 800);
        Developer d = new Developer("Tatenda", 3000, 10);

        System.out.println("----");
        e.describe();
        m.describe();      // describe() is inherited; calculatePay() is Manager's version
        d.describe();
    }
}
