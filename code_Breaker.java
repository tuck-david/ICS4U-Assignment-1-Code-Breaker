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
		final int SIZE = 4;// The size of the code that is trying to guessed
		final int TRIES = 10;// The number of Guesses the user has to guess the code in one game
		boolean playAgain = false;// Variable to control if a game is to be played again or not
		final String VALID_CHARS = ("GRBYOP");// String that defines all acceptable colors that are to be used
		System.out.println("Hello and Welcome to the code breaker game");
		boolean displaygenCode = false;
		do {// Beginning of new game
			displaygenCode = false;
			System.out.println("If you would like to see the rules please enter 'r' now.\nor enter anything else to continue");
			if (input.nextLine().equalsIgnoreCase("r")) {// checks to see if user wants to see the rules
				rules(VALID_CHARS, SIZE, TRIES);
			}
			System.out.println(
					"For marking purpose would you like to see what the current code is?\nEnter 'y' for yes, any other key for no.");//
			if (input.nextLine().equalsIgnoreCase("y")) {// checks to see if user wants to see the rules
				displaygenCode = true;
			}
			System.out.println("\t**********NEW GAME**********");
			int tryCount = 0;// Counts how many guesses user has made
			boolean exit = false;// boolean to track if the game should end prematurely
			String[][] guessHistory = new String[TRIES][SIZE];// 2d array the stores all guesses
			String[][] clueHistory = new String[TRIES][SIZE];// 2D array that stores all clues
			for (String[] strings : clueHistory) {// fills clueHistory array so there is no null
				Arrays.fill(strings, "");
			}
			for (String[] strings : guessHistory) {// fills guessHistory array so there is no null
				Arrays.fill(strings, "");
			}
			playAgain = false;// playAgain is set to false so game is not looped infinitely
			String[] genCode = createCode(VALID_CHARS, SIZE);// genCode is the code the user is trying to guess

			do {// loop for each guess of a game
				String[] genCodeCOPY = genCode.clone();// gencCodeCOPY is a copy of the values of gencode. When evaluating
														// for clues genCode would be modified therefore I made a copy and
														// only genCodeCOPY is modified.

				// code for advancing the state of the game
				if (displaygenCode == true) {
					System.out.println("GENERATED CODE:" + Arrays.toString(genCode));
				}
				System.out.println(displayGame(guessHistory, clueHistory, tryCount));// displays the game-board
				String[] currentGuess = getInput(SIZE, VALID_CHARS);// after game is display user call getInput
				for (int i = 0; i < SIZE; i++) {// adds to the guessHistory array in the row of the current tryCount
					guessHistory[tryCount][i] = currentGuess[i];
				}
				String[] fullyCorrectAns = findFullyCorrect(genCode, currentGuess);
				// fullyCorrectAns is the "b" component the the clues. The size of
				// fullyCorrectAns is exactly equal to the number of "b"
				currentGuess = removeFullyCorrect(genCodeCOPY, currentGuess);

				String[] colourCorrctAns = findColourCorrect(genCodeCOPY, currentGuess);
				// colourCorrctAns is the "w" component the the clues. The size of
				// colourCorrctAns is exactly equal to the number of "w"
				int writePos = 0;// Variable to track where I have written to in the clueHistory array

				for (int i = 0; i < fullyCorrectAns.length; i++) {// adds fullyCorrectAns to tryCount row in clueHistory
					clueHistory[tryCount][writePos] = fullyCorrectAns[i];
					writePos++;// writePos is ++ so that the position of the next clue is in the next index
				}
				for (int i = 0; i < colourCorrctAns.length; i++) {// adds colourCorrctAns to tryCount row in clueHistory
					clueHistory[tryCount][writePos] = colourCorrctAns[i];
					writePos++;// writePos is ++ so that the position of the next clue is in the next index
				}
				if (fullyCorrectAns.length == SIZE) {// if the fullyCorrectAns is the same size as SIZE then the
					// user has guessed the correct code
					System.out.println(displayGame(guessHistory, clueHistory, tryCount + 1));// displays the game-board
					System.out.println("Congratulations! it took you " + (tryCount + 1) + " guess to find the code");
					exit = true;
					break;
				} else if (tryCount >= TRIES - 1) {// checks to see if user has used all of your guesses
					System.out.println(displayGame(guessHistory, clueHistory, tryCount + 1));// displays the game-board
					System.out.println("I'm sorry you lose. The correct code was " + Arrays.toString(genCode));
					exit = true;
				}
				tryCount++;

			} while (exit == false);

			String playAgainAnswer;
			do {// do loop is for looping the game so you can play more than one game
				System.out.println("Do you want to play again. press y for yes and press n for no");
				playAgainAnswer = input.nextLine();
			} while (valid(("yn"), 1, playAgainAnswer) == false);// if user enters "y" a new game is started
			if (playAgainAnswer.equalsIgnoreCase("y")) {
				playAgain = true;
			}
		} while (playAgain == true);// Loop for multiple games
		System.out.println("Thank you for playing code breaker we hope you come back soon!!!");
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
			System.out.print(
					"Please enter your guess" + again + "with a length of " + (SIZE) + " using the letters " + VALID_CHARS);
			System.out.println();
			currentGuess = input.nextLine();
			currentGuess = currentGuess.toUpperCase();
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
	 *            An array with the users current guess with each color in a
	 *            separate index of one character.
	 * @return If the guess has none of the correct color or position then the
	 *         method returns an array with length 0. If guess has any amount of
	 *         colors and in the correct position will output a number amount of
	 *         correct with b indicating a black peg and that the position and color
	 *         is correct.
	 */
	public static String[] findFullyCorrect(String[] gencode, String[] currentGuess) {// Abdalla
		int blacks = 0;// the size and number of blacks to return
		for (int i = 0; i < gencode.length; i++) {
			if (gencode[i].equals(currentGuess[i]))// compares the same index of gencode with the same index of currentGuess
				blacks++;
		}

		String[] numOfB = new String[blacks];
		for (int i = 0; i < numOfB.length; i++) {// fill the array with "b", blacks number of times
			numOfB[i] = "b";
		}
		return numOfB;
	}

	/**
	 * If the index of currentGuess and the index of gencodeCOPY are the same then
	 * set that index to "/" for currentGuess and "*" for gencodeCOPY. The correct
	 * guesses must be changed so that the find color correct method does not count
	 * these guesses again.
	 *
	 * @param gencodeCOPY
	 *            Compares the generated code that the computer has made in random
	 *            to the code the user has guessed.
	 * @param currentGuess
	 *            An array with the users current guess with each color in a
	 *            separate index of one character.
	 * @return Returns currentGuess with correct guesses set to "/".
	 */
	public static String[] removeFullyCorrect(String[] gencodeCOPY, String[] currentGuess) {// David
		for (int i = 0; i < gencodeCOPY.length; i++) {
			if (gencodeCOPY[i].equals(currentGuess[i])) {
				currentGuess[i] = "/";// sets currentGuess at the current index to "/" so that it is not equal again
				gencodeCOPY[i] = "*";// sets gencodeCOPY at the current index to "*" so that it is not equal again
			}
		}
		return currentGuess;
	}

	/**
	 * Checks to see if the user has the correct color guesses but in the wrong
	 * position.
	 *
	 * @param gencodeCOPY
	 *            Compares the removeFullyCorrent version of gencodeCOPY. This is
	 *            the code that the user is trying to guess.
	 * @param currentGuess
	 *            An array with the users current guess with each color in a
	 *            separate index of one character.
	 * @return Returns an array containing the number of correct colors with each
	 *         index set to "w". The size of the return array has a max value of
	 *         SIZE, and a minimum size of 0.
	 */
	public static String[] findColourCorrect(String[] gencodeCOPY, String[] currentGuess) {// David
		ArrayList<String> holderList = new ArrayList<String>();// used an arrayList because it is dynamic
		for (int i = 0; i < currentGuess.length; i++) {// loop to iterate thought each character in currentGuess
			for (int a = 0; a < gencodeCOPY.length; a++) {// loop to iterate thought each character in gencodeCOPY
				if (currentGuess[i].equals(gencodeCOPY[a])) {
					holderList.add("w");
					gencodeCOPY[a] = "*";// changes value to "*" so it does not equal true again
					break;// breaks and goes to next character in currentGuess
				}
			}
		}
		String[] myArray = holderList.toArray(new String[holderList.size()]);// array that is returned
		return myArray;
	}

	/**
	 * Display game combines all of the previous guesses and previous clues and
	 * combines them into a string that can be return. Each letter is separated by a
	 * space and the guesses and clues are separated by a tab.
	 *
	 * @param guessHistory
	 *            A 2D array that holds all of the user guesses. This is necessary
	 *            to display so that the user knows what there previous guesses were
	 *            and what the clues were.
	 *
	 * @param clueHistory
	 *            A 2D array that holds all of the user clues. This is necessary to
	 *            display so that the user knows what there previous cues were.
	 * @param tryCount
	 *            An int the specifies what guess the user is on. This allows only
	 *            the relevant rows of guessHistory and clueHistory to be displayed.
	 *
	 * @return Returns a properly formated header with labels as well as the users
	 *         precious entered guesses and clues.
	 */
	public static String displayGame(String[][] guessHistory, String[][] clueHistory, int tryCount) {// David
		String toDisplay = "Guess \t \tClues\n****************\n";
		String temp;
		for (int i = 0; i < tryCount; i++) {// Adds multiple rows
			for (int j = 0; j < guessHistory[0].length; j++) {// adds guess history
				temp = guessHistory[i][j];
				toDisplay = toDisplay + temp + " ";// adds to toDisplay
			}
			toDisplay += "\t";// format to add new line
			for (int j = 0; j < guessHistory[0].length; j++) {// adds clue history
				temp = clueHistory[i][j];
				toDisplay = toDisplay + temp + " ";// adds to toDisplay
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
				+ "In addition b=black and w= white. To win you must place the right pegs in the right order.\n"
				+ "After each attempt you will be told how you  went.\n\n"
				+ "White clue - Means that there is one of your colors is in the code but not in  the right place. \n"
				+ "Black clue - Means that there is one of your colors in the code and in the right place. \n"
				+ "The answer clues are not in any particular order and don't line up with your guess colors.");
	}
}
