package exercises.history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Intermediate 1: BankAccount (Lab 3) extended with a transaction history. */
public class BankAccount {
    private final String accountNumber;
    private String ownerName;
    private double balance;
    private final List<String> history = new ArrayList<>();

    public BankAccount(String accountNumber, String ownerName, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name required");
        }
        this.accountNumber = accountNumber;
        this.ownerName = ownerName.trim();
        this.balance = openingBalance;
        history.add(String.format("OPEN    %.2f -> balance %.2f", openingBalance, balance));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName()     { return ownerName; }
    public double getBalance()       { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
        history.add(String.format("DEPOSIT %.2f -> balance %.2f", amount, balance));
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
        if (amount > balance) {
            history.add(String.format("FAILED  withdraw %.2f (insufficient funds)", amount));
            return false;
        }
        balance -= amount;
        history.add(String.format("WITHDRAW %.2f -> balance %.2f", amount, balance));
        return true;
    }

    /**
     * Returns an UNMODIFIABLE COPY. List.copyOf takes a snapshot, so later transactions don't
     * change a list the caller already holds, and the caller can't modify our internal list either.
     */
    public List<String> getHistory() {
        return List.copyOf(history);
    }
}
