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
		String[] array = new String[] { "a", "b", "c", "d" };
		String[] array2 = new String[] { "a", "b", "c", "e" };

		System.out.println(Arrays.toString(removeFullyCorrect(array, array2)));

	}

	public static void createCode() {// Abdalla

	}

	public static void valid() {// Abdalla

	}

	public static void findFullyCorrect() {// Abdalla

	}

	public static String[] removeFullyCorrect(String[] array1, String[] array2) {// David
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(array1));

		for (int i = 0; i < array1.length; i++) {
			if (array1[i] == array2[i]) {
				arrayList.remove(array1[i]);
			}
		}
		String[] myArray = arrayList.toArray(new String[arrayList.size()]);
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
