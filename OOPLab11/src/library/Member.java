package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    /** Business rule: a member may hold at most this many loans at once. */
    public static final int MAX_LOANS = 3;

    private final String id;
    private String name;
    private final List<Loan> activeLoans = new ArrayList<>();

    public Member(String id, String name) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Member id is required");
        this.id = id.trim();
        setName(name);
    }

    public String getId()   { return id; }
    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Member name is required");
        this.name = name.trim();
    }

    public int getActiveLoanCount() { return activeLoans.size(); }

    /** Read-only view: callers cannot add or remove loans behind the library's back. */
    public List<Loan> getActiveLoans() { return Collections.unmodifiableList(activeLoans); }

    void addLoan(Loan loan)    { activeLoans.add(loan); }
    void removeLoan(Loan loan) { activeLoans.remove(loan); }

    @Override
    public String toString() {
        return String.format("%-6s %-20s active loans: %d/%d", id, name, activeLoans.size(), MAX_LOANS);
    }
}
