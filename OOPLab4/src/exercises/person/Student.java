package exercises.person;

/** Basic 1: Student extends Person and adds course. */
public class Student extends Person {
    private final String course;

    public Student(String name, int age, String course) {
        super(name, age);
        this.course = course;
    }

    @Override
    protected String extraInfo() { return ", studies " + course; }
}
