package library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** The core service: keeps items and members in Maps keyed by id, handles borrowing, searching and persistence. */
public class Library {
    private final Map<String, LibraryItem> items = new TreeMap<>();
    private final Map<String, Member> members = new TreeMap<>();
    private final List<Loan> loans = new ArrayList<>();          // full history, active and returned

    // ---------------------------------------------------------------- registration
    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("An item with id " + item.getId() + " already exists");
        }
        items.put(item.getId(), item);
    }

    public void registerMember(Member member) {
        if (members.containsKey(member.getId())) {
            throw new IllegalArgumentException("A member with id " + member.getId() + " already exists");
        }
        members.put(member.getId(), member);
    }

    public Collection<LibraryItem> getItems()   { return Collections.unmodifiableCollection(items.values()); }
    public Collection<Member> getMembers()      { return Collections.unmodifiableCollection(members.values()); }
    public List<Loan> getLoanHistory()          { return Collections.unmodifiableList(loans); }

    // ---------------------------------------------------------------- borrowing
    public Loan borrow(String memberId, String itemId)
            throws ItemNotAvailableException, MemberLimitExceededException {
        return borrow(memberId, itemId, LocalDate.now());
    }

    public Loan borrow(String memberId, String itemId, LocalDate when)
            throws ItemNotAvailableException, MemberLimitExceededException {
        // 1. look up member and item (throw IllegalArgumentException if missing)
        Member member = members.get(memberId);
        if (member == null) throw new IllegalArgumentException("No such member: " + memberId);
        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("No such item: " + itemId);

        // 2. check availability and the member's loan limit (the item does both checks, and only
        //    marks itself unavailable if BOTH pass, so a failed attempt leaves everything unchanged)
        item.borrow(member);

        // 3. create a Loan, record it on the member and in the history
        Loan loan = new Loan(item, member, when);
        member.addLoan(loan);
        loans.add(loan);
        return loan;
    }

    public Loan returnItem(String itemId) {
        return returnItem(itemId, LocalDate.now());
    }

    public Loan returnItem(String itemId, LocalDate when) {
        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("No such item: " + itemId);

        Loan loan = loans.stream()
                .filter(l -> l.getItem() == item && !l.isReturned())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item " + itemId + " is not currently on loan"));

        loan.markReturned(when);
        loan.getMember().removeLoan(loan);
        item.giveBack();
        return loan;
    }

    // ---------------------------------------------------------------- queries (streams)
    public List<Loan> overdueLoans(LocalDate today) {
        return loans.stream()
                .filter(l -> l.isOverdue(today))
                .sorted(Comparator.comparing(Loan::getDueDate))
                .toList();
    }

    public List<LibraryItem> searchByTitle(String text) {
        return items.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(text.toLowerCase()))
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .toList();
    }

    // ---------------------------------------------------------------- persistence (CSV)
    private static final String ITEMS_FILE = "items.csv";
    private static final String MEMBERS_FILE = "members.csv";
    private static final String LOANS_FILE = "loans.csv";

    /** Writes items.csv, members.csv and loans.csv into the directory (created if necessary). */
    public void save(Path dir) throws IOException {
        Files.createDirectories(dir);

        List<String> itemLines = new ArrayList<>();
        for (LibraryItem i : items.values()) {
            itemLines.add(String.join(",", i.type(), Csv.escape(i.getId()), Csv.escape(i.getTitle()), Csv.escape(i.storedValue())));
        }
        Files.write(dir.resolve(ITEMS_FILE), itemLines);

        List<String> memberLines = new ArrayList<>();
        for (Member m : members.values()) {
            memberLines.add(String.join(",", Csv.escape(m.getId()), Csv.escape(m.getName())));
        }
        Files.write(dir.resolve(MEMBERS_FILE), memberLines);

        List<String> loanLines = new ArrayList<>();
        for (Loan l : loans) {
            loanLines.add(String.join(",", Csv.escape(l.getItem().getId()), Csv.escape(l.getMember().getId()),
                    l.getBorrowDate().toString(), l.isReturned() ? l.getReturnDate().toString() : ""));
        }
        Files.write(dir.resolve(LOANS_FILE), loanLines);
    }

    /** Reads the three files back. If the directory has no data yet, returns an empty library. */
    public static Library load(Path dir) throws IOException {
        Library lib = new Library();
        if (!Files.isDirectory(dir)) return lib;

        for (String line : readLines(dir.resolve(ITEMS_FILE))) {
            try {
                String[] p = Csv.parse(line);
                LibraryItem item = switch (p[0]) {
                    case "BOOK" -> new Book(p[1], p[2], p[3]);
                    case "DVD"  -> new Dvd(p[1], p[2], Integer.parseInt(p[3]));
                    default -> throw new IllegalArgumentException("unknown item type " + p[0]);
                };
                lib.addItem(item);
            } catch (RuntimeException e) {
                throw new IOException("Corrupt line in " + ITEMS_FILE + ": \"" + line + "\" (" + e.getMessage() + ")", e);
            }
        }

        for (String line : readLines(dir.resolve(MEMBERS_FILE))) {
            try {
                String[] p = Csv.parse(line);
                lib.registerMember(new Member(p[0], p[1]));
            } catch (RuntimeException e) {
                throw new IOException("Corrupt line in " + MEMBERS_FILE + ": \"" + line + "\" (" + e.getMessage() + ")", e);
            }
        }

        for (String line : readLines(dir.resolve(LOANS_FILE))) {
            try {
                String[] p = Csv.parse(line);
                LibraryItem item = lib.items.get(p[0]);
                Member member = lib.members.get(p[1]);
                if (item == null || member == null) throw new IllegalArgumentException("unknown item or member");
                Loan loan = new Loan(item, member, LocalDate.parse(p[2]));
                lib.loans.add(loan);
                if (p[3].isEmpty()) {                       // still out: restore the "on loan" state
                    item.setAvailable(false);
                    member.addLoan(loan);
                } else {
                    loan.markReturned(LocalDate.parse(p[3]));
                }
            } catch (RuntimeException e) {
                throw new IOException("Corrupt line in " + LOANS_FILE + ": \"" + line + "\" (" + e.getMessage() + ")", e);
            }
        }
        return lib;
    }

    private static List<String> readLines(Path file) throws IOException {
        if (!Files.exists(file)) return List.of();
        return Files.readAllLines(file).stream().filter(l -> !l.isBlank()).toList();
    }
}
