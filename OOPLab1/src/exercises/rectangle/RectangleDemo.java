package exercises.rectangle;

/**
 * Intermediate 1: array of 5 rectangles, print the one with the largest area.
 * Intermediate 2: predict, then run, the aliasing program (r2 = r1).
 */
public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle[] rects = {
            new Rectangle(2, 3),
            new Rectangle(5, 4),
            new Rectangle(1, 10),
            new Rectangle(6, 6),
            new Rectangle(3.5, 2)
        };

        Rectangle largest = rects[0];
        for (Rectangle r : rects) {
            System.out.printf("%s area=%.2f perimeter=%.2f%n", r, r.area(), r.perimeter());
            if (r.area() > largest.area()) {
                largest = r;
            }
        }
        System.out.println("Largest area: " + largest + " = " + largest.area());

        // ---- Intermediate 2: aliasing ----
        System.out.println("\n--- Aliasing: Rectangle r2 = r1; ---");
        Rectangle r1 = new Rectangle(4, 5);
        Rectangle r2 = r1;          // copies the REFERENCE, not the object
        r2.width = 99;              // change through r2 ...
        System.out.println("r1.width = " + r1.width);   // ... and r1 sees it: prints 99.0
        System.out.println("r1 == r2 ? " + (r1 == r2));

        /*
         * LAB RECORD EXPLANATION:
         * Prediction: r1.width prints 99.0, not 4.0.
         * Reason: 'Rectangle r2 = r1;' does not create a new Rectangle. Both variables hold the SAME
         * address, so they are aliases of one object on the heap. Changing the width through r2 changes
         * the one shared object, and r1 observes the change.
         * To get an independent copy you must create a new object: new Rectangle(r1.width, r1.height).
         */
    }
}
