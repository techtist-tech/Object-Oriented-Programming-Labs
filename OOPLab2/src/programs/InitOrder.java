package programs;

/** Program 2.2: Order of initialisation */
public class InitOrder {
    static int a = show("static field");
    int b = show("instance field");

    static { show("static block"); }
    { show("instance block"); }

    InitOrder() { show("constructor"); }

    static int show(String msg) {
        System.out.println(msg);
        return 1;
    }

    public static void main(String[] args) {
        System.out.println("--- first object ---");
        new InitOrder();
        System.out.println("--- second object ---");
        new InitOrder();
    }
}
