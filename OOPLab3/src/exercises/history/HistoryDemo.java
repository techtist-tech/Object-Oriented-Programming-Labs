package exercises.history;

import java.util.List;

public class HistoryDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-777", "Chipo", 100);
        acc.deposit(50);
        acc.withdraw(30);
        acc.withdraw(1000);     // fails, still recorded

        List<String> snapshot = acc.getHistory();
        snapshot.forEach(System.out::println);

        try {
            snapshot.add("HACK    +1000000");     // caller tries to forge history
        } catch (UnsupportedOperationException e) {
            System.out.println("Caller cannot modify the history list");
        }

        acc.deposit(5);
        System.out.println("Old snapshot size: " + snapshot.size() + ", fresh history size: " + acc.getHistory().size());
    }
}
