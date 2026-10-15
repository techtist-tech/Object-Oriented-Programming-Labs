# OOPLab10: File I/O, Lambdas and Streams

## Objectives
- Read/write text files with java.nio.file
- Lambdas, method references, functional interfaces
- Stream pipelines: filter, map, sorted, collect, groupingBy

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab10` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.FileDemo` | Program 10.1 - reading/writing text files (creates students.txt) |
| `programs.StudentRepository` | Program 10.2 - CSV persistence (creates learners.csv) |
| `programs.StreamDemo` | Program 10.3 - lambdas and streams |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.userlines.UserLines` | Basic 1: save 5 typed lines to a file and read them back (INTERACTIVE) |
| `exercises.removeeven.RemoveEven` | Basic 2: Predicate + removeIf to delete even numbers |
| `exercises.textstats.TextStats` | Intermediate 1: count lines, words, characters of a file (default sample.txt, or pass a path as program argument) |
| `exercises.average.AboveAverage` | Intermediate 2: students above the class average, sorted alphabetically |
| `exercises.bands.GradeBands` | Challenge: group learners.csv by band A/B/C and write report_A/B/C.txt |

## Key conclusion for the lab record
Files throw checked IOException, so handle or declare it. Relative paths resolve against the working directory (the project root in IntelliJ). Streams are lazy pipelines that do not modify their source and cannot be reused after a terminal operation.

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.

Note: Programs 10.1 and 10.2 create `students.txt` and `learners.csv` in the project folder when run.
