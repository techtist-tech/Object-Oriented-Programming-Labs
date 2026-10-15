package exercises.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Intermediate 2: a singly linked list with
 *  - a private STATIC nested Node class (a node doesn't need the outer list instance), and
 *  - an INNER Iterator class (it walks the outer list's 'head', so it needs the outer instance),
 * so the list works in a for-each loop (it implements Iterable<T>).
 */
public class LinkedList<T> implements Iterable<T> {

    private static class Node<E> {
        E value;
        Node<E> next;
        Node(E value) { this.value = value; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int size() { return size; }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    // INNER class: uses the outer instance's 'head' field directly
    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override public boolean hasNext() { return current != null; }

        @Override
        public T next() {
            if (current == null) throw new NoSuchElementException();
            T value = current.value;
            current = current.next;
            return value;
        }
    }
}
