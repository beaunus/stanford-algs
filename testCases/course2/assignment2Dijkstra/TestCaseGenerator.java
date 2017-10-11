package testCases.course2.assignment2Dijkstra;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import utility.AbstractTestCaseGenerator;
import utility.ClassCaller;

/**
 * An test case generator for course2 assignment2Dijkstra.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.course2.assignment1Dijkstra [method to call] [solver class] {[args to method]}
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

  /** A class to represent an outgoing edge. */
  private static class Edge {
    public int otherVertex;
    public int weight;

    public Edge(final int otherVertex, final int weight) {
      this.otherVertex = otherVertex;
      this.weight = weight;
    }
  }

  /**
   * Creates 4 unique test cases for each of the given problem sizes (n).
   *
   * <p>All graphs have 200 vertices.
   *
   * <p>Each vertex has [0,n] edges. Each edge has weight [1,i+n^e].
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args the filenameStartingIndex, followed by an array of problem sizes.
   */
  public void random(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    // Iterate through the command line arguments
    int argPointer = 1;
    while (argPointer < args.length) {

      System.out.println("Processing problem size => " + args[argPointer]);

      int problemSize = Integer.parseInt(args[argPointer++]);

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<String> solutions = new HashSet<String>();

      while (solutions.size() < 4) {

        // Create the filename for the test case.
        String inputFilename = "input_random";
        inputFilename += "_" + filenameStartingIndex;
        inputFilename += "_" + problemSize;
        inputFilename += ".txt";

        // Initialize the list of file lines
        ArrayList<String> lines = new ArrayList<String>();

        int numEdges = ThreadLocalRandom.current().nextInt(problemSize + 1) / 2;
        int maxEdgeWeight = (int) Math.ceil(Math.pow(problemSize, Math.E));
        // Create a set of vertices that share an edge with each vertex
        ArrayList<Set<Edge>> sharedEdges = new ArrayList<Set<Edge>>();
        for (int i = 0; i < 200; ++i) {
          sharedEdges.add(new HashSet<Edge>());
        }
        // Compute edges for each vertex
        for (int i = 0; i < 200; ++i) {
          for (int edgeCount = 0; edgeCount < numEdges; ++edgeCount) {
            int otherVertex = i;
            while (otherVertex == i) {
              otherVertex = ThreadLocalRandom.current().nextInt(0, 200);
            }
            int thisEdgeWeight = ThreadLocalRandom.current().nextInt(1, maxEdgeWeight + 1);
            sharedEdges.get(i).add(new Edge(otherVertex, thisEdgeWeight));
            sharedEdges.get(otherVertex).add(new Edge(i, thisEdgeWeight));
          }
        }

        // Write edges to the file
        for (int i = 0; i < 200; i++) {
          StringBuilder sb = new StringBuilder();
          sb.append(i + 1);
          for (Edge edge : sharedEdges.get(i)) {
            sb.append("\t").append(edge.otherVertex + 1).append(",").append(edge.weight);
          }
          lines.add(sb.toString());
        }

        // Write the input file.
        Path file = Paths.get(inputFilename);
        try {
          Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException exception) {
          exception.printStackTrace();
        }

        String[] filenames = {inputFilename};

        String solution = ClassCaller.callMethod(solverMainMethod, filenames);

        if (solution.contains("1000000")) {
          continue;
        }

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
