package programs;

import java.util.Iterator;

/** Program 9.1: The four kinds of nested class */
public class NestedDemo {

    private final int[] data = {10, 20, 30};
    private static int instances = 0;

    NestedDemo() { instances++; }

    // 1) STATIC NESTED: no outer instance needed
    static class Helper {
        static String describe() { return "Instances created: " + instances; }
    }

    // 2) INNER CLASS: can read the outer object's private fields
    class DataIterator implements Iterator<Integer> {
        private int index = 0;
        public boolean hasNext() { return index < data.length; }   // 'data' belongs to outer
        public Integer next()    { return data[index++]; }
    }

    public static void main(String[] args) {
        NestedDemo outer = new NestedDemo();
        System.out.println(Helper.describe());

        // EXPLAIN: inner class instance requires an outer instance
        NestedDemo.DataIterator it = outer.new DataIterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();

        // 3) LOCAL CLASS
        class Greeter {
            String greet(String n) { return "Hello, " + n; }
        }
        System.out.println(new Greeter().greet("class"));

        // 4) ANONYMOUS CLASS: implements Runnable on the spot
        Runnable task = new Runnable() {
            @Override public void run() { System.out.println("Anonymous task running"); }
        };
        task.run();

        // Modern alternative for single-method interfaces: a lambda (Lab 10)
        Runnable lambdaTask = () -> System.out.println("Lambda task running");
        lambdaTask.run();
    }
}
