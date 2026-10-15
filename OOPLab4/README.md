# OOPLab4: Inheritance

## Objectives
- Create subclasses with extends
- Use super for constructors and methods
- Understand constructor order, final, and composition vs inheritance

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab4` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.CompanyDemo` | Program 4.1 - Employee / Manager / Developer |
| `programs.Chain` | Program 4.2 - constructor order with three levels |
| `programs.CompositionDemo` | Program 4.3 - final class, toString, composition |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.person.PersonDemo` | Basic 1-2: Person, Student, Teacher share one display() |
| `exercises.vehicle.VehicleDemo` | Intermediate 1: Vehicle -> Car -> ElectricCar, constructor call order |
| `exercises.noargs.NoArgsDemo` | Intermediate 2: why `Dog extends Animal` fails without super(name) (explained in Animal.java) |
| `exercises.stack.StackDemo` | Challenge: Stack by inheritance (BadStack) vs composition (GoodStack) |

## Key conclusion for the lab record
Superclass constructors always run first; if the superclass has no no-argument constructor the subclass must call super(...) explicitly. Use inheritance only for true is-a relationships; a Stack has a list, it is not a list.

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.
