package exercises.level;

/** Basic 1: enum Level { LOW, MEDIUM, HIGH } and a switch that prints advice for each. */
public enum Level {
    LOW, MEDIUM, HIGH;

    public static void advise(Level level) {
        switch (level) {
            case LOW -> System.out.println("LOW: all clear, carry on as normal.");
            case MEDIUM -> System.out.println("MEDIUM: stay alert and double-check your work.");
            case HIGH -> System.out.println("HIGH: act now and inform your supervisor.");
        }
    }

    public static void main(String[] args) {
        for (Level l : Level.values()) {
            advise(l);
        }
        System.out.println("valueOf(\"HIGH\") == HIGH ? " + (Level.valueOf("HIGH") == HIGH));
    }
}
