/*
 * This is a template for creating a TestCaseGenerator class.
 *
 * 1. Copy this file into the appropriate folder. For example: "testCases/course1/assignment2"
 * 2. Change the declared package name to match the folder structure.
 * 3. Implement "yourMethod".
 * 4. Remove this template comment.
 * 5. Win!
 */

// Be sure to put the appropriate package name
package utility.template;

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

import utility.ClassCaller;
import utility.TestCaseGeneratorSuperclass;

/**
 * An test case generator for courseX assignmentX.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.courseX.assignmentX.questionX [method to call] [solver class] {[args to
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
   * IMPLEMENT A METHOD TO GENERATE TEST CASE FILES.
   *
   * <p>Your code should go here.
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args an array of {WHAT DOES YOUR ARGUMENT ARRAY LOOK LIKE?}
   */
  private static void yourMethod(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    int argPointer = 1;
    while (argPointer < args.length) {

      // Read arguments into variables.

      System.out.println("Processing {WHAT DO THE ARGUMENTS SPECIFY} => " + "YOUR ARGUMENT VALUES");

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<String> solutions = new HashSet<String>();

      while (solutions.size() < 4) {

        // Initialize the list of file lines
        LinkedList<String> lines = new LinkedList<String>();

        // Add the first line to lines.
        // Be sure to add the appropriate value.
        lines.add(" ");

        // ADD LINES TO THE lines ARRAY, thus creating an input file.

        // Create the filename for the test case input file.
        String inputFilename = "input_DESCRIPTION_" + filenameStartingIndex;
        inputFilename += "_" + "YOUR N VALUE";
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
