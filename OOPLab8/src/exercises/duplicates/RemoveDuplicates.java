package exercises.duplicates;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** Basic 2: remove duplicates from a list of names using a Set. */
public class RemoveDuplicates {
    public static void main(String[] args) {
        List<String> names = List.of("Tino", "Ada", "Tino", "Moyo", "Ada", "Zodwa", "Moyo", "Tino");
        System.out.println("With duplicates:    " + names);

        // LinkedHashSet: no duplicates AND keeps first-seen order (a HashSet would scramble the order)
        Set<String> unique = new LinkedHashSet<>(names);
        List<String> cleaned = new ArrayList<>(unique);
        System.out.println("Without duplicates: " + cleaned);
    }
}
