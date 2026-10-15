package library;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Console menu for the Library Management System. Data is loaded from the "library-data" folder at start-up
 * and saved back when you choose 0 (Save and exit).
 */
public class LibraryApp {

    private static final Path DATA_DIR = Path.of("library-data");

    private final Scanner in = new Scanner(System.in);
    private Library library;

    public static void main(String[] args) {
        new LibraryApp().run();
    }

    private void run() {
        try {
            library = Library.load(DATA_DIR);
            System.out.println("Loaded " + library.getItems().size() + " items and "
                    + library.getMembers().size() + " members from " + DATA_DIR + "/");
        } catch (IOException e) {
            System.out.println("Could not load saved data: " + e.getMessage());
            System.out.println("Starting with an empty library.");
            library = new Library();
        }

        boolean running = true;
        while (running) {
            printMenu();
            String choice = prompt("Choose an option");
            if (choice == null) {                 // end of input (e.g. piped input ran out): save and leave
                choice = "0";
            }
            try {
                switch (choice) {
                    case "1" -> addItem();
                    case "2" -> registerMember();
                    case "3" -> borrow();
                    case "4" -> giveBack();
                    case "5" -> listOverdue();
                    case "6" -> search();
                    case "7" -> library.getItems().forEach(System.out::println);
                    case "8" -> library.getMembers().forEach(System.out::println);
                    case "0" -> running = false;
                    default -> System.out.println("Unknown option: " + choice);
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                System.out.println("DENIED: " + e.getMessage());
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

        try {
            library.save(DATA_DIR);
            System.out.println("Data saved to " + DATA_DIR + "/. Goodbye!");
        } catch (IOException e) {
            System.out.println("COULD NOT SAVE: " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== Library Management System ===");
        System.out.println("1) Add item (book or DVD)");
        System.out.println("2) Register member");
        System.out.println("3) Borrow an item");
        System.out.println("4) Return an item");
        System.out.println("5) List overdue items");
        System.out.println("6) Search by title");
        System.out.println("7) List all items");
        System.out.println("8) List all members");
        System.out.println("0) Save and exit");
    }

    private void addItem() {
        String type = prompt("Type (book/dvd)");
        String id = prompt("Item id");
        String title = prompt("Title");
        if ("book".equalsIgnoreCase(type)) {
            library.addItem(new Book(id, title, prompt("Author")));
        } else if ("dvd".equalsIgnoreCase(type)) {
            library.addItem(new Dvd(id, title, parseInt(prompt("Runtime in minutes"))));
        } else {
            throw new IllegalArgumentException("Type must be 'book' or 'dvd'");
        }
        System.out.println("Item added.");
    }

    private void registerMember() {
        library.registerMember(new Member(prompt("Member id"), prompt("Member name")));
        System.out.println("Member registered.");
    }

    private void borrow() throws ItemNotAvailableException, MemberLimitExceededException {
        String memberId = prompt("Member id");
        String itemId = prompt("Item id");
        String date = prompt("Borrow date yyyy-mm-dd (blank = today)");
        LocalDate when = (date == null || date.isBlank()) ? LocalDate.now() : LocalDate.parse(date);
        Loan loan = library.borrow(memberId, itemId, when);
        System.out.println("OK: " + loan);
    }

    private void giveBack() {
        Loan loan = library.returnItem(prompt("Item id"));
        System.out.println("Returned: " + loan);
    }

    private void listOverdue() {
        List<Loan> overdue = library.overdueLoans(LocalDate.now());
        if (overdue.isEmpty()) {
            System.out.println("Nothing is overdue.");
            return;
        }
        for (Loan l : overdue) {
            System.out.println(l + "  [" + l.daysOverdue(LocalDate.now()) + " days overdue]");
        }
    }

    private void search() {
        List<LibraryItem> found = library.searchByTitle(prompt("Title contains"));
        if (found.isEmpty()) System.out.println("No matches.");
        found.forEach(System.out::println);
    }

    private String prompt(String label) {
        System.out.print(label + ": ");
        return in.hasNextLine() ? in.nextLine().trim() : null;
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'" + s + "' is not a whole number");
        }
    }
}
