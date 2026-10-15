package exercises.person;

/** Challenge: chained dot expression and what happens when the address is null. */
public class PersonDemo {
    public static void main(String[] args) {
        Person withAddress = new Person("Tendai", new Address("12 Samora Machel Ave", "Harare"));
        Person noAddress   = new Person("Chipo", null);

        // Chained dot expression: person -> address -> city
        System.out.println(withAddress.name + " lives in " + withAddress.address.city);

        // When the address is null the chained expression throws NullPointerException
        try {
            System.out.println(noAddress.name + " lives in " + noAddress.address.city);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + noAddress.name
                    + " has no Address object, so '.city' cannot be read from null");
        }

        // The safe way: check for null before the second dot
        String city = (noAddress.address != null) ? noAddress.address.city : "unknown";
        System.out.println(noAddress.name + " lives in " + city);
    }
}
