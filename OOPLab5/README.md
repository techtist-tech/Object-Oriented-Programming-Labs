# OOPLab5: Polymorphism

## Objectives
- Overloading (compile time) vs overriding (run time)
- Upcasting, dynamic dispatch, safe downcasting
- Write code that works for new subclasses unchanged

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab5` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.Calculator` | Program 5.1 - overloading |
| `programs.Shapes` | Program 5.2 - overriding and dynamic dispatch |
| `programs.CastingDemo` | Program 5.3 - casting, instanceof pattern, fields/static are not polymorphic |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.overload.PrintOverload` | Basic 1: print() overloaded for int, double, String, int[] |
| `exercises.animals.AnimalDemo` | Basic 2: Animal[] of Cat, Cow, Duck |
| `exercises.payroll.PayrollDemo` | Intermediate 1: Intern added; payroll loop unchanged |
| `exercises.fieldvsmethod.FieldVsMethod` | Intermediate 2: methods vs fields vs static through a superclass reference (explained) |
| `exercises.payment.PaymentDemo` | Challenge: instanceof design vs polymorphic process(), with comparison |

## Key conclusion for the lab record
Overloading is chosen by the compiler from argument types; overriding is chosen by the JVM from the real object. Fields and static methods follow the declared type. Polymorphic design lets you add new subclasses without editing existing loops.

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.
