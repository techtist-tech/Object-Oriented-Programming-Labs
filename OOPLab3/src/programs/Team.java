package programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Program 3.2: The defensive-copy trap */
public class Team {
    private final List<String> members = new ArrayList<>();

    public void addMember(String name) { members.add(name); }

    // BAD: caller receives the real list and can modify it directly
    public List<String> getMembersUnsafe() { return members; }

    // GOOD: caller receives a read-only view
    public List<String> getMembers() { return Collections.unmodifiableList(members); }

    public static void main(String[] args) {
        Team t = new Team();
        t.addMember("Chipo");

        t.getMembersUnsafe().clear();                 // silently empties the team!
        System.out.println("After unsafe clear: " + t.getMembers().size());

        t.addMember("Chipo");
        try {
            t.getMembers().clear();                   // blocked
        } catch (UnsupportedOperationException e) {
            System.out.println("Safe getter blocked modification");
        }
    }
}
