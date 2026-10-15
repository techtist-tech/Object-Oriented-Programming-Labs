package exercises.average;

import java.util.List;
import java.util.stream.Collectors;

/** Intermediate 2: given a list of Student records, use streams to print the names of those above the class average, sorted alphabetically. */
public class AboveAverage {

    record Student(String name, double marks) { }

    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Tendai", 72), new Student("Anesu", 88), new Student("Memory", 55),
            new Student("Blessing", 91), new Student("Farai", 48), new Student("Chipo", 77));

        double average = students.stream().mapToDouble(Student::marks).average().orElse(0);
        System.out.printf("Class average: %.2f%n", average);

        List<String> above = students.stream()
            .filter(s -> s.marks() > average)
            .map(Student::name)
            .sorted()
            .collect(Collectors.toList());

        System.out.println("Above average (alphabetical): " + above);
    }
}
