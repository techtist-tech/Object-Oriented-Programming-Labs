package exercises.book;

import java.util.HashSet;
import java.util.Set;

public class BookDemo {
    public static void main(String[] args) {
        Set<Book> library = new HashSet<>();
        System.out.println("add Clean Code:            " + library.add(new Book("978-0132350884", "Clean Code")));
        System.out.println("add Effective Java:        " + library.add(new Book("978-0134685991", "Effective Java")));
        System.out.println("add Clean Code (2nd copy): " + library.add(new Book("978-0132350884", "Clean Code (reprint)")));
        System.out.println("Set size: " + library.size() + "   <- the duplicate ISBN was rejected");
        System.out.println(library.contains(new Book("978-0134685991", "any title")) + " <- contains() finds a book by ISBN alone");
    }
}
