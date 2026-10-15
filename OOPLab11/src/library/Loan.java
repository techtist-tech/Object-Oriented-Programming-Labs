package library;

import java.time.LocalDate;

/** One borrowing event: which member has which item, from when, due when, and returned when (if at all). */
public class Loan {
    private final LibraryItem item;
    private final Member member;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;          // null while the item is still out

    public Loan(LibraryItem item, Member member, LocalDate borrowDate) {
        this.item = item;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(item.loanPeriodDays());    // polymorphic call: depends on Book/DVD
    }

    public LibraryItem getItem()      { return item; }
    public Member getMember()         { return member; }
    public LocalDate getBorrowDate()  { return borrowDate; }
    public LocalDate getDueDate()     { return dueDate; }
    public LocalDate getReturnDate()  { return returnDate; }
    public boolean isReturned()       { return returnDate != null; }

    void markReturned(LocalDate date) { this.returnDate = date; }

    public boolean isOverdue(LocalDate today) {
        return !isReturned() && today.isAfter(dueDate);
    }

    public long daysOverdue(LocalDate today) {
        return isOverdue(today) ? java.time.temporal.ChronoUnit.DAYS.between(dueDate, today) : 0;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s (borrowed %s, due %s%s)",
                item.getTitle(), member.getName(), borrowDate, dueDate,
                isReturned() ? ", returned " + returnDate : "");
    }
}
