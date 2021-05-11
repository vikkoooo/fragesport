package fragesport;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author viktorlundberg
 *
 */

public class UI
{
	// Variables
	Scanner myScan;
	DataHandler data;

	// Temp lists
	ArrayList<Question> currentQuestions;
	ArrayList<Question> history;

	// Score
	int correct;
	int wrong;

	/**
	 * Starts program
	 */
	public void run(String filePath)
	{
		// Initialize variables
		myScan = new Scanner(System.in);
		data = new DataHandler();
		correct = 0;
		wrong = 0;

		try
		{
			// Load and sort data
			data.loadData(filePath);
			data.sortData(data.database);

			currentQuestions = new ArrayList<>(data.database.size());
			history = new ArrayList<>(data.database.size());
			currentQuestions.addAll(data.database);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			System.out.println();
			menu();
		}
	}

	/**
	 * Menu
	 */
	private void menu()
	{
		int choice = 0;

		//@formatter:off
		System.out.println(
				"Menu \n"
				+ "1) List all questions with answers \n"
				+ "2) Play one question \n"
				+ "3) Play x number of random questions \n"
				+ "4) See current score \n"
				+ "5) See played questions \n"
				+ "6) Restart (reset score and questions database) \n"
				+ "7) Help me! \n"
				+ "8) Test \n"
				+ "9) Exit program\n");
		//@formatter:on

		System.out.print("Choice: ");

		try
		{
			choice = Integer.parseInt(myScan.nextLine());
			System.out.println();
		}
		catch (NumberFormatException e)
		{
			System.err.println("Please enter 1 - 9 as input only");
			menu();
		}

		switch (choice)
		{
		case 1:
			listAll();
			break;
		case 2:
			play(1);
			break;
		case 3:
			System.out.print("Enter how many questions you want to play: ");
			int m = Integer.parseInt(myScan.nextLine());
			play(m);
			break;
		case 4:
			currentScore(true);
			break;
		case 5:
			history();
			break;
		case 6:
			restart();
			break;
		case 7:
			help();
			break;
		case 8:
			test();
			break;
		case 9:
			exit();
			break;
		default:
			System.err.println("Please enter 1 - 9 as input only");
			menu();
		}
	}

	/**
	 * 
	 */
	private void listAll()
	{
		for (Question e : currentQuestions)
		{
			System.out.println(e.toString());
		}
		System.out.println();
		menu();
	}

	private int randQuestion()
	{
		Random rand = new Random();
		int nextQuestion = rand.nextInt(currentQuestions.size());

		return nextQuestion;
	}

	private int play(int n)
	{
		if (n == 0)
		{
			menu();
			return 0;
		}
		else
		{
			int index = randQuestion();
			Question question = currentQuestions.get(index);

			System.out.println("Question number " + index + ") \n" + question.getQuestion());
			System.out.println();
			System.out.println("1) " + question.getAnswer1());
			System.out.println("2) " + question.getAnswer2());
			System.out.println("3) " + question.getAnswer3());
			System.out.println("4) " + question.getAnswer4());

			String correctAnswer = question.getCorrectAnswer();
			System.out.println("\n(CHEATMODE) - Correct answer: " + correctAnswer);

			int correctAlternative = 0;

			if (correctAnswer.equals(question.getAnswer1()))
			{
				correctAlternative = 1;
			}
			if (correctAnswer.equals(question.getAnswer2()))
			{
				correctAlternative = 2;
			}
			if (correctAnswer.equals(question.getAnswer3()))
			{
				correctAlternative = 3;
			}
			if (correctAnswer.equals(question.getAnswer4()))
			{
				correctAlternative = 4;
			}

			System.out.println("(CHEATMODE) - Correct alternative: " + correctAlternative);
			System.out.println();

			// Om selected svar från användare == correct svar enligt fråga, poäng
			System.out.print("Answer: ");
			int userAnswer = Integer.parseInt(myScan.nextLine());

			if (userAnswer == correctAlternative)
			{
				correct++;
				System.out.println("Correct!");
			}
			else
			{
				wrong++;
				System.out.println("Wrong!");
			}

			history.add(question);
			currentQuestions.remove(index);
			System.out.println();
			currentScore(false);
		}

		return play(n - 1);
	}

	private void currentScore(boolean menu)
	{
		System.out.println("Current score");
		System.out.println("Correct answers: " + correct);
		System.out.println("Wrong answers: " + wrong);
		System.out.println("Questions played: " + history.size());
		System.out.println("Questions remaining: " + currentQuestions.size());
		System.out.println();

		if (menu == true)
		{
			menu();
		}
	}

	private void history()
	{
		for (Question e : history)
		{
			System.out.println(e.toString());
		}
		System.out.println();
		menu();
	}

	private void restart()
	{
		currentQuestions.clear();
		history.clear();
		currentQuestions.addAll(data.database);
		correct = 0;
		wrong = 0;
		System.out.println("Resetted. Added " + data.database.size() + " questions from database to game client");
		System.out.println();
		menu();
	}

	private void help()
	{
		System.out.println("Insert text to help user here");
		System.out.println();
		menu();
	}

	private void test()
	{
		System.out.println("test method start");
		menu();
	}

	private void exit()
	{
		myScan.close();
		System.out.println("Closing program..");
	}

}
