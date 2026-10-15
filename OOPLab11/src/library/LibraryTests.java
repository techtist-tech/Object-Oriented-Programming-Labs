package library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;

/**
 * Plain-Java checks (no JUnit needed, so it runs in any IDE). Each check prints PASS or FAIL;
 * the program exits with a non-zero code if any check fails.
 */
public class LibraryTests {

    private static int passed = 0;
    private static int failed = 0;
    private static final LocalDate TODAY = LocalDate.of(2026, 10, 1);

    public static void main(String[] args) throws Exception {
        testBorrowMarksItemUnavailable();
        testCannotBorrowTwice();
        testMemberLimit();
        testFailedBorrowLeavesStateUnchanged();
        testReturn();
        testOverdueUsesPolymorphicLoanPeriod();
        testSearchIsCaseInsensitiveAndSorted();
        testValidation();
        testDuplicateIds();
        testPersistenceRoundTrip();

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
        if (failed > 0) System.exit(1);
    }

    // ------------------------------------------------------------------ tests
    static void testBorrowMarksItemUnavailable() throws Exception {
        Library lib = sample();
        lib.borrow("M1", "B1", TODAY);
        check("borrow marks the item unavailable", !item(lib, "B1").isAvailable());
        check("borrow records the loan on the member", member(lib, "M1").getActiveLoanCount() == 1);
    }

    static void testCannotBorrowTwice() throws Exception {
        Library lib = sample();
        lib.borrow("M1", "B1", TODAY);
        check("second borrow of the same item throws ItemNotAvailableException",
                throwsChecked(() -> lib.borrow("M2", "B1", TODAY), ItemNotAvailableException.class));
    }

    static void testMemberLimit() throws Exception {
        Library lib = sample();
        lib.borrow("M1", "B1", TODAY);
        lib.borrow("M1", "B2", TODAY);
        lib.borrow("M1", "D1", TODAY);
        check("4th loan throws MemberLimitExceededException",
                throwsChecked(() -> lib.borrow("M1", "D2", TODAY), MemberLimitExceededException.class));
        check("member still has exactly 3 loans", member(lib, "M1").getActiveLoanCount() == 3);
    }

    static void testFailedBorrowLeavesStateUnchanged() throws Exception {
        Library lib = sample();
        lib.borrow("M1", "B1", TODAY);
        lib.borrow("M1", "B2", TODAY);
        lib.borrow("M1", "D1", TODAY);
        throwsChecked(() -> lib.borrow("M1", "D2", TODAY), MemberLimitExceededException.class);
        check("item refused because of the member limit stays available", item(lib, "D2").isAvailable());
    }

    static void testReturn() throws Exception {
        Library lib = sample();
        lib.borrow("M1", "B1", TODAY);
        lib.returnItem("B1", TODAY.plusDays(2));
        check("returned item is available again", item(lib, "B1").isAvailable());
        check("member's active loan count drops back to 0", member(lib, "M1").getActiveLoanCount() == 0);
        check("returning an item that is not on loan is rejected",
                throwsUnchecked(() -> lib.returnItem("B1", TODAY), IllegalArgumentException.class));
    }

    static void testOverdueUsesPolymorphicLoanPeriod() throws Exception {
        Library lib = sample();
        lib.borrow("M1", "B1", TODAY.minusDays(10));   // book: 14 days  -> due in 4 days, NOT overdue
        lib.borrow("M1", "D1", TODAY.minusDays(10));   // DVD:   7 days  -> due 3 days ago, overdue
        check("a book (14 days) borrowed 10 days ago is not overdue", !overdueIds(lib).contains("B1"));
        check("a DVD (7 days) borrowed 10 days ago is overdue", overdueIds(lib).contains("D1"));
    }

    static void testSearchIsCaseInsensitiveAndSorted() {
        Library lib = sample();
        var found = lib.searchByTitle("JAVA");
        check("search ignores case", found.size() == 2);
        check("search results are sorted by title",
                found.stream().map(LibraryItem::getTitle).toList()
                        .equals(found.stream().map(LibraryItem::getTitle).sorted(Comparator.naturalOrder()).toList()));
    }

