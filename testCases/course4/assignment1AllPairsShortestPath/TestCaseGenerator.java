package testCases.course4.assignment1AllPairsShortestPath;

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
 * An test case generator for courseX assignmentX.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.course4.assignment1AllPairsShortestPath [method to call] [solver class] {[args
 * to method]}
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
   * Generate 4 test case files for each of the specified number of vertices.
   *
   * <p>For each vertex in the graph, add sqrt(numberOfVertices) edges of random weight between -100
   * and 100.
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args the filenameStartingIndex, followed by an array of {WHAT DOES YOUR ARGUMENT ARRAY
   *     LOOK LIKE?}
   */
  public void random(Method solverMainMethod, String[] args) {
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
        ArrayList<String> lines = new ArrayList<String>();

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
        lines.add(0, numberOfVertices + " " + numberOfEdges);

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
        } else {
          solution = Double.parseDouble(solutionAsString.trim());
        }

        // create an output file for this solution only if it is a unique solution.
        if (!solutions.contains(solution)) {
          // Add this solution to the set of solutions so far.
          solutions.add(solution);
          AbstractTestCaseGenerator.generateOutputFile(inputFilename, solutionAsString);
          System.out.println("\t" + solutions.size() + " unique file(s) generated.");
          filenameStartingIndex++;
        }
      }
    }
  }
}
