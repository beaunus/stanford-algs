package testCases.course1.assignment4MinCut;

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
 * An test case generator for course1 assignment4MinCut.
 *
 * <p>Using this class's main method should be parameter driven. The convention
 * is as follows:
 *
 * <p>java testCases.course4.assignment1AllPairsShortestPath [method to call]
 * [solver class] {[args to method]}
 *
 * <p>The [solver class] is used to ensure that test cases with unique solutions
 * are produced.
 */
public class TestCaseGenerator extends AbstractTestCaseGenerator {
  public TestCaseGenerator(String methodToUse, String solverClassName,
                           String[] args) {
    super(methodToUse, solverClassName, args);
  }

  /**
   * Run the TestCaseGenerator.
   *
   * @param args [method to call] [solver class] {[args to method]}
   */
  public static void main(String[] args) {
    AbstractTestCaseGenerator.main(TestCaseGenerator.class, args);
    TestCaseGenerator tcg = new TestCaseGenerator(
        args[0], args[1], Arrays.copyOfRange(args, 2, args.length));
    tcg.generateInputFiles();
  }

  /**
   * Create test case files with adjacency lists that represent graphs with the
   * following properties:
   *
   * <ul>
   *   <li>The graph has the specified number of vertices.
   *   <li>Each vertex has [2, n/2] number of edges.
   * </ul>
   *
   * @param solverMainMethod the main method that gives a solution with the
   * given file
   * @param args the filenameStartingIndex, followed by an array of numbers of
   * vertices
   */
  public void random(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    // Iterate through the command line arguments
    int argPointer = 1;
    while (argPointer < args.length) {

      int numVertices = Integer.parseInt(args[argPointer]);

      System.out.println("Processing problem size => " + args[argPointer++]);

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<String> solutions = new HashSet<String>();

      while (solutions.size() < 4) {

        // Create the filename for the test case.
        String inputFilename = "input_random";
        inputFilename += "_" + filenameStartingIndex;
        inputFilename += "_" + numVertices;
        inputFilename += ".txt";

        // Write the input file.
        Path file = Paths.get(inputFilename);

        // Initialize the list of file lines
        ArrayList<String> lines = new ArrayList<String>();

        // Initialize an array to represent the adjacency list.
        ArrayList<ArrayList<Integer>> adjacencyList =
            new ArrayList<ArrayList<Integer>>(numVertices);

        for (int i = 0; i < numVertices; i++) {
          adjacencyList.add(new ArrayList<Integer>());
        }

        // Add the edges to each vertex.
        for (int i = 0; i < numVertices; i++) {
          // System.out.printf("i => %d\n", i);
          int thisVertexNumEdges =
              ThreadLocalRandom.current().nextInt(1, numVertices / 2);
          // thisVertexNumEdges += 1;
          int numEdges = 0;
          ArrayList<Integer> thisVertexEdges = adjacencyList.get(i);
          while (thisVertexEdges.size() < numVertices - 1 &&
                 numEdges < thisVertexNumEdges) {
            int vertexToConnect =
                ThreadLocalRandom.current().nextInt(numVertices);
            if (vertexToConnect == i) {
              continue;
            }
            // System.out.printf("numEdges => %d\n", numEdges);
            // System.out.printf("vertexToConnect => %d\n", vertexToConnect);
            // System.out.printf("thisVertexEdges => %s\n", thisVertexEdges);
            if (!thisVertexEdges.contains(vertexToConnect + 1)) {
              // System.out.printf("UNIQUE\n");
              thisVertexEdges.add(vertexToConnect + 1);
              numEdges++;
              adjacencyList.get(vertexToConnect).add(i + 1);
            }
          }
        }

        // Confirm that the newly created adjacency list doesn't have any
        // parallel edges.
        for (ArrayList<Integer> list : adjacencyList) {
          HashSet<Integer> hashSet = new HashSet<Integer>(list);
          assert(hashSet.size() == list.size());
        }

        // Translate the adjacency list into the lines array.
        for (int i = 0; i < numVertices; i++) {
          StringBuilder thisLine = new StringBuilder();
          thisLine.append("" + (i + 1));
          for (int vertexToConnect : adjacencyList.get(i)) {
            thisLine.append(" " + vertexToConnect);
          }
          lines.add(thisLine.toString());
        }

        try {
          Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException exception) {
          exception.printStackTrace();
        }

        String[] filenames = {inputFilename};

        String solution = ClassCaller.callMethod(solverMainMethod, filenames);
        System.out.println("This solution => " + solution);

        // create an output file for this solution only if it is a unique
        // solution.
        if (!solutions.contains(solution)) {
          // Add this solution to the set of solutions so far.
          solutions.add(solution);

          AbstractTestCaseGenerator.generateOutputFile(inputFilename, solution);
          System.out.println("\t" + solutions.size() +
                             " unique file(s) generated.");
          filenameStartingIndex++;
        }
      }
    }
  }
}
