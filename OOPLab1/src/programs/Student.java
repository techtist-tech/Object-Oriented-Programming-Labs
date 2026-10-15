package programs;

/** Program 1.1: A Student class */
public class Student {

    // EXPLAIN: instance variables define the STATE of every Student object
    String name;
    int rollNo;
    double gpa;

    // EXPLAIN: instance methods define BEHAVIOUR; they can read and change the fields
    void display() {
        System.out.println("Name: " + name + ", Roll No: " + rollNo + ", GPA: " + gpa);
    }

    boolean isHonours() {
        return gpa >= 3.5;   // EXPLAIN: returns true/false based on the object's own gpa
    }

    public static void main(String[] args) {
        // EXPLAIN: 'new' allocates memory on the heap and returns a reference
        Student s1 = new Student();
        s1.name = "Tariro";
        s1.rollNo = 101;
        s1.gpa = 3.8;

        Student s2 = new Student();
        s2.name = "Farai";
        s2.rollNo = 102;
        s2.gpa = 3.1;

        s1.display();
        s2.display();
        System.out.println(s1.name + " honours? " + s1.isHonours());
        System.out.println(s2.name + " honours? " + s2.isHonours());
    }
}
