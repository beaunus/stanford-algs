package testCases.course3.assignment2Clustering.question2;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import utility.ClassCaller;
import utility.TestCaseGeneratorSuperclass;

/**
 * An test case generator for course3 assignment2Clustering question2.
 *
 * <p>Using this class's main method should be parameter driven. The convention is as follows:
 *
 * <p>java testCases.course3.assignment2Clustering.question1 [method to call] [solver class] {[args
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
   * Generates an example file for Question 2, with the specified number of vertices and number of
   * bits per vertex.
   *
   * <p>The integer values of the vertices have the following bounds: [1, 2^numberOfBits]
   *
   * @param solverMainMethod the main method that gives a solution with the given file
   * @param args an array of numbers of vertices
   */
  private static void random(Method solverMainMethod, String[] args) {
    int filenameStartingIndex = Integer.parseInt(args[0]);

    // Since some of the (numberOfVertices, numberOfBits) pairs are incompatible, 
    // we should give up if more than 6 consecutive attempts lead to only a single component.
    final int MAX_ATTEMPTS = 6;
    final int[] numBitsArray = {4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24};

    // Iterate over all of the given numberOfVertices arguments
    for (int i = 1; i < args.length; i++) {
      int numberOfVertices = Integer.parseInt(args[i]);

      System.out.println("Processing numberOfVertices = " + numberOfVertices);

      // Iterate over all of the possible numberOfBits, in descending order.
      for (int h = numBitsArray.length - 1; h >= 0; h--) {
        int numberOfBits = numBitsArray[h];

        System.out.println("Processing numberOfBits = " + numberOfBits);

        boolean foundSolution = false; 
        int numberOfAttempts = 0;
        // Attempt to make a test case, until the number of attempts exceeds the MAX_ATTEMPTS
        while (!foundSolution && numberOfAttempts < MAX_ATTEMPTS) {
          // Initialize variables
          LinkedList<String> lines = new LinkedList<String>();
          lines.add(numberOfVertices + " " + numberOfBits);
          StringBuilder thisLine;
          StringBuilder thisLineWithSpaces;
          
          // Initialize the maximum int value for a vertex.
          int max = (int) Math.pow(2, numberOfBits);

          // For each vertex, generate a random number and add it's binary string
          // representation to the output.
          for (int j = 0; j < numberOfVertices; j++) {
            int number = ThreadLocalRandom.current().nextInt(max);
            thisLine = new StringBuilder();
            // Prepend necessary leading zeros.
            for (int k = 32 - numberOfBits; k < Integer.numberOfLeadingZeros(number); k++) {
              thisLine.append("0");
            }
            thisLine.append(Integer.toBinaryString(number));

            // Add spaces between all bit values.
            thisLineWithSpaces = new StringBuilder();
            for (int k = 0; k < numberOfBits; k++) {
              if (k != 0) {
                thisLineWithSpaces.append(" ");
              }
              thisLineWithSpaces.append(thisLine.charAt(k));
            }
            lines.add(thisLineWithSpaces.toString());
          }

          String inputFilename =
              "input_random_"
                  + filenameStartingIndex
                  + "_"
                  + numberOfVertices
                  + "_"
                  + numberOfBits
                  + ".txt";

          Path file = Paths.get(inputFilename);
          try {
            Files.write(file, lines, Charset.forName("UTF-8"));
          } catch (IOException exception) {
            exception.printStackTrace();
          }

          // Determine the solution to the file.
          String[] filenames = {inputFilename};
          String solution = ClassCaller.callMethod(solverMainMethod, filenames);

          int solutionAsInt = Integer.parseInt(solution.trim());
          // If the solution is useless, don't make an output file.
          if (solutionAsInt == 1
              || solutionAsInt == numberOfVertices
              || foundSolution) {
            numberOfAttempts++;
            try {
              Files.delete(file);
            } catch (IOException e) {
              e.printStackTrace();
            }
          } else {
            foundSolution = true; 
            generateOutputFile(inputFilename, solution);
            filenameStartingIndex++; 
            System.out.println("\t test case file generated.");
          }
        }
        if (numberOfAttempts >= MAX_ATTEMPTS && numberOfVertices > 1000) {
          System.out.println(
              "Giving up because the numberOfVertices is incompatible with the numberOfBits.");
          break;
        }
      }
    }
  }
}
