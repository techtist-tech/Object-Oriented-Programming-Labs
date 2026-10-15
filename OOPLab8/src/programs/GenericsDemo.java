package programs;

import java.util.List;

/** Program 8.1: Generic class and generic methods */

// EXPLAIN: T is a type parameter chosen by the caller
class Box<T> {
    private T content;

    Box(T content) { this.content = content; }

    T get()          { return content; }
    void set(T value) { this.content = value; }
}

// EXPLAIN: two type parameters
class Pair<K, V> {
    private final K key;
    private final V value;

    Pair(K key, V value) { this.key = key; this.value = value; }

    K getKey()   { return key; }
    V getValue() { return value; }

    @Override public String toString() { return key + "=" + value; }
}

public class GenericsDemo {

    // EXPLAIN: generic METHOD; <T> before the return type declares the parameter
    static <T> void printAll(List<T> items) {
        for (T item : items) System.out.print(item + " ");
        System.out.println();
    }

    // EXPLAIN: bounded type: T must be a Comparable<T>, so compareTo is available
    static <T extends Comparable<T>> T maxOf(List<T> items) {
        T best = items.get(0);
        for (T item : items) {
            if (item.compareTo(best) > 0) best = item;
        }
        return best;
    }

    public static void main(String[] args) {
        Box<String> sb = new Box<>("Hello");
        Box<Integer> ib = new Box<>(42);           // autoboxing: int -> Integer
        String s = sb.get();                        // no cast needed
        int n = ib.get() + 1;
        // sb.set(99);                              // COMPILE ERROR: type safety
        System.out.println(s + " " + n);

        Pair<String, Integer> p = new Pair<>("age", 21);
        System.out.println(p);

        printAll(List.of(1, 2, 3));
        printAll(List.of("a", "b"));
        System.out.println(maxOf(List.of(3, 9, 4)));
        System.out.println(maxOf(List.of("pear", "apple", "zebra")));
    }
}
