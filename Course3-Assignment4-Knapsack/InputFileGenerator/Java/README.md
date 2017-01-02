# stanford-algs

## Course 3 - Greedy Algorithms, Minimum Spanning Trees, and Dynamic Programming

### Assignment 4 - Knapsack - Input File Generator - Java

This is a Java implementation of a test case generator for this assignment.

`StanfordAlgsCourse3Assignment4InputFileGenerator.java` relies on the following API:

```java
public class KnapsackProblem {
  /**
   * Creates a KnapsackProblem with the given parameters.
   *
   * @param size
   *          the size of the knapsack
   * @param numberOfItems
   *          the number of available items
   * @param values
   *          the values of the available items
   * @param weights
   *          the weights of the available items
   */
  public KnapsackProblem(int size, int numberOfItems, int[] values,
      int[] weights) {
  }

  /**
   * Returns an Iterable of this KnapsackProblem, to be used in
   * generating example files.
   *
   * Each string should be a single line from the test case file, including the
   * first line.
   *
   * @return the Iterable object of Strings
   */
  public Iterable<String> toFileStringList() {
  }

  /**
   * The value of the solution.
   *
   * @return the value of the solution.
   */
  public int solutionValue() {
  }
}

```
