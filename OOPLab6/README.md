# OOPLab6: Abstraction, Abstract Classes and Interfaces

## Objectives
- Abstract classes and abstract methods
- Interfaces with default and static methods
- Choose between abstract class and interface; use Comparable

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab6` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.VehicleDemo` | Program 6.1 - abstract class |
| `programs.InterfaceDemo` | Program 6.2 - interfaces, default/static methods, multiple implementation |
| `programs.ComparableDemo` | Program 6.3 - Comparable |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.shape.ShapeDemo` | Basic 1: abstract Shape; Circle, Rectangle, Square |
| `exercises.flyable.FlyableDemo` | Basic 2: Flyable for unrelated Bird and Aeroplane |
| `exercises.account.AccountDemo` | Intermediate 1: abstract Account with abstract monthlyFee() |
| `exercises.student.StudentDemo` | Intermediate 2: Comparable by name + Comparator by marks |
| `exercises.diamond.DiamondDemo` | Challenge: conflicting default methods resolved with InterfaceName.super.hello() (rule explained) |

## Key conclusion for the lab record
Use an abstract class for shared code and state among closely related classes; use an interface for a capability unrelated classes can share. If two interfaces supply the same default method, the class must override it and may pick a parent version with X.super.method().

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.
