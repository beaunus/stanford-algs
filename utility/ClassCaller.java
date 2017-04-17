package utility;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A class to encapsulate methods for calling the main function of a class and returning the
 * System.out output as a String.
 * 
 * @author beaunus
 */
public class ClassCaller {
  /**
   * Return the main Method of the specified class.
   *
   * @param className the class whose main Method should be returned.
   * @return the main Method
   */
  public static Method getMethod(String className, String methodName) {
    try {
      // Get the Class object for the specified className.
      Class<?> myClass = Class.forName(className);
      // Since we want to get the main Method, the argTypes is a String[].
      Class<?>[] argTypes = new Class[] {String[].class};

      // Get and return the main Method.
      Method main = myClass.getDeclaredMethod(methodName, argTypes);
      return main;
    } catch (ClassNotFoundException exception) {
      System.err.println("The specified class name cannot be found.");
      exception.printStackTrace();
    } catch (NoSuchMethodException exception) {
      System.err.println("The specified class does not have a '" + methodName + "' method.");
      exception.printStackTrace();
    }
    return null;
  }

  /**
   * Call the specified method and return the Standard.out output as a String.
   *
   * @param method them method to call
   * @param args the single arguments to be passed to the method
   * @return the Standard.out output of the method as a String
   */
  public static String callMethod(Method method, String[] args) {
    try {

      // Capture and return the standard output of the specified method.
      // Create a stream to hold the output
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(baos, true, "UTF-16");
      // IMPORTANT: Save the old System.out!
      final PrintStream old = System.out;
      // Tell Java to use your special stream
      System.setOut(ps);

      // Invoke the method.
      method.invoke(null, (Object) args);

      // Return System.out to the initial stream.
      System.out.flush();
      System.setOut(old);

      return baos.toString("UTF-16");
    } catch (IllegalAccessException exception) {
      exception.printStackTrace();
    } catch (InvocationTargetException exception) {
      exception.printStackTrace();
    } catch (UnsupportedEncodingException exception) {
      exception.printStackTrace();
    }
    return null;
  }
}
