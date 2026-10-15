package exercises.sort;

import java.util.Arrays;

/**
 * Challenge: generic bubbleSort for any Comparable type.
 *
 * WHY int[] WILL NOT WORK:
 *  Generics work only with REFERENCE types. The type parameter T is erased to Object (or to its bound,
 *  Comparable) at compile time, and an int is a primitive, not an object, so it can never be a T.
 *  Moreover int[] is not an Integer[] (arrays of primitives are not arrays of objects), so
 *  bubbleSort(new int[]{...}) is a compile error: "inference variable T has incompatible bounds".
 *  Fix: use the wrapper type Integer[] (autoboxing converts each int to an Integer), or write a
 *  separate overload bubbleSort(int[]) for primitives.
 */
public class BubbleSort {

    static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        boolean swapped;
        for (int pass = 0; pass < arr.length - 1; pass++) {
            swapped = false;
            for (int i = 0; i < arr.length - 1 - pass; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;       // already sorted: stop early
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = {42, 7, 19, 3, 88, 1};
        String[] words = {"pear", "apple", "zebra", "mango", "banana"};

        bubbleSort(numbers);
        bubbleSort(words);
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(words));

        // int[] primitives = {3, 1, 2};
        // bubbleSort(primitives);        // COMPILE ERROR: generics cannot be used with primitive arrays
    }
}
