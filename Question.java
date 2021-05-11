package fragesport;

public class Question
{

	// Instance variables
	private int id;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String correctAnswer;

	public Question(String[] splitted)
	{
		id = Integer.parseInt(splitted[0]);
		question = splitted[1];
		answer1 = splitted[2];
		answer2 = splitted[3];
		answer3 = splitted[4];
		answer4 = splitted[5];
		correctAnswer = splitted[6];
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the question
	 */
	public String getQuestion()
	{
		return question;
	}

	/**
	 * @return the answer1
	 */
	public String getAnswer1()
	{
		return answer1;
	}

	/**
	 * @return the answer2
	 */
	public String getAnswer2()
	{
		return answer2;
	}

	/**
	 * @return the answer3
	 */
	public String getAnswer3()
	{
		return answer3;
	}

	/**
	 * @return the answer4
	 */
	public String getAnswer4()
	{
		return answer4;
	}

	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer()
	{
		return correctAnswer;
	}

	@Override
	public String toString()
	{
		return "Id: " + id + ", Question: " + question + ", | Answer1: " + answer1 + ", | Answer2: " + answer2
				+ ", | Answer3: " + answer3 + ", Answer4: " + answer4 + ", | CorrectAnswer: " + correctAnswer;
	}

}
