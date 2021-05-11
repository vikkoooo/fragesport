package fragesport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataHandler
{
	// List with question objects
	public ArrayList<Question> database = new ArrayList<>();

	/**
	 * @param filePath
	 * @throws IOException
	 */
	public void loadData(String filePath) throws Exception
	{
		long timeBefore = System.nanoTime();

		// Reader
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);

		String line;

		// As long as next tuple in BufferedReader is not empty, we have data to read
		while ((line = br.readLine()) != null)
		{
			// Split into array of size = 7 with delimiter ";"
			String[] splitted = line.split(";", 7);
			Question question = new Question(splitted);
			database.add(question);
		}
		br.close();
		
		System.out.println("Successfully loaded " + database.size() + " questions into ArrayList. Time elapsed: " + (System.nanoTime() - timeBefore) / 1000000 + " ms");

	}

	/**
	 * java api sort. mergesort algo for objects
	 */
	public void sortData(ArrayList<Question> list)
	{
		long timeBefore = System.nanoTime();

		Collections.sort(list, Comparator.comparing(Question::getId));
		
		System.out.println("Successfully sorted " + database.size() + " elements ascending. Time elapsed: " + (System.nanoTime() - timeBefore) / 1000000 + " ms");

	}

	
	/**
	 * Insertion sort. fungerar inte, men mer optimalt för att sortera halvsorterade listor.
	 * @param list
	 */
	public void insertionSort(ArrayList<Question> list)
	{
		/* Function to sort array using insertion sort */
		{
			
			int n = list.size();
			for (int i = 1; i < n; ++i)
			{
				//int key = list.get(i).getId();
				Question key = list.get(i);
				int j = i - 1;

				/*
				 * Move elements of arr[0..i-1], that are greater than key, to one position
				 * ahead of their current position
				 */
				while (j >= 0 && list.get(j).getId() > key.getId())
				{
					//arr[j + 1] = arr[j];
					list.add(j + 1, list.get(j));
					
					j = j - 1;
				}
				list.set(j + 1, key);
			}
		}
	}

}
