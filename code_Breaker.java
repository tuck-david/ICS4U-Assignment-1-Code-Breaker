import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;
import java.util.Set;

/**
 * Date: Mar 1, 2018 Assignment: Code Breaker
 *
 * @Author David Tuck
 * @author Abdalla
 */

public class code_Breaker {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {// David
		final int SIZE = 5;// The size of the code that is trying to guessed
		final int TRIES = 10;// The number of Guesses the user has to guess the code in one game
		boolean playAgain = false;// Variable to control if a game is to be played again or not
		final String VALID_CHARS = ("GRBYOP");// String that defines all acceptable colors that are to be used
		System.out.println("Hello and Welcome to the code breaker game");
		do {// Beginning of new game
			System.out.println("If you would like to see the rules please enter 'r' now. Enter anything else to continue");
			if (input.nextLine().equalsIgnoreCase("r")) {// checks to see if user wants to see the rules
				rules(VALID_CHARS, SIZE, TRIES);
			}
			System.out.println("\t**********NEW GAME**********");
			int tryCount = 0;// Counts how many guesses user has made
			boolean exit = false;// boolean to track if the game should end prematurely
			String[][] guessHistory = new String[TRIES][SIZE];// 2d array the stores all guesses
			String[][] answerHistory = new String[TRIES][VALID_CHARS.length()];// 2D array that stores all answers
			for (String[] strings : answerHistory) {// fills answerHistory array so there is no null
				Arrays.fill(strings, "");
			}
			for (String[] strings : guessHistory) {// fills guessHistory array so there is no null
				Arrays.fill(strings, "");
			}
			playAgain = false;// playAgain is set to false so game is not looped infinitely
			String[] genCode = createCode(VALID_CHARS, SIZE);// genCode is the code the user is trying to guess

			do {// loop for each guess of a game
				String[] genCodeCOPY = genCode.clone();
				if (tryCount >= TRIES) {// checks to see if user has used all of there guesses
					System.out.println("I'm sorry you lose. The correct code was " + Arrays.toString(genCode));
					exit = true;
				} else {
					// code for advancing the state of the game
					System.out.println(Arrays.toString(genCode));
					System.out.println(displayGame(guessHistory, answerHistory, tryCount));// displays the game-board
					String[] currentGuess = getInput(SIZE, VALID_CHARS);// after game is display user call getInput
					for (int i = 0; i < SIZE; i++) {// adds to the guessHistory array in the row of the current tryCount
						guessHistory[tryCount][i] = currentGuess[i];
					}
					String[] fullyCorrectAns = findFullyCorrect(genCode, currentGuess);
					// fullyCorrectAns is the "b" component the the clues. The size of
					// fullyCorrectAns is exactly equal to the number of "b"
					if (fullyCorrectAns.length == SIZE) {// if the fullyCorrectAns is the same size as SIZE than the
															// user has guessed the correct code
						System.out.println("Congratulations! it took you " + (tryCount + 1) + " guess to find the code");
						exit = true;
						break;
					}
					currentGuess = removeFullyCorrect(genCodeCOPY, currentGuess);
					// fullyCorrectAns is the "w" component the the clues. The size of
					// fullyCorrectAns is exactly equal to the number of "w"
					String[] colourCorrctAns = findColourCorrect(genCodeCOPY, currentGuess);
					int writePos = 0;// Variable to track where i have written to in the aswerHistory array
					for (int i = 0; i < fullyCorrectAns.length; i++) {// adds fullyCorrectAns to tryCount row in aswerHistory
						answerHistory[tryCount][writePos] = fullyCorrectAns[i];
						writePos++;// wrtiePos is ++ so that the position of the next clue is in the next index
					}
					for (int i = 0; i < colourCorrctAns.length; i++) {// adds colourCorrctAns to tryCount row in aswerHistory
						answerHistory[tryCount][writePos] = colourCorrctAns[i];
						writePos++;// wrtiePos is ++ so that the position of the next clue is in the next index
					}
				}
				tryCount++;
			} while (exit == false);
			String playAgainAnswer;
			do {// do loop is for looping the game so you can play more than one game
				System.out.println("Do you want to play again. press y for yes and press n for no");
				playAgainAnswer = input.nextLine();
			} while (valid(("yn"), 1, playAgainAnswer) == false);// if user enters "r" a new game is started
			if (playAgainAnswer.equalsIgnoreCase("y")) {
				playAgain = true;
			}
		} while (playAgain == true);// Loop for multiple games
		System.out.println("Thank you for playing code breker we hope you come back soon!!!");
	}

