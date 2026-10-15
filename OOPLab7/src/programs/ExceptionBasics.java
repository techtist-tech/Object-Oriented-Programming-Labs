package programs;

/** Program 7.1: try, catch, finally */
public class ExceptionBasics {

    static int divide(int a, int b) {
        return a / b;                       // ArithmeticException if b == 0
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30};
        String text = null;

        try {
            System.out.println("10 / 2 = " + divide(10, 2));
            System.out.println("10 / 0 = " + divide(10, 0));   // throws here
            System.out.println("never printed");
        } catch (ArithmeticException e) {
            System.out.println("Math problem: " + e.getMessage());
        } finally {
            System.out.println("finally always runs");
        }

        // EXPLAIN: several catch blocks, specific first
        try {
            System.out.println(data[5]);
            System.out.println(text.length());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Bad index: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Null reference");
        } catch (Exception e) {                 // most general last
            System.out.println("Something else: " + e);
        }

        // EXPLAIN: multi-catch when the handling is identical
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Invalid number input");
        }
    }
}