    static void testValidation() {
        check("blank book title is rejected", throwsUnchecked(() -> new Book("X", " ", "A"), IllegalArgumentException.class));
        check("DVD with zero runtime is rejected", throwsUnchecked(() -> new Dvd("X", "T", 0), IllegalArgumentException.class));
        check("blank member name is rejected", throwsUnchecked(() -> new Member("X", ""), IllegalArgumentException.class));
        check("borrowing for an unknown member is rejected",
                throwsUnchecked(() -> sample().borrow("NOPE", "B1", TODAY), IllegalArgumentException.class));
    }

    static void testDuplicateIds() {
        Library lib = sample();
        check("duplicate item id is rejected",
                throwsUnchecked(() -> lib.addItem(new Book("B1", "Other", "Someone")), IllegalArgumentException.class));
        check("duplicate member id is rejected",
                throwsUnchecked(() -> lib.registerMember(new Member("M1", "Other")), IllegalArgumentException.class));
    }

    static void testPersistenceRoundTrip() throws IOException, Exception {
        Path dir = Files.createTempDirectory("library-test");
        Library lib = sample();
        lib.addItem(new Book("B9", "Commas, %percent% and more", "A. Author"));
        lib.borrow("M1", "B1", TODAY.minusDays(30));                // still out, overdue
        lib.borrow("M2", "D1", TODAY.minusDays(1));
        lib.returnItem("D1", TODAY);                                 // returned loan is kept in history
        lib.save(dir);

        Library back = Library.load(dir);
        check("all items survive a save/load", back.getItems().size() == lib.getItems().size());
        check("a title with commas and % survives", item(back, "B9").getTitle().equals("Commas, %percent% and more"));
        check("an item that was on loan is still on loan after reload", !item(back, "B1").isAvailable());
        check("a returned item is available after reload", item(back, "D1").isAvailable());
        check("the member's active loan is restored", member(back, "M1").getActiveLoanCount() == 1);
        check("overdue loans are the same after reload", overdueIds(back).equals(overdueIds(lib)));
        check("loan history is preserved", back.getLoanHistory().size() == 2);
        check("DVD runtime survives", ((Dvd) item(back, "D1")).getRuntimeMinutes() == 120);

        check("loading a missing folder gives an empty library", Library.load(dir.resolve("nope")).getItems().isEmpty());

        Files.writeString(dir.resolve("items.csv"), "BOOK,B1\n");           // corrupt file
        check("a corrupt data file is reported as IOException", throwsChecked(() -> Library.load(dir), IOException.class));
    }

    // ------------------------------------------------------------------ helpers
    static Library sample() {
        Library lib = new Library();
        lib.addItem(new Book("B1", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B2", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B3", "Java: The Complete Reference", "Herbert Schildt"));
        lib.addItem(new Dvd("D1", "The Social Network", 120));
        lib.addItem(new Dvd("D2", "Hidden Figures", 127));
        lib.registerMember(new Member("M1", "Tendai"));
        lib.registerMember(new Member("M2", "Rudo"));
        return lib;
    }

    static LibraryItem item(Library lib, String id) {
        return lib.getItems().stream().filter(i -> i.getId().equals(id)).findFirst().orElseThrow();
    }

    static Member member(Library lib, String id) {
        return lib.getMembers().stream().filter(m -> m.getId().equals(id)).findFirst().orElseThrow();
    }

    static java.util.List<String> overdueIds(Library lib) {
        return lib.overdueLoans(TODAY).stream().map(l -> l.getItem().getId()).toList();
    }

    interface CheckedAction { void run() throws Exception; }

    static boolean throwsChecked(CheckedAction a, Class<? extends Exception> expected) {
        try {
            a.run();
            return false;
        } catch (Exception e) {
            return expected.isInstance(e);
        }
    }

    static boolean throwsUnchecked(CheckedAction a, Class<? extends RuntimeException> expected) {
        try {
            a.run();
            return false;
        } catch (Exception e) {
            return expected.isInstance(e);
        }
    }

    static void check(String description, boolean condition) {
        if (condition) { passed++; System.out.println("PASS  " + description); }
        else           { failed++; System.out.println("FAIL  " + description); }
    }
}
