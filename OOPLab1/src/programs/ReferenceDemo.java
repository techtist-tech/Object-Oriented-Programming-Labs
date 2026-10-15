package programs;

/** Program 1.2: References, aliasing and null */
public class ReferenceDemo {
    public static void main(String[] args) {
        Student a = new Student();
        a.name = "Original";

        // EXPLAIN: b copies the REFERENCE, not the object -> a and b are aliases
        Student b = a;
        b.name = "Changed via b";
        System.out.println(a.name);            // prints: Changed via b

        // EXPLAIN: == on references compares addresses, not contents
        Student c = new Student();
        c.name = "Changed via b";
        System.out.println(a == b);            // true  (same object)
        System.out.println(a == c);            // false (different objects)

        // EXPLAIN: a reference that points to nothing is null
        Student d = null;
        if (d == null) {
            System.out.println("d does not refer to any object");
        }
        // d.display();  // would throw NullPointerException at runtime
    }
}
