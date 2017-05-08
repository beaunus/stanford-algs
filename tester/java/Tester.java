package tester.java;

import utility.ClassCaller;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

/** A class to compare an assignment's solution output to the expected output of a test case. */
public class Tester {
  /**
   * Run the specified class's specified method and compare the output to the expected output from
   * the list of input files.
   *
   * <p>Output filenames are derived from the input filenames, where the string "input" is replaced
   * by "output."
   *
   * @param args the class name, followed by a list of input files, followed by options:
   *     <ul>
   *       <li>-maxsize:&lt;maximum input size&gt;
   *       <li>-name:&lt;method that returns solution&gt;
   *     </ul>
   */
  public static void main(String[] args) {

    // If the usage is incorrect, display usage string.
    if (args.length < 2) {
      displayUsageString();
      System.exit(0);
    }

    // Parse the arguments.
    int argPointer = 0;

    // Parse the options.
    HashMap<String, String> options = new HashMap<String, String>();
    while (args[argPointer].startsWith("-")) {
      int indexOfColon = args[argPointer].indexOf(":");
      String parameterName = args[argPointer].substring(1, indexOfColon);
      String argument = args[argPointer].substring(indexOfColon + 1, args[argPointer].length());
      options.put(parameterName, argument);
      argPointer++;
    }

    // Parse the solution class name.
    final String solutionClassName = args[argPointer++];

    // Parse the file names.
    ArrayList<String> filenames = new ArrayList<String>();
    while (argPointer < args.length) {
      if (validateFilename(args[argPointer])) {
        filenames.add(args[argPointer]);
      }
      argPointer++;
    }

    // Display feedback to user.
    System.out.println("\nTesting your code's output with the expected output.\n");

    // Display options.
    for (Entry<String, String> entry : options.entrySet()) {
      System.out.println(entry.getKey() + " => " + entry.getValue());
    }
    System.out.println();

    // Retrieve the main method of the solution class.
    String methodName = options.get("name") != null ? options.get("name") : "main";
    Method method = ClassCaller.getMethod(solutionClassName, methodName);

    // Sort the filenames, so simpler problems are processed first.
    filenames.sort(new InputSizeOrder());

    // Initialize an array of failed tests, to be displayed later.
    ArrayList<String> failedTests = new ArrayList<String>();

    // Iterate over all the filenames in the command-line arguments.
    for (String inputFilename : filenames) {
      String[] filenameStringArray =
          inputFilename.substring(0, inputFilename.indexOf(".")).split("_");

      // Determine if this input file exceeds the maxsize.
      if (options.get("maxsize") != null) {
        if (Integer.parseInt(options.get("maxsize")) < Integer.parseInt(filenameStringArray[3])) {
          System.out.print("\033[36m");
          System.out.println("skipped " + inputFilename + "\t-> exceeded maxsize");
          System.out.print("\033[0m");
          continue;
        }
      }

      // Infer the output filename from the input filename.
      String outputFilename = inputFilename.replaceAll("input", "output");

      // Retrieve the expected result.
      String expectedResult = null;
      try {
        expectedResult = new String(Files.readAllBytes(Paths.get(outputFilename)), "UTF-8");
      } catch (IOException exception) {
        exception.printStackTrace();
      }

      // Get the result of calling the solution class's main() method on this file.
      String[] thisFilenameAsArray = {inputFilename};
      final String result = ClassCaller.callMethod(method, thisFilenameAsArray);

      // Display whether or not this test matches the expected results.
      if (expectedResult != null) {
        if (result.trim().equals(expectedResult.trim())) {
          System.out.print("\033[32m");
          System.out.println(inputFilename + "\t✔");
          System.out.print("\033[0m");
        } else {
          System.out.print("\033[31m");
          System.out.println(inputFilename + "\t✘");
          System.out.println("\tExpected: " + expectedResult.trim());
          System.out.println("\t  Result: " + result.trim());
          System.out.print("\033[0m");
          failedTests.add(inputFilename);
        }
      }
    }

    // Display summary of test results.
    System.out.println();
    if (failedTests.isEmpty()) {
      System.out.print("\033[32m");
      System.out.println("✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔");
      System.out.println("All tests passed");
      System.out.println("✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔");
      System.out.print("\033[0m");
    } else {
      System.out.print("\033[31m");
      System.out.println("✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘");
      System.out.println("Some tests didn't pass");
      System.out.println("✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘");
      System.out.println("\nFailed tests:");
      for (String failedTest : failedTests) {
        System.out.println("\t" + failedTest);
      }
      System.out.print("\033[0m");
    }
  }

  /**
   * Return whether or not a filename is valid.
   *
   * @param string the filename to be validated
   * @return whether or not the filename is valid
   */
  private static boolean validateFilename(String string) {
    if (string.split("_").length == 4) {
      return true;
    }
    System.out.println(
        "'"
            + string
            + "' must match the pattern '\033[1minput_description_index_problemSize.txt\033[0m'");
    return false;
  }

  /** Display the class's usage string. */
  private static void displayUsageString() {
    System.out.println(
        "Usage:"
            + "java -cp <path to tester class>:<path to solution class> "
            + Tester.class.getName()
            + "  [-options] <solution class name> <list of input files>");
    System.out.println("where options include:");
    System.out.println("\t-maxsize        maximum input size");
    System.out.println("\t-name           method that returns solution (default: main)");
  }

  /**
   * A comparator to sort filenames based on the problem size.
   *
   * @author beaunus
   */
  private static class InputSizeOrder implements Comparator<String>, Serializable {

    /** Auto-generated serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Override
    public int compare(String o1, String o2) {
      String[] o1AsArray = o1.split("_");
      String[] o2AsArray = o2.split("_");

      int o1Size = Integer.parseInt(o1AsArray[o1AsArray.length - 2].split("\\.")[0]);
      int o2Size = Integer.parseInt(o2AsArray[o2AsArray.length - 2].split("\\.")[0]);

      return o1Size - o2Size;
    }
  }
}
