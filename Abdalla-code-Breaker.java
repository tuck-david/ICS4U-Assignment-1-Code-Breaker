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
		char[] chars = new char[VALID_CHARS.length() - 1];
				for (int i = 0; i < VALID_CHARS.length() - 1; i++) {
					chars[i] = VALID_CHARS.charAt(i);
				}
				String ran;
				ran= ran+chars[(int)(Math.random()*6)];