	/**
	 * Generates genCode. createCode creates a array with length SIZE using the
	 * characters contained in VALID_CHARS
	 *
	 * @param VALID_CHARS
	 *            Colors that the user would have to use when inputting their guess
	 *            each character in the string represents a different color. Only
	 *            characters in this string are used to make the code out of.
	 * @param SIZE
	 *            The SIZE/length of the code that the user must guess.
	 * @return The variable code is an array that contains the randomly generated
	 *         code that the user in trying to guess. Each index of the array holds
	 *         one character that represents a color.
	 */

	public static String[] createCode(String VALID_CHARS, int SIZE) {// Abdalla
		String[] code = new String[SIZE];// Array to hold the randomly generated code
		for (int i = 0; i < SIZE; i++) {
			code[i] = Character.toString(VALID_CHARS.charAt((int) (Math.random() * 6)));
			// Above code sets the i index to a random character of VALID_CHARS
		}
		return code;
	}

	/**
	 * Allows the user to input their guess of the code.
	 *
	 * @param VALID_CHARS
	 *            Colors that the user would have to use when inputting their guess,
	 *            each character in the string represents a different color. Only
	 *            characters in this string are used to make the code out of.
	 * @param SIZE
	 *            The SIZE/length of the code that the user must guess.
	 * @return The guess of the user, if the guess was incorrect the user will be
	 *         able to guess again, until the last attempt, or the user guesses the
	 *         correct code that was generated. currectAnswer is evaluated in other
	 *         methods to check for correctness compared to genCode.
	 */

	public static String[] getInput(int SIZE, String VALID_CHARS) {// David
		boolean valid = true;// boolean that is to be returned
		String currentGuess;// String to hold the current guess of the user
		String again = " ";// String to change what the system outputs
		String[] currectAnswer = new String[SIZE];
		do {
			valid = true;
			System.out.print("Please enter your guess" + again + "with a length of " + (SIZE) + " using the capital letters "
					+ VALID_CHARS);
			System.out.println();
			currentGuess = input.nextLine();
			if (valid(VALID_CHARS, SIZE, currentGuess) == false) {
				// If the method, valid, returns true the currectAnswer is loaded in to an array
				// to be returned, else the method loops and asks for the user to enter the
				// guess again.
				valid = false;// forces loop
				again = " again ";
			} else {
				for (int i = 0; i < currectAnswer.length; i++) {// converts string to single char arrays
					currectAnswer[i] = Character.toString(currentGuess.charAt(i));
				}
			}
		} while (valid == false);// loop in cases of invalid input
		return currectAnswer;
	}

	/**
	 * Checks if the currentGuess is of the same size as SIZE and also only contains
	 * the character found i the VALID_CHARS string.
	 *
	 * @param VALID_CHARS
	 *            Colors that the user would have to use when inputting their guess
	 *            each character in the string represents a different color.
	 * @param SIZE
	 *            The SIZE/length of the code that the user must guess
	 * @param currentGuess
	 *            An array with the users current guess with each color in a
	 *            separate index of one character.
	 * @return If the currentGuess contains one or more characters that VALID_CHARS
	 *         does not have the method will return false. The method will also
	 *         return false if the length of the currentGuess is not the same as the
	 *         value of SIZE
	 */

	public static boolean valid(String VALID_CHARS, int SIZE, String currentGuess) {// Abdalla
		boolean currentGuessHas = true;

		if (currentGuess.length() != SIZE) {
			return false;
		} else {
			for (int i = 0; i < SIZE; i++) {
				if (!VALID_CHARS.contains(Character.toString(currentGuess.charAt(i)))) {
					currentGuessHas = false;
				}
			}
			return currentGuessHas;
		}
	}

	/**
	 * Checks if the user's guess have the correct color and if they are positioned
	 * correctly.
	 *
	 * @param gencode
	 *            The array gencode is an array that contains the randomly generated
	 *            code that the user in trying to guess. Each index of the array
	 *            holds one character that represents a color
	 * @param currentGuess
	 *            Takes the guess of the user and compares it with the generated
	 *            code for the correct color and position
	 * @return If the guess has none of the correct color or position then the
	 *         method returns an array with length 0 if guess has any amount of
	 *         colors and in the correct position will output a number abount of
	 *         correct with b indicating a black peg and that the position and color
	 *         is correct.
	 */
	public static String[] findFullyCorrect(String[] gencode, String[] currentGuess) {// Abdalla

		int blacks = 0;
		for (int i = 0; i < gencode.length; i++) {
			if (gencode[i].equals(currentGuess[i]))
				blacks++;
		}

		String[] toReturn = new String[blacks];
		for (int i = 0; i < toReturn.length; i++) {
			toReturn[i] = "b";
		}
		return toReturn;
	}

