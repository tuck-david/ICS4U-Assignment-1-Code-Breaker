import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Author: David Tuck and Abdalla Date: Mar 1, 2018
 */
/*
 * You get 8 attempts at breaking the code. If you can't discover it by then
 * then you lose that game. But that's OK. You can get a new code and try again.
 *
 * The Code Breaker code is made up of 5 colors from the 8 available colors. To
 * win you must place the right marbles in the right order. After each attempt
 * you will be told how you went.
 *
 * White marble - Means that there is one of your marbles in the code but not in
 * the right place. Black marble - Means that there is one of your marbles in
 * the code and in the right place. The answer marbles are not in any particular
 * order and don't line up with your code marbles.
 */
public class code_Breaker {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {// David and Abdalla
		final int SIZE = 4;
		final int TRIES = 10;
		boolean playAgain = false;

		final String VALID_CHARS = ("GRBYOP");
		do {// Beginning of new game
			int tryCount = 0;// Counts how many guesses user has made
			boolean exit = false;
			String[][] guessHistory = new String[TRIES][SIZE];// 2d array the stores all guesses
			String[][] answerHistory = new String[TRIES][VALID_CHARS.length()];
			playAgain = true;
			String[] genCode = createCode(VALID_CHARS, SIZE);// genCode is the code the user is trying to guess
			do {// loop for each guess of a game

				if (tryCount >= SIZE - 1) {
					System.out.println("I'm sorry you lose. The correct code was " + Arrays.toString(genCode));

					exit = true;
				} else {
					System.out.println(displayGame(guessHistory, answerHistory));
					String[] currentGuess = getinput(SIZE, VALID_CHARS, tryCount, guessHistory);
					if (currentGuess == genCode) {
						System.out.println("Congratulations! it took you " + (tryCount + 1) + " to find the code");
						exit = true;
						break;
					}
					String[] fullyCorrectAns = findFullyCorrect(genCode, currentGuess);
					currentGuess = removeFullyCorrect(genCode, currentGuess);
					String[] colourCorrctAns = findColourCorrect(genCode, currentGuess);
					for (int i = 0; i < SIZE; i++) {
						answerHistory[tryCount][i] = fullyCorrectAns[i];
					}
					for (int i = 0; i < SIZE; i++) {
						answerHistory[tryCount][i] = fullyCorrectAns[i];
					}

				}
			} while (exit == false);
			String playAgainAnswer;
			do {// do loop is for looping the game so you can play more than one game
				System.out.println("Do you want to play again. y-yes n-no");
				playAgainAnswer = input.nextLine();
			} while (valid(("yn"), 1, playAgainAnswer) == false);
			if (playAgainAnswer.equals("n")) {
				playAgain = false;
			}
		} while (playAgain = true);

	}

	public static String[] createCode(String VALID_CHARS, int size) {// Abdalla
		char[] chars = new char[VALID_CHARS.length() - 1];
		for (int i = 0; i < VALID_CHARS.length() - 1; i++) {
			chars[i] = VALID_CHARS.charAt(i);
		}
		String[] code = new String[size];
		for (int i = 0; i < size; i++) {
			code[i] = Character.toString(VALID_CHARS.charAt((int) (Math.random() * 6)));
		}

		return code;
	}

	public static String[] getinput(int size, String valid_chars, int tryCount, String[][] guessHistory) {
		boolean valid = true;
		char[] chars = new char[valid_chars.length() - 1];
		String answer;
		String again = " ";
		String[] currectAnswer = new String[size];
		do {
			valid = true;
			System.out.print(
					"Please enter your guess" + again + "of length " + (size) + " using the letters " + valid_chars);
			System.out.println();
			answer = input.nextLine();

			if (valid(valid_chars, size, answer) == false) {
				valid = false;
				again = " again ";
			}

			for (int i = 0; i < answer.length(); i++) {
				currectAnswer[i] = Character.toString(answer.charAt(i));
			}
		} while (valid == false);

		return currectAnswer;
	}

	public static boolean valid(String VALID_CHARS, int size, String guess) {// Abdalla
		boolean guessHas = true;
		if (guess.length() != size) {
			return false;
		} else {
			for (int i = 0; i < size; i++) {
				if (!VALID_CHARS.contains(Character.toString(guess.charAt(i)))) {
					guessHas = false;
				}

			}
			return guessHas;
		}
	}

	public static String[] findFullyCorrect(String[] gencode, String[] currentGuess) {// Abdalla
		return gencode;// Too change
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

	public static String displayGame(String[][] guessHistory, String[][] answerHistory) {// David
		String toDisplay = "Guess \tClues\n****************\n";

		return toDisplay;
	}

}
