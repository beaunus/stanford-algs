import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Creates example input files for this assignment.
 *
 * @author beaunus
 *
 */
public class StanfordAlgsCourse4Assignment1InputFileGenerator {

  /**
   * Reads the arguments and creates 4 example files for each (number of vertices) argument.
   *
   * <p>
   * The output filenames will look like this:
   *
   * <p>
   * all-pairs-shortest-path-example-v-[number of vertices]-solution-[solution].txt
   *
   * @param args [number of vertices], ...
   */
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out
          .println("usage: " + StanfordAlgsCourse4Assignment1InputFileGenerator.class.getName()
              + " " + "{[number of vertices size]}");
      System.out.println();
      System.out.println("4 unique files will be created for each of the "
          + "([number of vertices size]) arguments.");
      return;
    }
    // Iterate through the command line arguments
    int argPointer = 0;
    while (argPointer < args.length) {

      // Read arguments into variables.
      int numberOfVertices = Integer.parseInt(args[argPointer++]);

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<Double> solutions = new HashSet<Double>();

      while (solutions.size() < 4) {

        // Initialize the list of file lines
        LinkedList<String> lines = new LinkedList<String>();

        // Initialize a new graph to solve.
        EdgeWeightedGraph graph = new EdgeWeightedGraph(numberOfVertices);
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
            graph.addEdge(new Edge(i, endVertex, weight));
            numberOfEdges++;
            lines.add((i + 1) + " " + (endVertex + 1) + " " + weight.intValue());
          }
        }

        // Prepend the lines with the number of vertices and number of edges.
        lines.addFirst(numberOfVertices + " " + numberOfEdges);

        FloydWarshall floydWarshall;
        Double solution;
        try {
          floydWarshall = new FloydWarshall(graph);
          solution = floydWarshall.shortestShortestPath();
        } catch (NegativeCycleException e) {
          solution = Double.NEGATIVE_INFINITY;
        }

        System.out.println("solution => " + solution);

        // create a file for this solution only if it is a unique solution.
        if (!solutions.contains(solution)) {
          // Create the filename for the test case.
          String filename = "all-pairs-shortest-path-example";
          filename += "-v-" + numberOfVertices;
          filename += "-solution-";
          if (Double.compare(solution, Double.NEGATIVE_INFINITY) != 0) {
            filename += solution.intValue();
          } else {
            filename += "NULL";
          }
          filename += ".txt";

          // Add this solution to the set of solutions so far.
          solutions.add(solution);

          // Write the file.
          Path file = Paths.get(filename);
          try {
            Files.write(file, lines, Charset.forName("UTF-8"));
          } catch (IOException exception) {
            exception.printStackTrace();
          }
        }
      }
    }
  }
}
