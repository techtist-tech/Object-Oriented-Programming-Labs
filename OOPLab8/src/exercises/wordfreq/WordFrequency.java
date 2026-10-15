package exercises.wordfreq;

import java.util.*;

/** Intermediate 2: read a paragraph and print the frequency of each word, sorted by frequency descending. */
public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = """
            Object oriented programming organises software around objects. An object has state and behaviour.
            Classes describe objects, and objects are created from classes. Good programs reuse classes,
            and good classes hide their state behind behaviour.
            """;

        Map<String, Integer> freq = new HashMap<>();
        for (String raw : paragraph.toLowerCase().split("[^a-z]+")) {      // split on anything that isn't a letter
            if (!raw.isEmpty()) freq.merge(raw, 1, Integer::sum);
        }

        // Sort entries: frequency descending, then alphabetically to make ties deterministic
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey()));

        for (Map.Entry<String, Integer> e : entries) {
            System.out.printf("%-12s %d%n", e.getKey(), e.getValue());
        }
    }
}
