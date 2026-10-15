# OOPLab8: Generics and the Collections Framework

## Objectives
- Generic classes and methods, bounded types
- List, Set, Map, Queue/Deque
- Sorting with Comparable and Comparator

## How to run in IntelliJ IDEA
1. **File > Open** and choose this `OOPLab8` folder (if asked for a Project SDK, pick any JDK 17 or newer).
2. Open a class listed below and click the green arrow next to its `main` method.
   Files are read/written relative to the project root (the default working directory).

## Programs from the manual
| Class (package.Class) | What it shows |
| --- | --- |
| `programs.GenericsDemo` | Program 8.1 - Box<T>, Pair<K,V>, generic methods |
| `programs.CollectionsDemo` | Program 8.2 - List, Set, Map, Deque |
| `programs.SortingDemo` | Program 8.3 - Comparable and Comparator |

## Exercises (Basic / Intermediate / Challenge)
| Class (package.Class) | What it shows |
| --- | --- |
| `exercises.numbers.NumberList` | Basic 1: 10 integers - sum, maximum, reversed |
| `exercises.duplicates.RemoveDuplicates` | Basic 2: remove duplicates with a Set |
| `exercises.stack.StackDemo` | Intermediate 1: generic Stack<T> backed by ArrayList |
| `exercises.wordfreq.WordFrequency` | Intermediate 2: word frequencies sorted by count descending |
| `exercises.sort.BubbleSort` | Challenge: generic bubbleSort(T[]); why int[] does not work (explained) |

## Key conclusion for the lab record
Generics move type errors to compile time and work only with reference types (hence Integer[] not int[]). Choose the collection by need: List (order, duplicates), Set (uniqueness), Map (lookup by key), Deque (stack/queue).

## Sample output
See `sample_output.txt` - the output of every class with a `main` method.
