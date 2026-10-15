package programs;

/** Program 7.3: try-with-resources and exception chaining */
class Connection implements AutoCloseable {     // EXPLAIN: AutoCloseable enables try-with-resources
    private final String name;

    Connection(String name) {
        this.name = name;
        System.out.println(name + " opened");
    }

    void query(boolean fail) {
        if (fail) throw new IllegalStateException(name + " query failed");
        System.out.println(name + " query ok");
    }

    @Override
    public void close() {
        System.out.println(name + " closed");   // called automatically
    }
}

public class ResourceDemo {
    public static void main(String[] args) {
        // EXPLAIN: resources close in REVERSE order, even if an exception occurs
        try (Connection c1 = new Connection("DB1");
             Connection c2 = new Connection("DB2")) {
            c1.query(false);
            c2.query(true);
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // EXPLAIN: wrapping a low-level exception keeps the original as the cause
        try {
            try {
                Integer.parseInt("12x");
            } catch (NumberFormatException e) {
                throw new RuntimeException("Configuration invalid", e);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + " <- " + e.getCause().getClass().getSimpleName());
        }
    }
}
