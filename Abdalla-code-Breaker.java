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
		public static void createCode(String VALID_CHARS, int size) {// Abdalla

		char[] chars = new char[VALID_CHARS.length() - 1];
		for (int i = 0; i < VALID_CHARS.length() - 1; i++) {
			chars[i] = VALID_CHARS.charAt(i);
		}
		String ran = "";
		for (int i = 0; i < size; i++) {
			ran = ran + chars[(int) (Math.random() * 6)];
		}
		System.out.println(ran);

	}
