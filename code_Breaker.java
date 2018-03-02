/**
 * Author: David Tuck and Abdalla Date: Mar 1, 2018
 */

public class code_Breaker {
	public static void main(String[] args) {// David and Abdalla
		final int SIZE = 10;// max Number of guesses
		final String VALID_CHARS = ("GRBYOP");

	}

	public static void createCode() {// Abdalla

	}

	public static void valid() {// Abdalla

	}

	public static void findFullyCorrect() {// Abdalla

	}

	public static void removeFullyCorrect(String[] array1, String[] array2) {// David
		int[] returnArray = new int[array1.length];
		int c = 0;
		for (int i = 0; i < array1.length; i++) {
			for (int a = 0; a < array1.length; a++) {
				if (array1[i] == array2[a]) {
					returnArray[c] = Integer.parseInt(array1[i]);
					c++;
				}
			}
		}
	}

	public static void findColourCorrect() {// David

	}

	public static void displayGame() {// David

	}

}
