package testCases.course2.assignment4TwoSum;

import utility.AbstractTestCaseGenerator;
import utility.ClassCaller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An test case generator for course2 assignment4TwoSum.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.course2.assignment2TwoSum [method to call] [solver class] {[args to method]}
 *
 * <p>The [solver class] is used to ensure that test cases with unique solutions are produced.
 */
public class TestCaseGenerator extends AbstractTestCaseGenerator {
  public TestCaseGenerator(String methodToUse, String solverClassName, String[] args) {
    super(methodToUse, solverClassName, args);
  }

  /**
   * Run the TestCaseGenerator.
   *
   * @param args [method to call] [solver class] {[args to method]}
   */
  public static void main(String[] args) {
    AbstractTestCaseGenerator.main(TestCaseGenerator.class, args);
    TestCaseGenerator tcg =
        new TestCaseGenerator(args[0], args[1], Arrays.copyOfRange(args, 2, args.length));
    tcg.generateInputFiles();
  }

  /**
   * Creates 4 unique test case files.
   *
   * <p>Each test case has a list of Longs. The solution for each test case is the number of target
   * values t in the interval [-10000,10000] (inclusive) such that there are distinct numbers x,y in
   * the input file that satisfy x+y=t.
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args the filenameStartingIndex, followed by an array of numbers of longs
   */
  public void random(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    // Iterate through the command line arguments
    int argPointer = 1;
    while (argPointer < args.length) {

      System.out.println("Processing problem size => " + args[argPointer]);
      int numValues = Integer.parseInt(args[argPointer++]);

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<String> solutions = new HashSet<String>();

      while (solutions.size() < 4) {

        // Initialize the list of file lines
        ArrayList<String> lines = new ArrayList<String>();

        // Initialize a set to store values that have already been added to the file.
        HashSet<Long> numsSoFar = new HashSet<Long>();
        // The guaranteed number of sums present in the list so far.
        int countSums = 0;

        for (int i = 0; i < numValues; i++) {
          long l;
          // Occasionally, add a value that satisfies the 2SUM condition.
          // As the number of matches increases, the probability decreases.
          if (!numsSoFar.isEmpty() && ThreadLocalRandom.current().nextInt(countSums + 2) == 1) {
            // Choose a value at random that has already been added to the list.
            long match =
                (long) numsSoFar.toArray()[ThreadLocalRandom.current().nextInt(numsSoFar.size())];
            // Determine the value that will satisfy the match condition.
            l = ThreadLocalRandom.current().nextInt(-10000, 10001) - match;
            countSums++;
          } else {
            // Choose a value at random.
            l = ThreadLocalRandom.current().nextLong(-99999999999L, 100000000000L);
          }
          lines.add("" + l);
          numsSoFar.add(l);
        }

        // Create the filename for the test case.
        String inputFilename = "input_random";
        inputFilename += "_" + filenameStartingIndex;
        inputFilename += "_" + numValues;
        inputFilename += ".txt";

        // Write the input file.
        Path file = Paths.get(inputFilename);
        try {
          Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException exception) {
          exception.printStackTrace();
        }

        String[] filenames = {inputFilename};

        String solution = ClassCaller.callMethod(solverMainMethod, filenames);

        // create an output file for this solution only if it is a unique solution.
        if (!solutions.contains(solution)) {
          // Add this solution to the set of solutions so far.
          solutions.add(solution);
          AbstractTestCaseGenerator.generateOutputFile(inputFilename, solution);
          System.out.println("\t" + solutions.size() + " unique file(s) generated.");
          filenameStartingIndex++;
        }
      }
    }
  }
}
