package com.papercut.printcalculator.job;

import java.util.List;

import com.papercut.printcalculator.util.CsvFileLoader;

public class PrintJobParser 
{
	private static final String csvFilePath = "sample.csv"; 
	/**
	 * Parses a PrintableJob from an array of Strings.
	 *  
	 * @return
	 * 
	 */
	public static List<PrintableJob> parseJobs() 
	{
		List<PrintableJob> jobs = new CsvFileLoader().parseCSVFile(csvFilePath);	
		return jobs;
	}

	
}
