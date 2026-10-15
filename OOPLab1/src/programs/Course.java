package programs;

/** Program 1.3: Objects that hold other objects */
public class Course {
    String title;
    Student topStudent;   // EXPLAIN: a field can itself be a reference to another object

    public static void main(String[] args) {
        Student s = new Student();
        s.name = "Rudo";
        s.gpa = 3.9;

        Course c = new Course();
        c.title = "Object-Oriented Programming";
        c.topStudent = s;

        // EXPLAIN: chained dot operator: course -> student -> name
        System.out.println(c.title + " top student: " + c.topStudent.name);
    }
}
