package exercises.person;

public class PersonDemo {
    public static void main(String[] args) {
        Person p = new Person("Generic Person", 30);
        Student s = new Student("Tariro", 19, "Software Engineering");
        Teacher t = new Teacher("Mr Moyo", 45, "Databases");

        p.display();
        s.display();   // display() written ONCE in Person, reused by both subclasses
        t.display();
    }
}
