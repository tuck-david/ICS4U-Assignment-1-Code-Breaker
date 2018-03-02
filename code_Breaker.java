import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Author: David Tuck and Abdalla Date: Mar 1, 2018
 */

public class code_Breaker {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {// David and Abdalla
		final int SIZE = 4;// max Number of guesses
		final int TRIES = 10;
		final String VALID_CHARS = ("GRBYOP");
		createCode(VALID_CHARS, SIZE);
		String[] array = new String[] { "a", "b", "c", "d" };
		String[] array2 = new String[] { "a", "b", "c", "e" };

		System.out.println(Arrays.toString(removeFullyCorrect(array, array2)));

	}

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

	public static String getinput(int size, String valid_chars) {
		boolean valid = true;
		String answer;
		do {
			valid = true;
			System.out.print("Please enter your guess of length " + (size + 1) + " using the letters " + valid_chars);
			answer = input.nextLine();
			if (answer.length() != size || answer.matches(valid_chars)) {

				System.out.println("yes");

			}
		} while (valid == false);

		return answer;
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
