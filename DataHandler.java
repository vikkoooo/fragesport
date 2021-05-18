package fragesport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Programmering i Java, DA556B. Projekt
 * 
 * Class to handle IO operations. Will try to read questions with answers from
 * .csv file.
 * 
 * @author Viktor Lundberg, viktor.lundberg0026@stud.hkr.se
 * @date 2021-05-18
 */

public class DataHandler {
	
	// List with question objects. Serves as "database".
	public ArrayList<Question> database;

	/**
	 * Constructor. Initialize "database" list, load data and sort it.
	 * 
	 * @param filePath, the path to the .csv file we want to load
	 * @throws Exception, if anything went wrong reading the file
	 */
	public DataHandler(String filePath) throws Exception {
		database = new ArrayList<>();
		loadData(filePath);
		sortData(database);
	}

	/**
	 * Loads data from .csv to ArrayList "database"
	 * 
	 * @param filePath, the path to the .csv file we want to load
	 * @throws Exception, if anything went wrong reading the file
	 */
	public void loadData(String filePath) throws Exception {
		
		// To show time elapsed
		long timeBefore = System.nanoTime();

		// Reader
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);

		// Line to read
		String line;

		// As long as next line in BufferedReader is not empty, we have data to read
		while ((line = br.readLine()) != null) {
			// Split into array of size = 7 with delimiter ";"
			String[] splitted = line.split(";", 7);
			Question question = new Question(splitted);
			database.add(question);
		}
		// Close reader
		br.close();

		// Print time elapsed
		System.out.println("Successfully loaded " + database.size() + " questions into ArrayList. Time elapsed: "
				+ (System.nanoTime() - timeBefore) / 1000000 + " ms");
	}

	/**
	 * Java API sort. The questions have id, and this method is to make sure the
	 * program can grow. Java uses MergeSort algorithm for objects. Will run
	 * O(nlogn) time.
	 * 
	 * If the programs list would grow to thousands of questions we might have to
	 * use binary search for some operations in the list. This makes sure we can let
	 * the program grow.
	 * 
	 * @param list to sort
	 */
	public void sortData(ArrayList<Question> list) {
		long timeBefore = System.nanoTime();

		// Sort Question objects by getId
		Collections.sort(list, Comparator.comparing(Question::getId));

		System.out.println("Successfully sorted " + database.size() + " elements ascending. Time elapsed: "
				+ (System.nanoTime() - timeBefore) / 1000000 + " ms");
	}

}
