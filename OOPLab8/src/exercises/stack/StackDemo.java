package exercises.stack;

import java.util.NoSuchElementException;

public class StackDemo {
    public static void main(String[] args) {
        Stack<Integer> ints = new Stack<>();
        ints.push(10);
        ints.push(20);
        ints.push(30);
        System.out.println(ints + " size=" + ints.size());
        System.out.println("peek = " + ints.peek());
        System.out.println("pop  = " + ints.pop());
        System.out.println("pop  = " + ints.pop());
        System.out.println(ints + " empty? " + ints.isEmpty());

        Stack<String> words = new Stack<>();     // the SAME class works for Strings: that is the point of generics
        words.push("alpha");
        words.push("beta");
        String top = words.pop();                // no cast needed
        System.out.println("popped string: " + top);

        ints.pop();
        try {
            ints.pop();
        } catch (NoSuchElementException e) {
            System.out.println("Popping an empty stack: " + e.getMessage());
        }
    }
}
