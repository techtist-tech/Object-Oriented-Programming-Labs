package exercises.person;

/** Basic 2: Teacher also extends Person: it shares the Person code (name, age, display()). */
public class Teacher extends Person {
    private final String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    protected String extraInfo() { return ", teaches " + subject; }
}