	/**
	 * If the index of currentGuess and the index of gencodeCOPY are the same then
	 * set that index to "/" for currentGuess and "*" for gencodeCOPY. The correct
	 * guesses must be changed so that the find color correct method does not count
	 * these guesses again.
	 *
	 * @param gencodeCPOY
	 *            Compares the generated code that the computer has made in random
	 *            to the code the user has guessed.
	 * @param currentGuess
	 *            Takes the guess of the user and compares it with the generated
	 *            code to check for the same length of the generated code
	 * @return If the guess size is not the same will be asked to guess again if the
	 *         guess size is the same as the generated code it will accept the guess
	 *         and return it
	 */
	public static String[] removeFullyCorrect(String[] gencodeCOPY, String[] currentGuess) {// David
		for (int i = 0; i < gencodeCOPY.length; i++) {
			if (gencodeCOPY[i].equals(currentGuess[i])) {
				currentGuess[i] = "/";
				gencodeCOPY[i] = "*";
			}
		}
		return currentGuess;
	}

	/**
	 * Checks to see if the user has the correct color guesses but in the wrong
	 * position.
	 *
	 * @param gencode
	 *            Compares the generated code that the computer has made in random
	 *            to the code the user has guessed.
	 * @param currentGuess
	 *            Takes the guess of the user and compares it with the generated
	 *            code for the correct color.
	 * @return Returns an array containing the number of correct colors with each
	 *         index set to "w". The size of the return array has a max value of
	 *         SIZE, and a minimum size of 0.
	 */
	public static String[] findColourCorrect(String[] gencodeCOPY, String[] currentGuess) {// David
		ArrayList<String> holderList = new ArrayList<String>();// used an arrayList because it is dynamic
		// for (int i = 0; i < currentGuess.length; i++) {
		// if (genCodeList.contains(currentGuess[i])) {
		// holderList.add("w");
		// genCodeList.set(currentGuess[i], "*");
		// }
		// }
		for (int i = 0; i < currentGuess.length; i++) {
			for (int a = 0; a < gencodeCOPY.length; a++) {
				if (currentGuess[i].equals(gencodeCOPY[a])) {
					holderList.add("w");
					gencodeCOPY[a] = "*";
					break;
				}
			}
		}
		String[] myArray = holderList.toArray(new String[holderList.size()]);
		return myArray;
	}

	/**
	 * Display game combines all of the previous guesses and previous clues and
	 * combines them into a string that can be return. Each letter is separated by a
	 * space and the guesses and clues are separated by a tab.
	 *
	 * @param guessHistory
	 * @param answerHistory
	 * @param tryCount
	 * @return
	 */
	public static String displayGame(String[][] guessHistory, String[][] answerHistory, int tryCount) {// David
		String toDisplay = "Guess \t \tClues\n****************\n";
		String temp;
		for (int i = 0; i < tryCount; i++) {// Adds multiple rows
			for (int j = 0; j < guessHistory[0].length; j++) {// adds guess history
				temp = guessHistory[i][j];
				toDisplay = toDisplay + temp + " ";
			}
			toDisplay += "\t";// format to add new line
			for (int j = 0; j < guessHistory[0].length; j++) {// adds clue history
				temp = answerHistory[i][j];
				toDisplay = toDisplay + temp + " ";
			}
			toDisplay += "\n";// format to add new line
		}
		return toDisplay;
	}

	/**
	 * This method 'rules' displays the rules for the user.
	 *
	 * @param VALID_CHARS
	 *            The colors that the user would have to use when inputting their
	 *            guess. Each character in the string represents a different color.
	 * @param SIZE
	 *            The SIZE/length of the code that the user must guess
	 * @return Returns and displays the rules
	 */
	public static void rules(String VALID_CHARS, int SIZE, int TRIES) {
		System.out.println("You get " + TRIES + " attempts at breaking the code. \n"
				+ "If you can't discover it by then, then you lose that game. \n"
				+ "But that's OK. You can get a new code and try again.\n\n" + "The Code Breaker code is made up of " + SIZE
				+ " pegs  from " + VALID_CHARS.length() + " available colors.\n"
				+ "These colors are symbolized as the following characters" + VALID_CHARS + ". \n"
				+ "In addition b=black and w= white. To win you must place the  right marbles in the right order.\n"
				+ "After each attempt you will be told how you  went.\n\n"
				+ "White clue - Means that there is one of your colors is in the code but not in  the right place. \n"
				+ "Black clue - Means that there is one of your colors in the code and in the right place. \n"
				+ "The answer clues are not in any particular order and don't line up with your guess colors.");
	}
}
