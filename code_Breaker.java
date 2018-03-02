import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: David Tuck and Abdalla Date: Mar 1, 2018
 */

public class code_Breaker {
	public static void main(String[] args) {// David and Abdalla
		final int SIZE = 10;// max Number of guesses
		final String VALID_CHARS = ("GRBYOP");
		String[] array = new String[] { "a", "b", "c", "d" };
		String[] array2 = new String[] { "a", "b", "c", "d" };

		System.out.println(Arrays.toString(removeFullyCorrect(array, array2)));

	}

	public static void createCode(VALID_CHARS, SIZE) {// Abdalla
	Random= r new Random
        VALID_CHARS[r.nextInt(VALID_CHARS.length)
	}

	public static void valid() {// Abdalla

	}

	public static void findFullyCorrect() {// Abdalla

	}

	public static String[] removeFullyCorrect(String[] array1, String[] array2) {// David

		List<String> myArrayList = new ArrayList<String>();
		for (int i = 0; i < array1.length; i++) {
			if (array1[i] == array2[i]) {
				myArrayList.add(array1[i]);
			}
		}
		String[] myArray = myArrayList.toArray(new String[myArrayList.size()]);
		return myArray;
	}

	public static String[] findColourCorrect(String[] array1, String[] array2) {// David
		List<String> myArrayList = new ArrayList<String>();

		for (int i = 0; i < array1.length; i++) {
			for (int a = 0; a < array1.length; a++) {
				if (array1[i] == array2[a]) {
					myArrayList.add("w");
				}
			}
		}
		String[] myArray = myArrayList.toArray(new String[myArrayList.size()]);
		return myArray;
	}

	public static void displayGame() {// David

	}

}
