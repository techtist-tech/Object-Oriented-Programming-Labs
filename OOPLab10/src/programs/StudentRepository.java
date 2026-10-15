package programs;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/** Program 10.2: Saving and loading objects as CSV */
record Learner(String name, int roll, double gpa) {

    String toCsv() { return name + "," + roll + "," + gpa; }

    // EXPLAIN: a static factory that parses one CSV line back into an object
    static Learner fromCsv(String line) {
        String[] p = line.split(",");
        return new Learner(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2]));
    }
}

public class StudentRepository {
    private final Path file;

    public StudentRepository(String fileName) { this.file = Path.of(fileName); }

    public void saveAll(List<Learner> learners) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Learner l : learners) lines.add(l.toCsv());
        Files.write(file, lines);
    }

    public List<Learner> loadAll() throws IOException {
        List<Learner> result = new ArrayList<>();
        if (!Files.exists(file)) return result;          // first run: nothing saved yet
        for (String line : Files.readAllLines(file)) {
            if (!line.isBlank()) result.add(Learner.fromCsv(line));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        StudentRepository repo = new StudentRepository("learners.csv");
        repo.saveAll(List.of(new Learner("Ada", 1, 3.9), new Learner("Kuda", 2, 3.2)));

        for (Learner l : repo.loadAll()) System.out.println(l);
    }
}
