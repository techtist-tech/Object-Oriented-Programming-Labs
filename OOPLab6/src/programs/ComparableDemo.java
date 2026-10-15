package programs;

import java.util.Arrays;

/** Program 6.3: Comparable, an interface from the Java library */
class Player implements Comparable<Player> {
    private final String name;
    private final int score;

    Player(String name, int score) { this.name = name; this.score = score; }

    // EXPLAIN: defines the natural ordering: highest score first
    @Override
    public int compareTo(Player other) {
        return Integer.compare(other.score, this.score);
    }

    @Override
    public String toString() { return name + "(" + score + ")"; }
}

public class ComparableDemo {
    public static void main(String[] args) {
        Player[] players = {
            new Player("Anesu", 70), new Player("Blessing", 95), new Player("Chenai", 82)
        };
        Arrays.sort(players);          // sort relies only on the Comparable contract
        System.out.println(Arrays.toString(players));
    }
}
