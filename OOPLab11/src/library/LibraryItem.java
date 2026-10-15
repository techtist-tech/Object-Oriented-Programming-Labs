package library;

/**
 * Abstract parent of everything the library lends. Holds the shared state (id, title, availability) and the
 * shared borrowing rules; subclasses supply what differs: loan period, type name and extra detail.
 */
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank())       throw new IllegalArgumentException("Item id is required");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Item title is required");
        this.id = id.trim();
        this.title = title.trim();
    }

    /** POLYMORPHIC: each kind of item has its own loan period. */
    public abstract int loanPeriodDays();

    /** Short type label used in listings and in the CSV file (e.g. BOOK, DVD). */
    public abstract String type();

    /** Human-readable extra detail shown in listings (author, "120 min"...). */
    public abstract String detail();

    /** The raw value written to the CSV file for that extra detail (author, or runtime minutes). */
    public abstract String storedValue();

    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public boolean isAvailable() { return available; }

    /** Package-private: used by Library when rebuilding state from files. */
    void setAvailable(boolean available) { this.available = available; }

    @Override
    public void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException {
        if (!available) {
            throw new ItemNotAvailableException(id, title);
        }
        if (member.getActiveLoanCount() >= Member.MAX_LOANS) {
            throw new MemberLimitExceededException(member.getId(), Member.MAX_LOANS);
        }
        available = false;
    }

    @Override
    public void giveBack() {
        available = true;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-6s %-32s %-22s loan=%2d days  %s",
                type(), id, title, detail(), loanPeriodDays(), available ? "AVAILABLE" : "ON LOAN");
    }
}
