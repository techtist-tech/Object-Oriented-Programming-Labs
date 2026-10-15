package exercises.immutable;

import java.util.List;

/**
 * Intermediate 2: an immutable class. DESIGN CHOICES:
 *  1. 'final class'            -> no subclass can add mutable state or override getters to cheat.
 *  2. 'private final' fields   -> assigned once in the constructor; can never be reassigned.
 *  3. No setters               -> nothing can change the state after construction.
 *  4. Defensive copy IN        -> List.copyOf(hobbies) so the caller's later changes to their own list don't leak in.
 *  5. Unmodifiable OUT         -> List.copyOf returns an immutable list, so getHobbies() exposes no mutable reference.
 *  6. Validation in constructor-> the object is valid from birth and stays valid.
 * Benefits: thread-safe without locks, safe as HashMap keys / shared freely, easy to reason about.
 */
public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<String> hobbies;

    public ImmutablePerson(String name, int age, List<String> hobbies) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        this.name = name;
        this.age = age;
        this.hobbies = List.copyOf(hobbies);        // defensive copy
    }

    public String getName() { return name; }
    public int getAge()     { return age; }
    public List<String> getHobbies() { return hobbies; }   // already immutable

    /** "Modification" returns a NEW object, the original is untouched. */
    public ImmutablePerson withAge(int newAge) {
        return new ImmutablePerson(name, newAge, hobbies);
    }

    @Override
    public String toString() {
        return name + " (" + age + ") hobbies=" + hobbies;
    }
}
