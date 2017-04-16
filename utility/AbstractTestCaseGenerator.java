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

public abstract class AbstractTestCaseGenerator {
  Method solverMethod;
  Method inputFileGeneratorMethod;
  String[] argsForInputFileGenerator;

  public AbstractTestCaseGenerator(String methodToUse, String solverClassName, String[] args) {
    solverMethod = ClassCaller.getMethod(solverClassName, "main");
    Class<?>[] argTypes = new Class[] {Method.class, String[].class};
    try {
      inputFileGeneratorMethod = this.getClass().getDeclaredMethod(methodToUse, argTypes);
    } catch (NoSuchMethodException e) {
      System.out.println("methodToUse => " + methodToUse);
      displayAvailableMethods(this.getClass());
      e.printStackTrace();
      System.exit(0);
    } catch (SecurityException e) {
      e.printStackTrace();
      System.exit(0);
    }
    argsForInputFileGenerator = args;
    generateInputFiles();
  }
  /**
   * Generate the test cases.
   *
   * @param args [method to call] [solver class] {[args to method]}
   */
  public static void main(Class<?> myClass, String[] args) {
    // Confirm that the command-line arguments are valid.
    if (args.length < 2) {
      System.out.println("usage:");
      System.out.println(
          myClass.getName()
              + " [method to call] [solver class] {[args to method]}");
      displayAvailableMethods(myClass);
      System.out.println();
      System.exit(0);
    }
  }
  
  private static void displayAvailableMethods(Class<?> myClass) {
    System.out.println("\nAvailable methods: ");
    for (Method method : myClass.getDeclaredMethods()) {
      if (!method.getName().equals("main")) {
        System.out.println("\t" + method.getName());
      }
    }
  }

  public void generateInputFiles() {
    try {
      inputFileGeneratorMethod.invoke(this, solverMethod, argsForInputFileGenerator);
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      // TODO Auto-generated catch block
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
