package exercises.coin;

import java.util.ArrayList;
import java.util.List;

/** Challenge: enum Coin with values and a method returning the fewest coins for an amount. */
public enum Coin {
    // Declared from smallest to largest; the method below walks them largest-first.
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private final int cents;

    Coin(int cents) { this.cents = cents; }

    public int getCents() { return cents; }

    /**
     * Greedy algorithm: repeatedly take the largest coin that still fits.
     * For the coin system 1, 5, 10, 25 the greedy choice is known to give the minimum number of coins
     * (it is a "canonical" coin system). That is NOT true for every system: with coins {1, 3, 4} and
     * amount 6, greedy picks 4+1+1 (3 coins) while 3+3 (2 coins) is better.
     */
    public static List<Coin> fewestCoins(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        List<Coin> result = new ArrayList<>();
        Coin[] all = values();
        for (int i = all.length - 1; i >= 0; i--) {       // QUARTER, DIME, NICKEL, PENNY
            while (amount >= all[i].cents) {
                result.add(all[i]);
                amount -= all[i].cents;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] amounts = {0, 3, 41, 99, 100, 68};
        for (int a : amounts) {
            List<Coin> coins = fewestCoins(a);
            System.out.println(a + " cents -> " + coins.size() + " coins " + coins);
        }
    }
}
