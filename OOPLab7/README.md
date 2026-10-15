# OOPLab7: Exception Handling

## Objectives
- Checked vs unchecked exceptions
- try / catch / finally, throw, throws
- Custom exceptions and try-with-resources

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab7` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.ExceptionBasics` | Program 7.1 - try, catch, finally, multi-catch |
| `programs.Wallet` | Program 7.2 - custom checked exception (InsufficientFundsException.java) |
| `programs.ResourceDemo` | Program 7.3 - try-with-resources and exception chaining |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.division.DivisionDemo` | Basic 1: Scanner division handling bad input and divide-by-zero (INTERACTIVE - type q to quit) |
| `exercises.array.ArrayAccess` | Basic 2: catching ArrayIndexOutOfBoundsException |
| `exercises.age.AgeValidator` | Intermediate 1: checked InvalidAgeException (0-120) |
| `exercises.bank.BankDemo` | Intermediate 2: BankAccount.withdraw throws InsufficientFundsException (trade-offs in BankAccount.java) |
| `exercises.finallydemo.FinallyReturn` | Challenge: return inside try + finally (prediction and explanation in source) |

## Key conclusion for the lab record
finally always runs when control leaves try (return value is remembered first). Checked exceptions force callers to decide; a boolean return can be ignored by mistake. try-with-resources closes resources in reverse order automatically.

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.
