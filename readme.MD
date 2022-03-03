# Running it

```shell script
mvn clean test
```

# Notes on the exercise

1. Instruction says FastestComparator.compare must not be modified, but it throws NumberFormatException for non integer values from the example JSON provided with the exercise. So to account for this I strip out any bad input (nulls, strings, booleans, empty values) after the list is read from the file
2. I have shown two approaches to search the list for the number. 
    - Using java8 the list can be scanned using a parallel stream to hopefully make the search as optimal as possible while keeping the code nice and succint.
    - Recursion to divide and conquer the problem
3. A few tests both for the list searching and for the JSON data loading 