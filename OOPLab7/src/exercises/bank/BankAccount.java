package exercises.bank;

/**
 * Intermediate 2: BankAccount (Lab 3) modified so withdraw() throws InsufficientFundsException.
 *
 * TRADE-OFFS  (boolean return vs checked exception):
 *  + Exception: cannot be silently ignored (checked => compiler forces a decision); carries rich information
 *    (shortfall, message); separates the normal path from the failure path so the "happy path" code stays clean.
 *  + Boolean:   cheap, simple, no try/catch; fine when failure is routine and callers always check.
 *  - Exception: more verbose for callers; exceptions are slower than a return value; overuse for ordinary
 *    control flow is considered bad style.
 *  - Boolean:   callers can forget to check the result and carry on as if the withdrawal worked (a classic bug).
 * Insufficient funds is a recoverable, business-level condition, so a checked exception is a reasonable choice.
 */
public class BankAccount {
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double openingBalance) {
        if (openingBalance < 0) throw new IllegalArgumentException("Opening balance cannot be negative");
        this.accountNumber = accountNumber;
        this.balance = openingBalance;
    }

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) {
            throw new InsufficientFundsException(
                accountNumber + ": cannot withdraw " + amount + ", balance is " + balance, amount - balance);
        }
        balance -= amount;
    }
}
