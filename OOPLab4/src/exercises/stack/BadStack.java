package exercises.stack;

import java.util.ArrayList;

/**
 * Challenge (BEFORE): Stack built by INHERITANCE from ArrayList.
 * It "works", but BadStack IS-A ArrayList, so it wrongly exposes the whole ArrayList API:
 * add(index, e), remove(index), set(...), get(index), sort(...) ... Anyone can break the LIFO rule.
 */
public class BadStack<T> extends ArrayList<T> {
    public void push(T item) { add(item); }

    public T pop() { return remove(size() - 1); }

    public T peek() { return get(size() - 1); }
}
