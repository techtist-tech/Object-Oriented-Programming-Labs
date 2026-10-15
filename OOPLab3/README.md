# OOPLab3: Encapsulation and Access Modifiers

## Objectives
- Hide data with private fields
- Validate in setters and constructors
- Avoid leaking mutable internals

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab3` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.BankDemo` | Program 3.1 - safe BankAccount (BankAccount.java) |
| `programs.Team` | Program 3.2 - the defensive-copy trap |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.student.StudentDemo` | Basic 1-2: Student with validated marks (0-100) and derived getGrade() |
| `exercises.history.HistoryDemo` | Intermediate 1: BankAccount with transaction history; getter returns an unmodifiable copy |
| `exercises.immutable.ImmutablePersonDemo` | Intermediate 2: ImmutablePerson (each design choice explained in the source) |
| `exercises.temperature.TemperatureDemo` | Challenge: Temperature stored in kelvin; rejects values below absolute zero |

## Key conclusion for the lab record
Private fields plus validated constructors and setters keep objects in a valid state. Getters must not return references to internal mutable objects. An immutable class is final, has private final fields, no setters and defensive copies.

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.
