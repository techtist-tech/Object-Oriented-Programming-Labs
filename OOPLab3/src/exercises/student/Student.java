package exercises.student;

/** Basic 1 & 2: Student with private name and marks (0-100); getGrade() derives the grade, no grade field. */
public class Student {
    private String name;
    private int marks;

    public Student(String name, int marks) {
        setName(name);
        setMarks(marks);        // constructor reuses the validating setter
    }

    public String getName() { return name; }
    public int getMarks()   { return marks; }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name required");
        }
        this.name = name.trim();
    }

    public void setMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100 (got " + marks + ")");
        }
        this.marks = marks;
    }

    /** Grade is computed on demand from marks, so it can never get out of step with them. */
    public String getGrade() {
        if (marks >= 75) return "A";
        if (marks >= 60) return "B";
        if (marks >= 50) return "C";
        return "F";
    }

    @Override
    public String toString() {
        return name + " - " + marks + " (" + getGrade() + ")";
    }
}
