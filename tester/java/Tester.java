package tester.java;

import utility.ClassCaller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
   * @param args the class name, followed by a list of input files, followed by options
   *     <p>Options:
   *     <ul>
   *       <li>-maxsize:<maximum input size>
   *       <li>-name:<method that returns solution>
   *       <li>-only:<specific tests to run, excluding others>
   *     </ul>
   */
  public static void main(String[] args) {

    if (args.length < 2) {
      System.out.println(
          "Usage:"
              + Tester.class.getName()
              + " [solution class name] {[list of input files]} [-options]");
      System.out.println("where options include:");
      System.out.println("\t-maxsize        maximum input size");
      System.out.println("\t-name           method that returns solution (default: main)");
      System.out.println("\t-only           specific tests to run, excluding others");
      System.exit(0);
    }

    // Parse the arguments.
    HashMap<String, String> arguments = new HashMap<String, String>();
    ArrayList<String> filenames = new ArrayList<String>();
    arguments.put("solutionClassName", args[0]);

    for (int i = 1; i < args.length; i++) {
      if (args[i].startsWith("-")) {
        int indexOfColon = args[i].indexOf(":");
        String parameterName = args[i].substring(1, indexOfColon);
        String argument = args[i].substring(indexOfColon + 1, args[i].length());
        arguments.put(parameterName, argument);
      } else {
        filenames.add(args[i]);
      }
    }

    String className = arguments.get("solutionClassName");

    System.out.println("Testing your code's output with the expected output.");
    System.out.println();
    for (Entry<String, String> entry : arguments.entrySet()) {
      System.out.println(entry.getKey() + " => " + entry.getValue());
    }

    // Retrieve the main method of the solution class.
    String methodName = arguments.get("name") != null ? arguments.get("name") : "main";
    Method method = ClassCaller.getMethod(className, methodName);

    filenames.sort(new InputSizeOrder());

    // Iterate over all the filenames in the command-line arguments.
    for (String inputFilename : filenames) {
      String[] filenameStringArray =
          inputFilename.substring(0, inputFilename.indexOf(".")).split("_");

      // Determine if this input file exceeds the maxsize
      if (arguments.get("maxsize") != null) {
        if (Integer.parseInt(arguments.get("maxsize")) < Integer.parseInt(filenameStringArray[3])) {
          System.out.println("skipped " + inputFilename + "\t-> exceeded maxsize");
          continue;
        }
      }

      // Infer the output filename from the input filename.
      String outputFilename = inputFilename.replaceAll("input", "output");

      String expectedResult = null;
      try {
        expectedResult = new String(Files.readAllBytes(Paths.get(outputFilename)), "UTF-8");
      } catch (IOException exception) {
        exception.printStackTrace();
      }

      // Get the result of calling the solution class's main() method on this file.
      String[] thisFilenameAsArray = {inputFilename};
      final String result = ClassCaller.callMethod(method, thisFilenameAsArray);
      if (expectedResult != null && result.trim().equals(expectedResult.trim())) {
        System.out.println(inputFilename + "\t✔");
      } else {
        System.out.println(inputFilename + "\t✘");
        System.out.println("\tExpected: " + expectedResult.trim());
        System.out.println("\t  Result: " + result.trim());
      }
    }
  }

  private static class InputSizeOrder implements Comparator<String> {

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
