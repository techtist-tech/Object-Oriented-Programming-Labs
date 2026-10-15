package exercises.person;

/** Basic 1: Person (name, age) with a display() that subclasses inherit. */
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge()     { return age; }

    /** Overridable hook: subclasses add their own detail. */
    protected String extraInfo() { return ""; }

    public void display() {
        System.out.println(getClass().getSimpleName() + ": " + name + ", age " + age + extraInfo());
    }
}
