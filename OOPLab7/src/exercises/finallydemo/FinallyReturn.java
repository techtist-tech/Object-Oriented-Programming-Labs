package exercises.finallydemo;

/**
 * Challenge: predict the output of a method with 'return' inside try and a print statement inside finally.
 *
 * PREDICTION:
 *   inside try
 *   inside finally
 *   result = 1
 *   ----
 *   inside try
 *   inside finally   (the finally return overrides the try return)
 *   result = 2
 *
 * WHY finally STILL EXECUTES: the JVM guarantees the finally block runs whenever control leaves the try block -
 * by normal completion, by return, break/continue, or by an exception. With 'return', the return VALUE is
 * computed and remembered first, then finally runs, then the method actually returns the remembered value.
 * (Only System.exit() or a JVM crash prevents finally from running.)
 * BAD PRACTICE: a 'return' inside finally replaces the earlier return value and even swallows exceptions.
 */
public class FinallyReturn {

    static int tryReturn() {
        try {
            System.out.println("inside try");
            return 1;
        } finally {
            System.out.println("inside finally");   // still runs, AFTER the return value was computed
        }
    }

    @SuppressWarnings("finally")
    static int finallyOverrides() {
        try {
            System.out.println("inside try");
            return 1;
        } finally {
            System.out.println("inside finally");
            return 2;                               // overrides the return of the try block (avoid this!)
        }
    }

    public static void main(String[] args) {
        System.out.println("result = " + tryReturn());
        System.out.println("----");
        System.out.println("result = " + finallyOverrides());
    }
}
