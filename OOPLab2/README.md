# OOPLab2: Constructors, this and static Members

## Objectives
- Initialise objects with constructors
- Overload and chain constructors with this(...)
- Distinguish instance and static members; use initialiser blocks

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab2` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.Book` | Program 2.1 - constructors, this, static counter, initialiser blocks |
| `programs.InitOrder` | Program 2.2 - order of initialisation |
| `programs.Point` | Program 2.3 - copy constructor |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.circle.CircleDemo` | Basic 1-2: Circle with default radius and a static instance counter |
| `exercises.employee.EmployeeDemo` | Intermediate 1: Employee with static auto-id, 3 overloaded constructors, copy constructor |
| `exercises.blocks.BlockOrder` | Intermediate 2: two static + two instance blocks (prediction in source comment, then verified) |
| `exercises.temperature.TemperatureDemo` | Challenge: static factory methods + private constructor (explanation in Temperature.java) |

## Key conclusion for the lab record
Static members belong to the class (one copy); instance members belong to each object. Static blocks run once at class load, instance blocks before every constructor body. Static factory methods have descriptive names, which overloaded constructors cannot provide.

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.
