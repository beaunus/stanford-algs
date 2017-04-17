package testCases.course3.assignment2Clustering.question1;

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
 * An test case generator for course3 assignment2Clustering question1.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.course3.assignment2Clustering.question1 [method to call] [solver class] {[args
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
   * Generate 4 unique example files for each of the specified number of vertices. The input file
   * represents a <i>complete graph</i> with random edge lengths.
   *
   * <p>The edge lengths have the following bounds: [1, numberOfVertices^2]
   *
   * @author beaunus
   * @param solverMainMethod the main method to be used to solve the generated input files
   * @param args the first index to be used in naming files, followed by a list of numbers of
   *     vertices
   */
  public static void completeRandom(Method solverMainMethod, String[] args) {
    // Initialize the filenameStartingIndex
    int filenameStartingIndex = Integer.parseInt(args[0]);

    // Iterate over each number of vertices and make 4 files with unique solutions.
    for (int i = 1; i < args.length; i++) {
      int numberOfVertices = Integer.parseInt(args[i]);

      System.out.println("Processing problem size = " + numberOfVertices);

      // Initialize the maximum cost of an edge length.
      final int maxCost = numberOfVertices * numberOfVertices;

      // Initialize a HashSet of solutions to ensure uniqueness.
      HashSet<String> solutions = new HashSet<String>();

      // Process until there are 4 unique solutions.
      while (solutions.size() < 4) {
        // Use an ArrayList to store the lines that will be added to the file.
        ArrayList<String> lines = new ArrayList<String>();
        // Add the first line, which defines the problem size.
        lines.add("" + numberOfVertices);

        // Add edges between all vertices
        for (int j = 1; j <= numberOfVertices; j++) {
          for (int k = j + 1; k <= numberOfVertices; k++) {
            int cost = ThreadLocalRandom.current().nextInt(maxCost) + 1;
            lines.add(j + " " + k + " " + cost);
          }
        }

        // Setup the name of the input file.
        String inputFilename =
            "input_completeRandom_" + filenameStartingIndex + "_" + numberOfVertices + ".txt";

        // Create the input file and write the lines.
        Path inputFile = Paths.get(inputFilename);
        try {
          Files.write(inputFile, lines, Charset.forName("UTF-8"));
        } catch (IOException exception) {
          exception.printStackTrace();
        }

        // Determine the solution to the file.
        String[] filenames = {inputFilename};
        String solution = ClassCaller.callMethod(solverMainMethod, filenames);
        if (solutions.contains(solution)) {
          // If the solution is not unique, delete the input file
          try {
            Files.delete(inputFile);
          } catch (IOException exception) {
            exception.printStackTrace();
          }
        } else {
          // If the solution is unique, generate an output file.
          solutions.add(solution);
          generateOutputFile(inputFilename, solution);
          System.out.println("\t" + solutions.size() + " unique file(s) generated.");
          filenameStartingIndex++;
        }
      }
    }
  }
}
