package utility;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import testCases.course3.assignment2Clustering.question1.TestCaseGenerator;

/**
 * The {@code TestCaseGeneratorSuperclass} provides a superclass for each assignment and question to
 * extend.
 *
 * @author beaunus
 */
public class TestCaseGeneratorSuperclass {
  /**
   * Generate the test cases.
   *
   * <p>This main method relies on public methods in the extending class.
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
   * Generate an output file for the corresponding input file. The solution has already been
   * calculated.
   *
   * @param inputFilename the filename for the input file
   * @param solution the solution (as a String)
   */
  protected static void generateOutputFile(String inputFilename, String solution) {
    // Infer the output filename from the input filename.
    String outputFilename = inputFilename.replaceAll("input", "output");

    // Write the output file.
    Path outputFile = Paths.get(outputFilename);
    ArrayList<String> lines = new ArrayList<String>();
    lines.add(solution.trim());
    try {
      Files.write(outputFile, lines, Charset.forName("UTF-8"));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
