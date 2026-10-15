package exercises.bands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Challenge: load learners from learners.csv, group them by grade band (A, B, C) with Collectors.groupingBy,
 * and write one report file per band (report_A.txt, report_B.txt, report_C.txt).
 *
 * Bands by GPA:  A >= 3.5,  B >= 3.0,  C below 3.0.
 * NOTE: Program 10.2 (StudentRepository) overwrites learners.csv with just two learners. So if the file has
 * fewer than 5 learners, this program re-seeds it with a larger sample first, whatever order you run things in.
 */
public class GradeBands {

    record Learner(String name, int roll, double gpa) {
        static Learner fromCsv(String line) {
            String[] p = line.split(",");
            return new Learner(p[0], Integer.parseInt(p[1].trim()), Double.parseDouble(p[2].trim()));
        }
        String band() { return gpa >= 3.5 ? "A" : gpa >= 3.0 ? "B" : "C"; }
    }

    static final List<String> SEED = List.of(
        "Ada,1,3.9", "Kuda,2,3.2", "Rudo,3,3.7", "Tendai,4,2.8", "Farai,5,3.1",
        "Chipo,6,2.4", "Tariro,7,3.5", "Moyo,8,2.9", "Nomsa,9,3.0", "Blessing,10,3.95");

    public static void main(String[] args) throws IOException {
        Path csv = Path.of("learners.csv");

        if (!Files.exists(csv) || Files.readAllLines(csv).stream().filter(l -> !l.isBlank()).count() < 5) {
            Files.write(csv, SEED);
            System.out.println("(learners.csv was missing or tiny - seeded with a sample of " + SEED.size() + " learners)");
        }

        List<Learner> learners = Files.readAllLines(csv).stream()
            .filter(l -> !l.isBlank())
            .map(Learner::fromCsv)
            .collect(Collectors.toList());

        // TreeMap keeps the bands in A, B, C order
        Map<String, List<Learner>> byBand = learners.stream()
            .collect(Collectors.groupingBy(Learner::band, TreeMap::new, Collectors.toList()));

        for (Map.Entry<String, List<Learner>> e : byBand.entrySet()) {
            String band = e.getKey();
            List<String> report = new ArrayList<>();
            report.add("Band " + band + " report (" + e.getValue().size() + " learners)");
            report.add("-------------------------");
            e.getValue().stream()
                .sorted(Comparator.comparingDouble(Learner::gpa).reversed())
                .forEach(l -> report.add(String.format("%-10s roll %-3d GPA %.2f", l.name(), l.roll(), l.gpa())));

            Path out = Path.of("report_" + band + ".txt");
            Files.write(out, report);
            System.out.println("Wrote " + out + ":");
            report.forEach(line -> System.out.println("   " + line));
        }
    }
}
