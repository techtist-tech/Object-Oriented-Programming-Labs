package programs;

/** Program 2.1: Constructors and this */
public class Book {

    // EXPLAIN: static field -> ONE copy shared by all Book objects
    private static int count = 0;

    // instance fields -> each Book has its own copy
    private final int id;
    private String title;
    private String author;
    private double price;

    // EXPLAIN: static initialiser block runs once, when the class is first loaded
    static {
        System.out.println("Book class loaded");
    }

    // EXPLAIN: instance initialiser block runs before EVERY constructor body
    {
        count++;
        id = count;   // final field assigned exactly once
    }

    // Constructor 1: full initialisation
    public Book(String title, String author, double price) {
        this.title = title;       // EXPLAIN: this.title = field, title = parameter
        this.author = author;
        this.price = price;
    }

    // Constructor 2: constructor chaining with this(...)
    public Book(String title, String author) {
        this(title, author, 0.0);  // EXPLAIN: must be the FIRST statement
    }

    // Constructor 3: no-argument constructor
    public Book() {
        this("Untitled", "Unknown");
    }

    // EXPLAIN: static method -> no 'this'; it can only use static members directly
    public static int getCount() {
        return count;
    }

    public void display() {
        System.out.printf("#%d %s by %s ($%.2f)%n", id, title, author, price);
    }

    public static void main(String[] args) {
        Book b1 = new Book("Clean Code", "Robert Martin", 39.99);
        Book b2 = new Book("Effective Java", "Joshua Bloch");
        Book b3 = new Book();

        b1.display();
        b2.display();
        b3.display();
        System.out.println("Total books created: " + Book.getCount());
    }
}
