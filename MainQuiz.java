package fragesport;

/**
 * @author viktorlundberg
 *
 */

public class MainQuiz
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Start UI / GUI with param the questions you want to load
		new UI().run("src/fragesport/db/questions_db.csv");
	}

}
