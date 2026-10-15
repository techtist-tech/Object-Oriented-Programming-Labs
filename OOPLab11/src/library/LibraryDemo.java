package library;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

/**
 * A scripted, non-interactive walk through every requirement. Uses a fixed "today" so the output is the same
 * on every run, and a separate data folder (library-data-demo) so it never touches the real menu data.
 */
public class LibraryDemo {

    private static final LocalDate TODAY = LocalDate.of(2026, 10, 1);

    public static void main(String[] args) throws IOException {
        Library lib = new Library();

        heading("1. Inheritance + polymorphism: Books and DVDs behind one LibraryItem type");
        lib.addItem(new Book("B1", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B2", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B3", "Java: The Complete Reference", "Herbert Schildt"));
        lib.addItem(new Dvd("D1", "The Social Network", 120));
        lib.addItem(new Dvd("D2", "Hidden Figures, Extended Cut", 127));     // note the comma in the title
        lib.registerMember(new Member("M1", "Tendai"));
        lib.registerMember(new Member("M2", "Rudo"));
        for (LibraryItem item : lib.getItems()) {
            System.out.println(item);        // same call, each subclass supplies its own loan period/detail
        }

        heading("2. Borrowing (limit is " + Member.MAX_LOANS + " loans per member)");
        borrow(lib, "M1", "B1", TODAY.minusDays(20));     // a book 20 days ago: due after 14 -> overdue
        borrow(lib, "M1", "D1", TODAY.minusDays(3));      // a DVD 3 days ago: due after 7  -> not overdue
        borrow(lib, "M1", "B2", TODAY);
        borrow(lib, "M1", "B3", TODAY);                   // 4th loan for M1 -> MemberLimitExceededException
        borrow(lib, "M2", "B1", TODAY);                   // B1 is already out  -> ItemNotAvailableException
        borrow(lib, "M2", "D2", TODAY);                   // fine
        borrow(lib, "M9", "D2", TODAY);                   // unknown member     -> IllegalArgumentException

        heading("3. Overdue items as of " + TODAY);
        for (Loan l : lib.overdueLoans(TODAY)) {
            System.out.println(l + "  [" + l.daysOverdue(TODAY) + " days overdue]");
        }

        heading("4. Search by title (streams)");
        System.out.println("search \"java\":");
        lib.searchByTitle("java").forEach(i -> System.out.println("  " + i));
        System.out.println("search \"zzz\": " + lib.searchByTitle("zzz"));

        heading("5. Returning an item");
        Loan returned = lib.returnItem("B1", TODAY);
        System.out.println("Returned: " + returned);
        System.out.println("Overdue now: " + lib.overdueLoans(TODAY));
        borrow(lib, "M2", "B1", TODAY);                   // works now that B1 is back

        heading("6. Persistence: save to CSV, then load into a brand-new Library");
        Path dir = Path.of("library-data-demo");
        lib.save(dir);
        Library reloaded = Library.load(dir);
        System.out.println("Items after reload:");
        reloaded.getItems().forEach(i -> System.out.println("  " + i));
        System.out.println("Members after reload:");
        reloaded.getMembers().forEach(m -> System.out.println("  " + m));
        System.out.println("Overdue after reload (as of " + TODAY + "): " + reloaded.overdueLoans(TODAY).size()
                + " (the only late loan, B1, had been returned before saving)");
    }

    private static void borrow(Library lib, String memberId, String itemId, LocalDate when) {
        try {
            Loan loan = lib.borrow(memberId, itemId, when);
            System.out.println("OK      " + loan);
        } catch (ItemNotAvailableException | MemberLimitExceededException e) {
            System.out.println("DENIED  " + e.getClass().getSimpleName() + ": " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR   " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    private static void heading(String text) {
        System.out.println();
        System.out.println("--- " + text + " ---");
    }
}
