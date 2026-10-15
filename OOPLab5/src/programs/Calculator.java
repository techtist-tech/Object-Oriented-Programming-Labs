package programs;

/** Program 5.1: Compile-time polymorphism (overloading) */
public class Calculator {

    // EXPLAIN: same name, different parameter lists -> resolved at compile time
    int add(int a, int b)           { return a + b; }
    int add(int a, int b, int c)    { return a + b + c; }
    double add(double a, double b)  { return a + b; }
    String add(String a, String b)  { return a + b; }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(2, 3));            // add(int,int)        -> 5
        System.out.println(calc.add(2, 3, 4));         // add(int,int,int)    -> 9
        System.out.println(calc.add(2.5, 3.5));        // add(double,double)  -> 6.0
        System.out.println(calc.add("Java", "OOP"));   // add(String,String)  -> JavaOOP
        System.out.println(calc.add(2, 3.5));          // int widened to double -> 5.5
    }
}
