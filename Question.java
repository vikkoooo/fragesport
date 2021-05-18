package fragesport;

/**
 * Programmering i Java, DA556B. Projekt
 * 
 * Question class that holds all the information of one question
 * 
 * @author Viktor Lundberg, viktor.lundberg0026@stud.hkr.se
 * @date 2021-05-18
 */

public class Question {

	// Instance variables
	private int id;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String correctAnswer;

	/**
	 * Constructor
	 * 
	 * @param the array which is containing one line from .csv file splitted by the
	 *            delimiter ";"
	 */
	public Question(String[] splitted) {
		id = Integer.parseInt(splitted[0]);
		question = splitted[1];
		answer1 = splitted[2];
		answer2 = splitted[3];
		answer3 = splitted[4];
		answer4 = splitted[5];
		correctAnswer = splitted[6];
	}

	/**
	 * Getter for ID
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter for question
	 * 
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * Getter for first answer alternative
	 * 
	 * @return the answer1
	 */
	public String getAnswer1() {
		return answer1;
	}

	/**
	 * Getter for the second answer alternative
	 * 
	 * @return the answer2
	 */
	public String getAnswer2() {
		return answer2;
	}

	/**
	 * Getter for the third answer alternative
	 * 
	 * @return the answer3
	 */
	public String getAnswer3() {
		return answer3;
	}

	/**
	 * Getter for the fourth answer alternative
	 * 
	 * @return the answer4
	 */
	public String getAnswer4() {
		return answer4;
	}

	/**
	 * Getter for the correct answer
	 * 
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * toString method that prints all the information about a question
	 */
	@Override
	public String toString() {
		return "Id: " + id + ", Question: " + question + ", | Answer1: " + answer1 + ", | Answer2: " + answer2
				+ ", | Answer3: " + answer3 + ", Answer4: " + answer4 + ", | CorrectAnswer: " + correctAnswer;
	}

}
