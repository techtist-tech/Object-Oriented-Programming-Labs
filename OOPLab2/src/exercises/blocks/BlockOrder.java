package exercises.blocks;

/**
 * Intermediate 2: Predict the output of a class with two static blocks and two instance blocks.
 *
 * PREDICTION (for two objects created in main):
 *   static block 1
 *   static block 2
 *   main started
 *   instance block 1
 *   instance block 2
 *   constructor
 *   instance block 1
 *   instance block 2
 *   constructor
 *
 * Static blocks run ONCE, in the order written, when the class is loaded (before main starts).
 * Instance blocks run for EVERY object, in the order written, before the constructor body.
 */
public class BlockOrder {

    static { System.out.println("static block 1"); }

    { System.out.println("instance block 1"); }

    static { System.out.println("static block 2"); }

    { System.out.println("instance block 2"); }

    BlockOrder() { System.out.println("constructor"); }

    public static void main(String[] args) {
        System.out.println("main started");
        new BlockOrder();
        new BlockOrder();
    }
}
