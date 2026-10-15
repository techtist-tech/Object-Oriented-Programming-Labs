# Mini-Project Report - Library Management System (Lab 11)

## 1. Objective
Combine the course concepts - classes, encapsulation, inheritance, polymorphism, abstraction, interfaces,
exceptions, collections, streams and file I/O - in one working console application.

## 2. Design summary
| Requirement | Where it is met |
| --- | --- |
| `Book`, `Member`, `Loan` with private fields, validation, `toString` | `Book.java`, `Member.java`, `Loan.java` (constructors/setters reject blank or invalid values) |
| Abstract `LibraryItem` (title, id) with `Book` and `Dvd`, each with its own `loanPeriodDays()` | `LibraryItem.java` (abstract), `Book` = 14 days, `Dvd` = 7 days |
| `Borrowable` interface: `borrow(Member)`, `giveBack()` | `Borrowable.java`, implemented once in `LibraryItem` |
| Custom exceptions, max 3 loans per member | `ItemNotAvailableException`, `MemberLimitExceededException`, `Member.MAX_LOANS = 3` |
| Items and members in `Map<String, ...>` keyed by id | `Library.items`, `Library.members` (`TreeMap`, so listings are ordered by id) |
| Persist to CSV on exit, reload on start | `Library.save(Path)` / `Library.load(Path)`; `LibraryApp` loads at start-up and saves on option 0 |
| Menu: add item, register member, borrow, return, list overdue, search by title (streams) | `LibraryApp.java`; overdue and search are stream pipelines in `Library` |

## 3. Key design decisions
* **Polymorphism in two places:** `Loan` computes its due date by calling `item.loanPeriodDays()` on a
  `LibraryItem` reference, and listings call `type()`, `detail()` - no `instanceof` anywhere.
* **Atomic borrow:** `LibraryItem.borrow()` performs *both* checks (availability, member limit) before it changes
  any state, so a refused request leaves the item available (verified by a test).
* **Encapsulation:** `Member.getActiveLoans()` and `Library.getItems()` return read-only views; loan bookkeeping
  methods are package-private so only `Library` can change them.
* **Checked exceptions** for recoverable business rules the user must handle; `IllegalArgumentException` for bad
  input (unknown id, blank name).
* **CSV safety:** commas, `%` and new lines in titles/names are escaped (`Csv.java`), so "Hidden Figures, Extended Cut"
  survives a save/load. A corrupted data file raises an `IOException` naming the file and line.
* **Time is a parameter:** `borrow`, `returnItem` and `overdueLoans` accept a `LocalDate`, which makes overdue logic
  testable without waiting for real days to pass.

## 4. Testing
`LibraryTests` runs 29 automated checks (borrow/return, limit, overdue with different loan periods, search,
validation, duplicate ids, persistence round-trip including special characters, missing and corrupt files).
`LibraryDemo` shows every scenario with printed output (see `sample_output.txt`).

## 5. How to run
Open the `OOPLab11` folder in IntelliJ IDEA and run `library.LibraryApp` (menu), `library.LibraryDemo` (scripted tour) or
`library.LibraryTests` (checks). Data is stored in `library-data/` in the project folder.

## 6. Conclusions and possible extensions
The abstract class + interface split kept shared rules in one place while letting each item type vary its loan
period. Possible extensions: late fees, reservations, a `Magazine` item type (one new subclass, no changes elsewhere),
and swapping CSV for a database behind the same `save`/`load` methods.
