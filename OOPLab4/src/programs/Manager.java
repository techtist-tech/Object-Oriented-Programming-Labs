package programs;

public class Manager extends Employee {     // EXPLAIN: Manager IS-A Employee

    private double bonus;

    public Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary);            // EXPLAIN: must be first; runs Employee's constructor
        this.bonus = bonus;
        System.out.println("Manager constructor: " + name);
    }

    @Override                               // EXPLAIN: compiler checks we really override
    public double calculatePay() {
        return super.calculatePay() + bonus; // reuse the parent's logic, then extend it
    }
}
