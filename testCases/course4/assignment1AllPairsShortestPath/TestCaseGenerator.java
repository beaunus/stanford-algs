// Be sure to put the appropriate package name
package testCases.course4.assignment1AllPairsShortestPath;

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
 * An test case generator for courseX assignmentX.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.course4.assignment1AllPairsShortestPath [method to call] [solver class] {[args
 * to method]}
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
   * Generate 4 test case files for each of the specified number of vertices.
   *
   * <p>For each vertex in the graph, add sqrt(numberOfVertices) edges of random weight between -100
   * and 100.
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args the filenameStartingIndex, followed by an array of {WHAT DOES YOUR ARGUMENT ARRAY
   *     LOOK LIKE?}
   */
  private static void random(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    // Iterate through the command line arguments
    int argPointer = 1;
    while (argPointer < args.length) {

      // Read arguments into variables.
      int numberOfVertices = Integer.parseInt(args[argPointer++]);
      
      System.out.println("Processing numberOfVertices => " + numberOfVertices);

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<Double> solutions = new HashSet<Double>();

      while (solutions.size() < 4) {

        // Initialize the list of file lines
        LinkedList<String> lines = new LinkedList<String>();

        // A counter for number of edges
        int numberOfEdges = 0;

        // For each vertex in the graph, add sqrt(numberOfVertices) edges
        // of random weight between -100 and 100.
        for (int i = 0; i < numberOfVertices; i++) {
          for (int j = 0; j < Math.sqrt(numberOfVertices); j++) {
            int endVertex = ThreadLocalRandom.current().nextInt(0, numberOfVertices);
            Double weight = (double) ThreadLocalRandom.current().nextInt(-100, 100);
            // To prevent negative cycles
            if (solutions.contains(Double.NEGATIVE_INFINITY) && endVertex <= i) {
              continue;
            }
            numberOfEdges++;
            lines.add((i + 1) + " " + (endVertex + 1) + " " + weight.intValue());
          }
        }

        // Prepend the lines with the number of vertices and number of edges.
        lines.addFirst(numberOfVertices + " " + numberOfEdges);

        // Create the filename for the test case.
        String inputFilename = "input_random";
        inputFilename += "_" + filenameStartingIndex;
        inputFilename += "_" + numberOfVertices;
        inputFilename += ".txt";

        // Write the input file.
        Path file = Paths.get(inputFilename);
        try {
          Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException exception) {
          exception.printStackTrace();
        }

        String[] filenames = {inputFilename};

        String solutionAsString = ClassCaller.callMethod(solverMainMethod, filenames);
        Double solution;
        if (solutionAsString.trim().equals("NULL")) {
          solution = Double.NEGATIVE_INFINITY; 
        }
        else {
          solution = Double.parseDouble(solutionAsString.trim()); 
        }

        // create an output file for this solution only if it is a unique solution.
        if (!solutions.contains(solution)) {
          // Add this solution to the set of solutions so far.
          solutions.add(solution);
          generateOutputFile(inputFilename, solutionAsString);
          System.out.println("\t" + solutions.size() + " unique file(s) generated.");
          filenameStartingIndex++;
        }
      }
    }
  }
}
