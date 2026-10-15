package exercises.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Challenge (AFTER): Stack built by COMPOSITION - it HAS-A private ArrayList.
 * Only push/pop/peek/isEmpty/size are public, so the LIFO invariant can't be violated, and the
 * implementation could change (e.g. to an array) without affecting callers.
 */
public class GoodStack<T> {
    private final List<T> items = new ArrayList<>();   // hidden implementation detail

    public void push(T item) { items.add(item); }

    public T pop() {
        if (items.isEmpty()) throw new NoSuchElementException("Stack is empty");
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (items.isEmpty()) throw new NoSuchElementException("Stack is empty");
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() { return items.isEmpty(); }
    public int size()        { return items.size(); }
}
