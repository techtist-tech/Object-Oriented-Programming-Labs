package exercises.immutable;

import java.util.ArrayList;
import java.util.List;

public class ImmutablePersonDemo {
    public static void main(String[] args) {
        List<String> hobbies = new ArrayList<>(List.of("chess", "football"));
        ImmutablePerson p = new ImmutablePerson("Tariro", 20, hobbies);

        hobbies.add("hacking");                 // changing the ORIGINAL list afterwards...
        System.out.println(p);                  // ...does not affect p (defensive copy)

        try {
            p.getHobbies().add("gaming");
        } catch (UnsupportedOperationException e) {
            System.out.println("getHobbies() is read-only");
        }

        ImmutablePerson older = p.withAge(21);
        System.out.println("Original: " + p);
        System.out.println("New copy: " + older);
    }
}
