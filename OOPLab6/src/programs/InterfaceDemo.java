package programs;

/** Program 6.2: Interfaces, default methods and multiple implementation */
interface Payable {
    double MAX_LIMIT = 10_000;              // implicitly public static final

    void pay(double amount);                // implicitly public abstract

    // EXPLAIN: default method gives implementations for free; classes may override it
    default void receipt(double amount) {
        System.out.println("Receipt: paid " + amount);
    }

    // EXPLAIN: static utility method belongs to the interface itself
    static boolean withinLimit(double amount) {
        return amount <= MAX_LIMIT;
    }
}

interface Loggable {
    void log(String message);
}

// EXPLAIN: one class, several interfaces -> multiple inheritance of TYPE
class CardPayment implements Payable, Loggable {
    @Override
    public void pay(double amount) {
        if (!Payable.withinLimit(amount)) {
            log("Blocked: over limit");
            return;
        }
        log("Card charged " + amount);
        receipt(amount);
    }

    @Override
    public void log(String message) {           // interface methods must be public
        System.out.println("[LOG] " + message);
    }
}

class CashPayment implements Payable {
    @Override
    public void pay(double amount) {
        System.out.println("Cash received: " + amount);
    }

    @Override
    public void receipt(double amount) {        // overriding the default
        System.out.println("Handwritten receipt for " + amount);
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        // EXPLAIN: program to the interface: the variable type is Payable
        Payable[] methods = { new CardPayment(), new CashPayment() };

        for (Payable p : methods) {
            p.pay(250);
            p.receipt(250);
            System.out.println("---");
        }
        new CardPayment().pay(50_000);
    }
}
