package exercises.division;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Basic 1: read two integers from the user (Scanner) and divide them, handling both non-numeric input
 * and division by zero. Type q at the first prompt to quit.
 */
public class DivisionDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the first integer (q to quit): ");
            if (!in.hasNext()) break;                    // end of input
            if (in.hasNext("q")) { in.next(); break; }

            try {
                int a = in.nextInt();
                System.out.print("Enter the second integer: ");
                int b = in.nextInt();
                System.out.println(a + " / " + b + " = " + (a / b));
            } catch (InputMismatchException e) {
                String bad = in.next();                  // discard the bad token so we don't loop forever
                System.out.println("Not a valid whole number: '" + bad + "'");
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero.");
            }
        }
        System.out.println("Goodbye!");
    }
}
