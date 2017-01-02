import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Creates example input files for this assignment.
 *
 * execution
 * java -cp build/classes/main
 * beaunus.stanford.algs.course3.assignment4.InputFileGenerator knapsack-example
 * 4 4 10 10 10 100 100 10 100 100 100 1000 1000 100 1000 1000 10000 1000 100000
 * 2000 1000000 2000 2000000 2000
 *
 * @author beaunus
 *
 */
public class StanfordAlgsCourse3Assignment4InputFileGenerator {

  /**
   * Reads the arguments and creates 4 example files for each (size, n) pair.
   *
   * The output filenames will look like this:
   * knapsack-example-w-[size]-n-[n]-solution-[solution].txt
   *
   * @param args
   *          knapsack size, number of items, ...
   */
  public static void main(String[] args) {
    if (args.length < 3) {
      System.out.println("usage: InputFileGenerator "
          + "{[knapsack size], [number of items]}");
      System.out.println();
      System.out.println("4 unique files will be created for each of the "
          + "([knapsack size], [number of items]) pairs.");
      return;
    }
    // Iterate through the command line arguments
    int argPointer = 0;
    while (argPointer < args.length) {

      // Read arguments into variables.
      int size = Integer.parseInt(args[argPointer++]);
      int numberOfItems = Integer.parseInt(args[argPointer++]);

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<Integer> solutions = new HashSet<Integer>();

      while (solutions.size() < 4) {
        int[] values = new int[numberOfItems];
        int[] weights = new int[numberOfItems];
        int maxValue = size / 2;
        int maxWeight = size / 2;

        // Generate a random set of weights and values
        // The "+1" ensures that 0 is excluded from the possibilities.
        for (int i = 0; i < numberOfItems; i++) {
          values[i] = ThreadLocalRandom.current().nextInt(maxValue) + 1;
          weights[i] = ThreadLocalRandom.current().nextInt(maxWeight) + 1;
        }

        // construct a KnapsackProblem to be used as an example
        KnapsackProblem kp = new KnapsackProblem(size, numberOfItems, values,
            weights);
        int solution = kp.solutionValue();

        // create a file for this solution only if it is a unique solution.
        if (!solutions.contains(solution)) {
          // Create the filename for the test case.
          String filename = "knapsack-example";
          filename += "-w-" + size;
          filename += "-n-" + numberOfItems;
          filename += "-solution-" + solution;
          filename += ".txt";

          // Add this solution to the set of solutions so far.
          solutions.add(solution);

          // Write the file.
          Path file = Paths.get(filename);
          try {
            Files.write(file, kp.toFileStringList(), Charset.forName("UTF-8"));
          } catch (IOException exception) {
            exception.printStackTrace();
          }
        }
      }
    }
  }
}
