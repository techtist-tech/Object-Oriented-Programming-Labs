package exercises.array;

/** Basic 2: a method that reads an array element and catches ArrayIndexOutOfBoundsException. */
public class ArrayAccess {

    static int safeGet(int[] arr, int index) {
        try {
            return arr[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  Index " + index + " is invalid: " + e.getMessage());
            return -1;       // sentinel value
        }
    }

    public static void main(String[] args) {
        int[] scores = {50, 60, 70};
        System.out.println("scores[1]  = " + safeGet(scores, 1));
        System.out.println("scores[3]  = " + safeGet(scores, 3));
        System.out.println("scores[-1] = " + safeGet(scores, -1));
    }
}
