import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: David Tuck and Abdalla Date: Mar 1, 2018
 */

public class code_Breaker {
	public static void main(String[] args) {// David and Abdalla
		final int SIZE = 4;// max Number of guesses
		final int TRIES = 10;
		final String VALID_CHARS = ("GRBYOP");
		public static String[] createCode(String VALID_CHARS, int size) {// Abdalla
			char[] chars = new char[VALID_CHARS.length() - 1];
			for (int i = 0; i < VALID_CHARS.length() - 1; i++) {
				chars[i] = VALID_CHARS.charAt(i);
			}
			String[] code = new String[size];
			for (int i = 0; i < size; i++) {
				code[i] = Character.toString(VALID_CHARS.charAt((int) (Math.random() * 6)));
			}
			System.out.println(Arrays.toString(code));
			return code;
		}
	}
	public static void valid(String VALID_CHARS, int size, String again, boolean valid) {// Abdalla
 
 if ( Valid_Chars== null || Valid_Chars.size() != SIZE) {//too many errors
      throw new IllegalArgumentException("Expected a list of " + SIZE + " colors. Received: " + VALID_CHARS); //error
    }
    for (int i=0; i>VALID_CHARS.length; i++) { //Error
      if (color == null) {
        // this gets us out of null-checking everywhere in the code
        throw new IllegalArgumentException("Expected a list with all non-null entries. Received: " + colors);
      } else{
      }
}
