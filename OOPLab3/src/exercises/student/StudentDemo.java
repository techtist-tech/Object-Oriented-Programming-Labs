package exercises.student;

public class StudentDemo {
    public static void main(String[] args) {
        Student s = new Student("Anesu", 82);
        System.out.println(s);

        s.setMarks(55);
        System.out.println("After setMarks(55): " + s);

        int[] attempts = {-5, 101, 40};
        for (int m : attempts) {
            try {
                s.setMarks(m);
                System.out.println("Accepted " + m + " -> " + s);
            } catch (IllegalArgumentException e) {
                System.out.println("Rejected: " + e.getMessage());
            }
        }

        try {
            new Student("Bad", 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Constructor also validates: " + e.getMessage());
        }
    }
}
