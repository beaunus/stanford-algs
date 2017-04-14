package utility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/** A class to generate an output file from a solution class and input file. */
public class OutputFileGenerator {
  public static void main(String[] args) {

    if (args.length < 2) {
      System.out.println("usage:");
      System.out.println(OutputFileGenerator.class.getName() + " [solution class name] {[list of filenames]}");
      System.exit(0);
    }

    // Initialize the className that will be used to generate the output files.
    String className = args[0];

    System.out.println("Running your code with the input files.");
    System.out.println();
    System.out.println("Your class name => " + className);

    // Retrieve the main method of the solution class.
    Method main = ClassCaller.getMainMethod(className);

    // Iterate over all the filenames in the command-line arguments.
    for (int i = 1; i < args.length; i++) {
      String inputFilename = args[i];
      System.out.println(" inputFilename => " + inputFilename);

      // Get the result of calling the solution class's main() method on this file.
      final String result = ClassCaller.callMethod(main, Arrays.copyOfRange(args, i, i + 1));
      System.out.print("\t   your result => " + result);

      // Infer the output filename from the input filename.
      String outputFilename = inputFilename.replaceAll("input", "output");

      // Write the output file.
      Path outputFile = Paths.get(outputFilename);
      ArrayList<String> lines = new ArrayList<String>();
      lines.add(result.trim());
      try {
        Files.write(outputFile, lines, Charset.forName("UTF-8"));
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      System.out.println();
    }
  }
}
