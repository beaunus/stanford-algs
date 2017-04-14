import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import utility.ClassCaller;

/** A class to compare an assignment's solution output to the expected output of a test case. */
public class Tester {
  /**
   * Run the specified class's main method and compare the output to the expected output from the
   * list of input files. Output filenames are derived from the input filenames, where the string
   * "input" is replaced by "output."
   *
   * @param args the class name, followed by a list of input files
   */
  public static void main(String[] args) {

    if (args.length < 2) {
      System.out.println("usage:");
      System.out.println(Tester.class.getName() + " [solution class name] {[list of filenames]}");
      System.exit(0);
    }

    // Initialize the className that will be used to test the input files.
    String className = args[0];

    System.out.println("Testing your code's output with the expected output.");
    System.out.println();
    System.out.println("Your class name => " + className);

    // Retrieve the main method of the solution class.
    Method main = ClassCaller.getMainMethod(className);

    // Iterate over all the filenames in the command-line arguments.
    for (int i = 1; i < args.length; i++) {
      String inputFilename = args[i];
      // Infer the output filename from the input filename.
      String outputFilename = inputFilename.replaceAll("input", "output");

      // Display the results of this test case file.
      System.out.println("  inputFilename => " + inputFilename);
      System.out.println(" outputFilename => " + outputFilename);
      String expectedResult = null;
      try {
        expectedResult = new String(Files.readAllBytes(Paths.get(outputFilename)), "UTF-8");
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      System.out.print("\texpectedResult => " + expectedResult);

      // Get the result of calling the solution class's main() method on this file.
      final String result = ClassCaller.callMethod(main, Arrays.copyOfRange(args, i, i + 1));
      System.out.print("\t   your result => " + result);

      if (result.trim().equals(expectedResult.trim())) {
        System.out.println("\tYour result matches the test case.");
      } else {
        System.out.println("\tYour result DOES NOT matches the test case.");
        System.out.println("Press \"ENTER\" to continue...");
        try {
          System.in.read();
        } catch (IOException ioe) {
          ioe.printStackTrace();
        } 
      }
      System.out.println();
    }
  }
}
