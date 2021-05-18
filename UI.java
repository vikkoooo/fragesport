package fragesport;

import java.util.Scanner;

/**
 * Programmering i Java, DA556B. Projekt
 * 
 * Console UI class for the quiz game
 * 
 * @author Viktor Lundberg, viktor.lundberg0026@stud.hkr.se
 * @date 2021-05-18
 */

public class UI {
	
	// Variables
	private Scanner myScan;
	private Logic logic;

	/**
	 * Starts console UI for the quiz game.
	 * 
	 * @param filePath, the .csv file we want to load our "database" from
	 */
	public void run(String filePath) {
		
		// Initialize variables
		myScan = new Scanner(System.in);

		try {
			logic = new Logic(filePath);
			menu();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Console Menu
	 */
	private void menu() {
		
		//@formatter:off
		System.out.println();
		System.out.println(
				"Menu \n"
				+ "1) Play one random question \n"
				+ "2) Play x number of random questions \n"
				+ "3) See current score \n"
				+ "4) See played questions \n"
				+ "5) Restart (reset score and questions database) \n"
				+ "7) Help me! \n"
				+ "8) List all questions with answers (CHEATMODE) \n"
				+ "9) Exit program\n");
		//@formatter:on

		// Get input from user
		int choice = 0;
		System.out.print("Choice: ");

		try {
			choice = Integer.parseInt(myScan.nextLine());
			System.out.println();
		} catch (NumberFormatException e) {
			System.out.println("Please enter 1 - 5 and 7 - 9 as input only");
			menu();
		}

		// Will invoke one of the options depending of user choice
		switch (choice) {
		case 1:
			play(1);
			menu();
			break;

		case 2:
			System.out.print("Enter how many questions you want to play: ");
			int n = Integer.parseInt(myScan.nextLine());
			play(n);
			menu();
			break;

		case 3:
			printCurrentScore();
			menu();
			break;

		case 4:
			printHistory();
			menu();
			break;

		case 5:
			restart();
			menu();
			break;

		case 7:
			help();
			menu();
			break;

		case 8:
			printRemainingQuestions();
			menu();
			break;

		case 9:
			exit();
			break;

		default:
			System.out.println("Please enter 1 - 5 and 7 - 9 as input only");
			menu();
		}
	}

	/**
	 * Play one random question. This method is recursive, meaning when calling
	 * this method, choose how many questions you want to run.
	 * 
	 * @param n, number of questions to run
	 * @return n, the remaining number of questions to run recursive
	 */
	private int play(int n) {
		
		// Base case
		if (n == 0) {
			menu();
			return 0;
		} else {
			// Get a question from logic
			Question question = logic.getRandomQuestion();

			// Print question
			System.out.println("Question number " + question.getId() + ") \n" + question.getQuestion());
			System.out.println();
			System.out.println("1) " + question.getAnswer1());
			System.out.println("2) " + question.getAnswer2());
			System.out.println("3) " + question.getAnswer3());
			System.out.println("4) " + question.getAnswer4());

			// Get the correct answer (print is optional)
			String correctAnswer = question.getCorrectAnswer();
			System.out.println("\n(CHEATMODE) - Correct answer: " + correctAnswer);

			// Find out which is the correct alternative
			int correctAlternative = 0;

			if (correctAnswer.equals(question.getAnswer1())) {
				correctAlternative = 1;
			}
			if (correctAnswer.equals(question.getAnswer2())) {
				correctAlternative = 2;
			}
			if (correctAnswer.equals(question.getAnswer3())) {
				correctAlternative = 3;
			}
			if (correctAnswer.equals(question.getAnswer4())) {
				correctAlternative = 4;
			}

			// (Optional code) print the correct alternative
			System.out.println("(CHEATMODE) - Correct alternative: " + correctAlternative);
			System.out.println();

			// Checks user answer
			System.out.print("Answer: ");
			int userAnswer = Integer.parseInt(myScan.nextLine());

			if (userAnswer == correctAlternative) {
				logic.correct();
				System.out.println("Correct!");
			} else {
				logic.wrong();
				System.out.println("Wrong!");
			}
			// Show score after each question
			printCurrentScore();
		}
		// Call remaining number of questions recursive
		return play(n - 1);
	}

	/**
	 * Prints current score. Correct answers, wrong answer, how many questions we
	 * played and how many is remaining.
	 */
	private void printCurrentScore() {
		System.out.println("Current score");
		System.out.println("Correct answers: " + logic.getCorrect());
		System.out.println("Wrong answers: " + logic.getWrong());
		System.out.println("Questions played: " + logic.getHistory().size());
		System.out.println("Questions remaining: " + logic.getCurrentQuestions().size());
	}

	/**
	 * Prints history, which questions have we already played?
	 */
	private void printHistory() {
		if (logic.getHistory().isEmpty()) {
			System.out.println("History is empty");
		} else {
			for (Question e : logic.getHistory()) {
				System.out.println(e.toString());
			}
		}
	}

	/**
	 * Restart the game. Reset score, database and history.
	 */
	private void restart() {
		logic.reset();

		System.out.println(
				"Resetted. Added " + logic.getCurrentQuestions().size() + " questions from database to game client");
		System.out.println();
		printCurrentScore();
	}

	/**
	 * Help text to the user who might be confused
	 */
	private void help() {
		System.out.println("Start the quiz from MainQuiz.java where you call the UI.run() method");
		System.out.println("In the console UI buttons 1-5 are game friendly, and 7-9 other options.");
		System.out.println("By default the game will print correct answers as well, this is to easier");
		System.out.println("understand that all the functions work. In the live version of the program");
		System.out.println("this is obviously disabled.");
		System.out.println("System will give you random questions and keep track of score, until you reset the score.");
	}

	/**
	 * Prints all questions remaining in the "database".
	 */
	private void printRemainingQuestions() {
		for (Question e : logic.getCurrentQuestions()) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Exit the program, close scanner.
	 */
	private void exit() {
		myScan.close();
		System.out.println("Closing program..");
	}

}
