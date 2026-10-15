package exercises.record;

/** Basic 2: record Student(String name, int age): print it, compare two equal records, read a component. */
record Student(String name, int age) { }

public class RecordDemo {
    public static void main(String[] args) {
        Student s1 = new Student("Tariro", 20);
        Student s2 = new Student("Tariro", 20);
        Student s3 = new Student("Farai", 22);

        System.out.println(s1);                                  // Student[name=Tariro, age=20]
        System.out.println("s1.equals(s2): " + s1.equals(s2));   // true  - same component values
        System.out.println("s1 == s2:      " + (s1 == s2));      // false - different objects
        System.out.println("s1.equals(s3): " + s1.equals(s3));   // false
        System.out.println("hashCodes equal: " + (s1.hashCode() == s2.hashCode()));
        System.out.println("s1.name() = " + s1.name() + ", s1.age() = " + s1.age());   // accessors have no 'get'
    }
}
