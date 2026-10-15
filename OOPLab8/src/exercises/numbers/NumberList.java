package exercises.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Basic 1: store 10 integers in an ArrayList; print the sum, the maximum and the list reversed. */
public class NumberList {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(14, 3, 27, 8, 41, 19, 5, 33, 12, 26));

        int sum = 0;
        for (int n : numbers) sum += n;                       // unboxing Integer -> int

        System.out.println("Numbers: " + numbers);
        System.out.println("Sum: " + sum);
        System.out.println("Maximum: " + Collections.max(numbers));

        List<Integer> reversed = new ArrayList<>(numbers);    // copy, so the original keeps its order
        Collections.reverse(reversed);
        System.out.println("Reversed: " + reversed);
    }
}
