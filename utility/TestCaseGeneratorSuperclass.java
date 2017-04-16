package utility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The {@code TestCaseGeneratorSuperclass} provides a superclass for each assignment and question to
 * extend.
 *
 * @author beaunus
 */
public abstract class TestCaseGeneratorSuperclass {
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
