package exercises.removeeven;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/** Basic 2: use a Predicate and removeIf to delete all even numbers from a list. */
public class RemoveEven {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));   // must be mutable
        System.out.println("Before: " + numbers);

        Predicate<Integer> isEven = n -> n % 2 == 0;
        numbers.removeIf(isEven);          // safe removal - no ConcurrentModificationException

        System.out.println("After:  " + numbers);
    }
}
