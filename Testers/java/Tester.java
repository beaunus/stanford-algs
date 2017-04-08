import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Tester {
  public static void main(String[] args) throws UnsupportedEncodingException, IOException {
    // Initialize the className that will be used to test the input files.
    String className = args[0];

    System.out.println("Testing your code's output with the expected output.");
    System.out.println();
    System.out.println("Your class name => " + className);

    // Retrieve the main method of the solution class.
    Method main = getMainMethod(className);

    // Iterate over all the filenames in the command-line arguments.
    for (int i = 1; i < args.length; i++) {
      String inputFilename = args[i];
      // Infer the output filename from the input filename.
      String outputFilename = inputFilename.replaceAll("input", "output");

      // Get the result of calling the solution class's main() method on this file.
      String result = callMethod(main, inputFilename);

      // Display the results of this test case file.
      System.out.println(" inputFilename => " + inputFilename);
      String expectedResult = null;
      expectedResult = new String(Files.readAllBytes(Paths.get(outputFilename)), "UTF-8");
      System.out.print("\texpectedResult => " + expectedResult);
      System.out.print("\t   your result => " + result);
      if (result.equals(expectedResult)) {
        System.out.println("\tYour result matches the test case.");
      } else {
        System.out.println("\tYour result DOES NOT matches the test case.");
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
      }
      System.out.println();
    }
  }

  private static Method getMainMethod(String className) {
    try {
      Class<?> c = Class.forName(className);
      Class<?>[] argTypes = new Class[] {String[].class};
      Method main = c.getDeclaredMethod("main", argTypes);
      return main;
    } catch (ClassNotFoundException x) {
      System.err.println("The specified class name cannot be found.");
      x.printStackTrace();
    } catch (NoSuchMethodException x) {
      System.err.println("The specified class does not have a main method.");
      x.printStackTrace();
    }
    return null;
  }

  private static String callMethod(Method method, String filename) {
    try {
      String[] args = {filename};

      // Capture and return the standard output of the main function.
      // Create a stream to hold the output
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(baos);
      // IMPORTANT: Save the old System.out!
      PrintStream old = System.out;
      // Tell Java to use your special stream
      System.setOut(ps);

      method.invoke(null, (Object) args);

      // Return System.out to the initial stream.
      System.out.flush();
      System.setOut(old);

      return baos.toString();
    } catch (IllegalAccessException x) {
      x.printStackTrace();
    } catch (InvocationTargetException x) {
      x.printStackTrace();
    }
    return null;
  }
}
