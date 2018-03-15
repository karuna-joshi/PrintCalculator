package com.papercut.printcalculator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.papercut.printcalculator.job.PrintableJob;

/**
 * 
 * This class loads a CSV input file.
 * This can be improved by using an external library such as Apache * 
 * Commons CSV.
 */
public class CsvFileLoader 
{
	private static final String cvsSplitBy = ",";
   
	private List<PrintableJob> printJobs = null;
	

	public List<PrintableJob> parseCSVFile(String fileName) 
	{	
		printJobs = new ArrayList<PrintableJob>();
		BufferedReader br = null;
		try 
		{			
			if(fileName != null) {
				ClassLoader classLoader = getClass().getClassLoader();
				File file = new File(classLoader.getResource(fileName).getFile());
				
				int index = 1;
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) 
				{
					String line = scanner.nextLine();				
					String[] jobDtlLines = line.split(cvsSplitBy);
		            System.out.println("PrintJobs [totalPages= " + jobDtlLines[0] + " , colorpg cnt=" + jobDtlLines[1] + ", double sided cnt=" + jobDtlLines[2]+"]");
		                
		            if(index != 1 ) 
		            {
			            PrintableJob job = new PrintableJob();
			            job.setTotalPagesCnt(Integer.parseInt(jobDtlLines[0].trim()));
			            job.setColorPagesCnt(Integer.parseInt(jobDtlLines[1].trim()));
			            job.setDoubleSided(Boolean.parseBoolean(jobDtlLines[2].trim()));
			            printJobs.add(job);
			            job = null;
		            }
		            index++;
				}
				scanner.close();
			}
			else {
				System.out.println("File Name cannot be empty");
			}
        } 
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		catch (Exception e) {
            e.printStackTrace();
        } 
		finally
		{
            if (br != null) 
            {
                try 
                {
                    br.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return printJobs;

			
	}

}
