# Library Management System - Class Diagram

## UML (Mermaid - renders in IntelliJ's Markdown preview and on GitHub)

```mermaid
classDiagram
    class Borrowable {
        <<interface>>
        +borrow(Member) void
        +giveBack() void
    }
    class LibraryItem {
        <<abstract>>
        -String id
        -String title
        -boolean available
        +loanPeriodDays()* int
        +type()* String
        +detail()* String
        +storedValue()* String
        +borrow(Member) void
        +giveBack() void
    }
    class Book {
        -String author
        +loanPeriodDays() int
    }
    class Dvd {
        -int runtimeMinutes
        +loanPeriodDays() int
    }
    class Member {
        +MAX_LOANS$ int
        -String id
        -String name
        -List~Loan~ activeLoans
        +getActiveLoanCount() int
    }
    class Loan {
        -LocalDate borrowDate
        -LocalDate dueDate
        -LocalDate returnDate
        +isOverdue(LocalDate) boolean
    }
    class Library {
        -Map~String,LibraryItem~ items
        -Map~String,Member~ members
        -List~Loan~ loans
        +borrow(memberId,itemId) Loan
        +returnItem(itemId) Loan
        +overdueLoans(LocalDate) List~Loan~
        +searchByTitle(String) List~LibraryItem~
        +save(Path) void
        +load(Path)$ Library
    }
    class ItemNotAvailableException
    class MemberLimitExceededException
    class LibraryApp {
        +main(String[])$ void
    }

    Borrowable <|.. LibraryItem
    LibraryItem <|-- Book
    LibraryItem <|-- Dvd
    Library "1" o-- "*" LibraryItem : items by id
    Library "1" o-- "*" Member : members by id
    Library "1" o-- "*" Loan : history
    Loan "*" --> "1" LibraryItem
    Loan "*" --> "1" Member
    Member "1" o-- "0..3" Loan : active loans
    Exception <|-- ItemNotAvailableException
    Exception <|-- MemberLimitExceededException
    LibraryItem ..> ItemNotAvailableException : throws
    LibraryItem ..> MemberLimitExceededException : throws
    LibraryApp --> Library : uses
```

## Plain-text version

```
                     <<interface>>
                     Borrowable
                     + borrow(Member)
                     + giveBack()
                          ^
                          | implements
                  <<abstract>> LibraryItem
                  - id, title, available
                  + loanPeriodDays()*   <- polymorphic
                     ^              ^
          extends    |              |    extends
              Book (14 days)     Dvd (7 days)
              - author           - runtimeMinutes

   Library  ----------------------------------------------+
   - Map<String, LibraryItem> items      (by id)          |
   - Map<String, Member>      members    (by id)          | uses
   - List<Loan>               loans                       |
   + borrow / returnItem / overdueLoans / searchByTitle   v
   + save(Path) / load(Path)  --> items.csv, members.csv, loans.csv

   Loan   : item (LibraryItem), member (Member), borrowDate, dueDate, returnDate
   Member : id, name, activeLoans (max 3)

   Checked exceptions:  ItemNotAvailableException, MemberLimitExceededException   (both extend Exception)
```
