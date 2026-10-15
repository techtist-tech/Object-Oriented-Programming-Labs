# OOPLab1: Classes and Objects

## Objectives
- Define a class with fields and methods
- Create objects with new and use the dot operator
- Understand references, aliasing and null

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab1` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.Student` | Program 1.1 - Student class (fields, display(), isHonours()) |
| `programs.ReferenceDemo` | Program 1.2 - references, aliasing, == and null |
| `programs.Course` | Program 1.3 - an object that holds another object |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.car.CarDemo` | Basic 1-2: Car (brand, year, mileage), display(), isAntique() |
| `exercises.rectangle.RectangleDemo` | Intermediate 1-2: largest of 5 rectangles; aliasing prediction r2 = r1 (explained in the source comments) |
| `exercises.person.PersonDemo` | Challenge: Person has an Address; chained dot expression and the null case |

## Key conclusion for the lab record
An object variable holds a reference, so `Rectangle r2 = r1;` creates an alias, not a copy: changing `r2.width` also changes `r1.width`. Using a chained dot expression on a null reference throws NullPointerException, so check for null first.

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.
