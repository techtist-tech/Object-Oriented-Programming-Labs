package library;

public class Book extends LibraryItem {
    private final String author;

    public Book(String id, String title, String author) {
        super(id, title);
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Book author is required");
        this.author = author.trim();
    }

    public String getAuthor() { return author; }

    @Override public int loanPeriodDays() { return 14; }
    @Override public String type()        { return "BOOK"; }
    @Override public String detail()      { return author; }
    @Override public String storedValue() { return author; }
}
