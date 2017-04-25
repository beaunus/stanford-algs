package testCases.course2.assignment1SCC;

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
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An test case generator for courseX assignmentX.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.course2.assignment2SCC [method to call] [solver class] {[args to method]}
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
   * Creates test cases for the given graph sizes. The graph sizes are determined by the list of
   * ints that are provided as arguments.
   *
   * <p>Each vertex on the graph has [0, 20] outgoing edges.
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args the filenameStartingIndex, followed by an array of numbers of vertices
   */
  public void random(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    // Iterate through the command line arguments
    int argPointer = 1;
    while (argPointer < args.length) {

      System.out.println("Processing problem size => " + args[argPointer]);

      int numVertices = Integer.parseInt(args[argPointer++]);

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<String> solutions = new HashSet<String>();

      while (solutions.size() < 4) {

        // Initialize the list of file lines
        ArrayList<String> lines = new ArrayList<String>();

        // Create the filename for the test case.
        String inputFilename = "input_random";
        inputFilename += "_" + filenameStartingIndex;
        inputFilename += "_" + numVertices;
        inputFilename += ".txt";

        // Add the lines to the lines array.
        for (int vertex = 1; vertex <= numVertices; vertex++) {
          int numOutNeighbors = ThreadLocalRandom.current().nextInt(21);
          int previousNeighbor = -1;
          for (int i = 0; i < numOutNeighbors; i++) {
            if (ThreadLocalRandom.current().nextBoolean()) {
              int thisOutNeighbor = ThreadLocalRandom.current().nextInt(numVertices) + 1;
              if (thisOutNeighbor == i) {
                i--;
                continue;
              }
              lines.add(vertex + " " + thisOutNeighbor);
              if (ThreadLocalRandom.current().nextBoolean() && previousNeighbor != -1) {
                lines.add(thisOutNeighbor + " " + vertex);
              }
              previousNeighbor = thisOutNeighbor;
            }
          }
        }

        // Sort the lines by tail vertex index
        lines.sort(new tailOrder());

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

  private class tailOrder implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
      StringTokenizer st1 = new StringTokenizer(s1);
      StringTokenizer st2 = new StringTokenizer(s2);
      int tail1 = Integer.parseInt(st1.nextToken());
      int head1 = Integer.parseInt(st1.nextToken());
      int tail2 = Integer.parseInt(st2.nextToken());
      int head2 = Integer.parseInt(st2.nextToken());
      if (tail1 == tail2) {
        if (head1 > head2) {
          return 1;
        }
        return 0;
      }
      if (tail1 > tail2) {
        return 1;
      }
      return -1;
    }
  }
}
