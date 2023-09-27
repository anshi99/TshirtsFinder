package com.nagarro.training.asynctask;

import java.io.File;
import java.io.FileReader;

import com.nagarro.training.constants.Constants;
import com.nagarro.training.dao.TShirtDao;
import com.nagarro.training.models.TShirt;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class CSVFileLoaderTask implements Runnable {

	@Override
	public void run() {
		// Initiate an infinite loop
		while (true) {
			try {
				// Get all Files in the folder
				File[] files = getCSVFiles();

				// Read all files in the files array
				for (File file : files) {
					readFile(file);
				}
				// Make the thread sleep for 15 seconds
				Thread.sleep(15000);
			} catch (InterruptedException ie) {
				System.out.println(Constants.THREAD_ERROR);
			} catch (NullPointerException npe) {
				System.out.println(Constants.NO_FILE_FOUND);
			} catch (Exception e) {
				System.out.println(Constants.UNKNOWN_ERROR);
			}
		}
	}

	// Method to get all CSV files in a folder
	public File[] getCSVFiles() throws NullPointerException {
		// Create a null files array object
		File[] files = null;

		try {
			// Get all the CSV Files from the folder
			files = new File(getClass().getResource(Constants.FILES_PATH).getPath()).listFiles((dir, name) -> {
				return name.toLowerCase().endsWith(".csv");
			});
		} catch (NullPointerException npe) {
			throw new NullPointerException();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return all the files
		return files;
	}

	// Method to read the CSV file data and return a ArrayList of TShirt
	public void readFile(File file) {
		try {
			TShirtDao opr = new TShirtDao();
			// Parser for the CSV File to separate the columns with "|"
			CSVParser parser = new CSVParserBuilder().withSeparator('|').build();

			// Read CSV files
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(parser).build();
			// Create an empty String array
			String[] strArr = csvReader.readNext();

			// Iterate to the end of CSV Files
			while ((strArr = csvReader.readNext()) != null) {
				// Check if the TShirt already exists in the database
				if (!opr.checkTShirt(strArr[Constants.COLUMN_ID])) {
					// Add current row to a TShirt Object
					TShirt tShirt = new TShirt();
					tShirt.setID(strArr[Constants.COLUMN_ID]);
					tShirt.setName(strArr[Constants.COLUMN_NAME]);
					tShirt.setColor(strArr[Constants.COLUMN_COLOR]);
					tShirt.setGender(strArr[Constants.COLUMN_GENDER]);
					tShirt.setSize(strArr[Constants.COLUMN_SIZE]);
					tShirt.setPrice(Double.parseDouble(strArr[Constants.COLUMN_PRICE]));
					tShirt.setRating(Double.parseDouble(strArr[Constants.COLUMN_RATING]));
					tShirt.setAvailibility(strArr[Constants.COLUMN_AVAILABILITY]);
					// insert TShirst into Database
					opr.insertTShirt(tShirt);
				}
			}
			// Close CSVReader object
			csvReader.close();
		} catch (CsvException csv) {
			System.out.println(Constants.CSV_FILE_READ_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
