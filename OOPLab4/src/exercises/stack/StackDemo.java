package exercises.stack;

public class StackDemo {
    public static void main(String[] args) {
        // ---- Inheritance version leaks the ArrayList API ----
        BadStack<String> bad = new BadStack<>();
        bad.push("A");
        bad.push("B");
        bad.push("C");
        bad.add(0, "SNEAKY");          // inserts at the BOTTOM: not a stack operation!
        bad.remove(1);                 // removes from the middle: not a stack operation!
        System.out.println("BadStack after illegal operations: " + bad);

        // ---- Composition version exposes only stack operations ----
        GoodStack<String> good = new GoodStack<>();
        good.push("A");
        good.push("B");
        good.push("C");
        // good.add(0, "SNEAKY");      // COMPILE ERROR: GoodStack has no add(int,E)
        System.out.println("GoodStack peek: " + good.peek());
        System.out.println("GoodStack pop order: " + good.pop() + good.pop() + good.pop());
        System.out.println("GoodStack empty? " + good.isEmpty());

        /*
         * WHAT THE INHERITANCE VERSION WRONGLY EXPOSES:
         * add(int, E), remove(int), set(int, E), get(int), clear(), sort(), subList(), iterator()-based removal...
         * all of which let callers read or change elements at arbitrary positions, destroying the
         * last-in-first-out guarantee. A Stack is NOT an ArrayList ("is-a" is false); it merely USES one
         * ("has-a"), so composition is the right design (Effective Java: "favour composition over inheritance").
         */
    }
}
