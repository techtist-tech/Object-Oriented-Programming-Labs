package exercises.bank;

public class BankDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-9", 200);
        try {
            acc.withdraw(150);
            System.out.println("Withdrew 150, balance " + acc.getBalance());
            acc.withdraw(100);                         // fails
            System.out.println("not reached");
        } catch (InsufficientFundsException e) {
            System.out.println("Failed: " + e.getMessage());
            System.out.println("Short by " + e.getShortfall());
        }
        System.out.println("Balance unchanged by the failed attempt: " + acc.getBalance());
    }
}
