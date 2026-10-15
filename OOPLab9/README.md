# OOPLab9: Nested Types, Enums, Records and the Object Contract

## Objectives
- Static nested, inner, local and anonymous classes
- Enums and records
- Override equals, hashCode and toString consistently

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab9` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.NestedDemo` | Program 9.1 - four kinds of nested class |
| `programs.EnumDemo` | Program 9.2 - enums with fields and constant-specific bodies |
| `programs.ObjectContract` | Program 9.3 - equals/hashCode/toString and records |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.level.Level` | Basic 1: enum Level with a switch giving advice |
| `exercises.record.RecordDemo` | Basic 2: record Student(name, age) |
| `exercises.book.BookDemo` | Intermediate 1: Book equals/hashCode by ISBN; HashSet rejects duplicates |
| `exercises.linkedlist.LinkedListDemo` | Intermediate 2: LinkedList with private static Node and inner iterator |
| `exercises.coin.Coin` | Challenge: enum Coin and fewestCoins(amount) (has its own main) |

## Key conclusion for the lab record
Equal objects must have equal hash codes, otherwise hash-based collections misbehave. A record gives constructor, accessors, equals, hashCode and toString for free. A static nested class needs no outer instance; an inner class does.

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.
