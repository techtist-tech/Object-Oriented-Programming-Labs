package programs;

public class BankDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", "Tendai", 500.00);

        acc.deposit(250.00);
        System.out.println(acc);

        System.out.println("Withdraw 100: " + acc.withdraw(100.00));
        System.out.println("Withdraw 5000: " + acc.withdraw(5000.00));
        System.out.println(acc);

        // acc.balance = 1_000_000;   // COMPILE ERROR: balance has private access

        try {
            acc.deposit(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
