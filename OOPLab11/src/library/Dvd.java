package library;

public class Dvd extends LibraryItem {
    private final int runtimeMinutes;

    public Dvd(String id, String title, int runtimeMinutes) {
        super(id, title);
        if (runtimeMinutes <= 0) throw new IllegalArgumentException("DVD runtime must be positive");
        this.runtimeMinutes = runtimeMinutes;
    }

    public int getRuntimeMinutes() { return runtimeMinutes; }

    @Override public int loanPeriodDays() { return 7; }      // DVDs go out for a shorter time than books
    @Override public String type()        { return "DVD"; }
    @Override public String detail()      { return runtimeMinutes + " min"; }
    @Override public String storedValue() { return String.valueOf(runtimeMinutes); }
}
