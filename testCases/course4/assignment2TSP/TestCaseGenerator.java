package testCases.course4.assignment2TSP;

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
 * An test case generator for course4 assignment2.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.course4.assignment2TSP.TestCaseGenerator [method to call] [solver class] {[args
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
   * Creates 4 unique test cases for each of the problem sizes.
   *
   * <p>The problem size defines the number of "cities" in the tour.
   *
   * <p>The "cities'" coordinates range from [0, numberOfCites^2]. The "cities'" coordinates are
   * floating point numbers.
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args the filenameStartingIndex, followed by an array of numbers of cities.
   */
  public void intCoordinates(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    // Iterate through the command line arguments
    int argPointer = 1;
    while (argPointer < args.length) {

      System.out.println("Processing problem size => " + args[argPointer]);

      int numCities = Integer.parseInt(args[argPointer++]);

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<String> solutions = new HashSet<String>();

      while (solutions.size() < 4) {

        // Initialize the list of file lines
        ArrayList<String> lines = new ArrayList<String>();

        int maxCoordinate = (int) Math.pow(numCities,2);

        lines.add("" + numCities);
        for (int i = 0; i < numCities; i++) {
          int xCoord = ThreadLocalRandom.current().nextInt(maxCoordinate);
          int yCoord = ThreadLocalRandom.current().nextInt(maxCoordinate);
          lines.add(xCoord + " " + yCoord);
        }

        // Create the filename for the test case.
        String inputFilename = "input_int2";
        inputFilename += "_" + filenameStartingIndex;
        inputFilename += "_" + numCities;
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

  /**
   * Creates 4 unique test cases for each of the problem sizes.
   *
   * <p>The problem size defines the number of "cities" in the tour.
   *
   * <p>The "cities'" coordinates range from [0, numberOfCites]. The "cities'" coordinates are
   * floating point numbers.
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args the filenameStartingIndex, followed by an array of numbers of cities.
   */
  public void floatCoordinates(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    // Iterate through the command line arguments
    int argPointer = 1;
    while (argPointer < args.length) {

      System.out.println("Processing problem size => " + args[argPointer]);

      int numCities = Integer.parseInt(args[argPointer++]);

      // A set of solutions.
      // Used to ensure 4 unique test case files are generated.
      HashSet<String> solutions = new HashSet<String>();

      while (solutions.size() < 4) {

        // Initialize the list of file lines
        ArrayList<String> lines = new ArrayList<String>();

        int maxCoordinate = numCities;

        lines.add("" + numCities);
        for (int i = 0; i < numCities; i++) {
          double xCoord = ThreadLocalRandom.current().nextDouble(maxCoordinate);
          double yCoord = ThreadLocalRandom.current().nextDouble(maxCoordinate);
          lines.add(xCoord + " " + yCoord);
        }

        // Create the filename for the test case.
        String inputFilename = "input_float";
        inputFilename += "_" + filenameStartingIndex;
        inputFilename += "_" + numCities;
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
