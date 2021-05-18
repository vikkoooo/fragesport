package fragesport;

/**
 * Programmering i Java, DA556B. Projekt
 * 
 * Main method to start the UI
 * 
 * @author Viktor Lundberg, viktor.lundberg0026@stud.hkr.se
 * @date 2021-05-18
 */

public class MainQuiz {

	/**
	 * Main method to start the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Start UI with param the questions you want to load
		new UI().run("src/fragesport/db/questions_db.csv");

	}

}
