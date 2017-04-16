package testCases.course3.assignment4Knapsack;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import utility.ClassCaller;
import utility.TestCaseGeneratorSuperclass;

/**
 * An test case generator for course3 assignment4Knapsack.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.course3.assignment4Knapsack.question1 [method to call] [solver class] {[args to
 * method]}
 *
 * <p>The [solver class] is used to ensure that test cases with unique solutions are produced.
 */
public class TestCaseGenerator extends TestCaseGeneratorSuperclass {
  /**
   * Generate the test cases.
   *
   * @param args [method to call] [solver class] {[args to method]}
   */
  public static void main(String[] args) {
    // Confirm that the command-line arguments are valid.
    if (args.length < 2) {
      System.out.println("usage:");
      System.out.println(
          TestCaseGenerator.class.getName()
              + " [method to call] [solver class] {[args to method]}");
      // Display all available methods for generating an input file.
      System.out.println("\nAvailable methods: ");
      for (Method method : TestCaseGenerator.class.getDeclaredMethods()) {
        if (!method.getName().equals("main")) {
          System.out.println("\t" + method.getName());
        }
      }
      System.out.println();
      System.exit(0);
    }

    // Retrieve the main method of the solver class;
    Method solverMainMethod = ClassCaller.getMainMethod(args[1]);

    // Since we want to get the main Method, the argTypes is a String[].
    Class<?>[] argTypes = new Class[] {Method.class, String[].class};
    try {
      Method methodToCall = TestCaseGenerator.class.getDeclaredMethod(args[0], argTypes);
      methodToCall.invoke(null, solverMainMethod, Arrays.copyOfRange(args, 2, args.length));
    } catch (NoSuchMethodException e) {
      System.out.println("The specified [method to call] does not exist.");
      e.printStackTrace();
      System.exit(0);
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates 4 example files for each (size, n) pair.
   *
   * <p>The values and weights both have a maximum value of (size/2). 
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args an array of (size, n) pairs
   */
  private static void random(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    int argPointer = 1;
    while (argPointer < args.length) {

      // Read arguments into variables.
      int size = Integer.parseInt(args[argPointer++]);
      int numberOfItems = Integer.parseInt(args[argPointer++]);

      System.out.println(
          "Processing (size, numberOfItems) => (" + size + ", " + numberOfItems + ")");

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<String> solutions = new HashSet<String>();

      while (solutions.size() < 4) {
        int[] values = new int[numberOfItems];
        int[] weights = new int[numberOfItems];
        int maxValue = size / 2;
        int maxWeight = size / 2;

        // Initialize the list of file lines
        LinkedList<String> lines = new LinkedList<String>();

        // Add the first line to lines.
        lines.add(size + " " + numberOfItems);

        // Generate a random set of weights and values
        // The "+1" ensures that 0 is excluded from the possibilities.
        for (int i = 0; i < numberOfItems; i++) {
          values[i] = ThreadLocalRandom.current().nextInt(maxValue) + 1;
          weights[i] = ThreadLocalRandom.current().nextInt(maxWeight) + 1;
          lines.add(values[i] + " " + weights[i]);
        }

        // Create the filename for the test case input file.
        String inputFilename = "input_random_" + filenameStartingIndex;
        inputFilename += "_" + size;
        inputFilename += "_" + numberOfItems;
        inputFilename += ".txt";

        // Write the input file.
        Path file = Paths.get(inputFilename);
        try {
          Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException exception) {
          exception.printStackTrace();
        }

        // Obtain the solution
        String[] filenames = {inputFilename};
        String solution = ClassCaller.callMethod(solverMainMethod, filenames);

        // create a file for this solution only if it is a unique solution.
        if (!solutions.contains(solution)) {
          // Add this solution to the set of solutions so far.
          solutions.add(solution);
          generateOutputFile(inputFilename, solution);
          System.out.println("\t" + solutions.size() + " unique file(s) generated.");
          filenameStartingIndex++;
        }
      }
    }
  }
}
