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
					List<String> answerHistoryList = new ArrayList<String>();
					for (int i = 0; i < SIZE; i++) {
						answerHistory[tryCount][i] = fullyCorrectAns[i];
					}
					for (int i = 0; i < SIZE; i++) {
						answerHistory[tryCount][i] = colourCorrctAns[i];
					}

				}
				tryCount++;
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

			for (int i = 0; i < answer.length(); i++) {// converts string to single char arrays
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
		 int blacks=0;
        for(int i=0;i<gencode.length;i++){
        for (int j=0;j<gencode.length;j++){
            if(gencode[i]==currentGuess[j])
                blacks++;
	}
        }
		if (blacks==0){ 
			System.out.println("There are no colors that are correct");
		}
		else{
			System.out.println( blacks + " colors are correct");
		}

	}
