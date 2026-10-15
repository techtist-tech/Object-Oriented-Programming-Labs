package programs;

import java.util.*;

/** Program 8.2: List, Set and Map */
public class CollectionsDemo {
    public static void main(String[] args) {

        // ---- List: ordered, allows duplicates ----
        List<String> names = new ArrayList<>();   // programming to the interface
        names.add("Zodwa");
        names.add("Alice");
        names.add("Moyo");
        names.add("Alice");
        System.out.println("List: " + names + " size=" + names.size());
        names.remove("Moyo");
        Collections.sort(names);                  // natural (alphabetical) order
        System.out.println("Sorted: " + names);

        // ---- Set: no duplicates ----
        Set<String> unique = new TreeSet<>(names); // TreeSet keeps elements sorted
        System.out.println("Set: " + unique);

        // ---- Map: key -> value ----
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Tino", 78);
        marks.put("Rufaro", 91);
        marks.put("Tino", 82);                    // same key -> value replaced
        marks.putIfAbsent("Nomsa", 65);

        System.out.println("Tino: " + marks.get("Tino"));
        System.out.println("Has Nomsa? " + marks.containsKey("Nomsa"));

        // iterate a map in key order using TreeMap
        for (Map.Entry<String, Integer> e : new TreeMap<>(marks).entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // ---- Word frequency: a classic Map use ----
        String text = "to be or not to be";
        Map<String, Integer> freq = new TreeMap<>();
        for (String w : text.split(" ")) {
            freq.merge(w, 1, Integer::sum);       // add 1, or start at 1
        }
        System.out.println(freq);

        // ---- Queue / Deque ----
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1); stack.push(2); stack.push(3);
        System.out.println("Stack pop: " + stack.pop());   // LIFO -> 3
        Queue<String> line = new ArrayDeque<>();
        line.offer("first"); line.offer("second");
        System.out.println("Queue poll: " + line.poll());  // FIFO -> first
    }
}
