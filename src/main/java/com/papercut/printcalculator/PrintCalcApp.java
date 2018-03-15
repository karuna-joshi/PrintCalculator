package com.papercut.printcalculator;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.papercut.printcalculator.job.A4PrintJob;
import com.papercut.printcalculator.job.PrintJobParser;
import com.papercut.printcalculator.job.PrintableJob;

/**
 * Hello world!
 *
 */
public class PrintCalcApp 
{
    public static void main( String[] args )
    {        
    	BigDecimal totalPrintCost = new BigDecimal("0");
        // Parses CSV lines into a list of printable jobs
     	 List<PrintableJob> jobs = PrintJobParser.parseJobs();
     	 if(jobs != null && jobs.size() > 1) {
     		 Iterator<PrintableJob> printJob = jobs.iterator();
     		 while(printJob.hasNext())
     		 {
     			 PrintableJob job = printJob.next();
     			 A4PrintJob a4Printjob = A4PrintJob.parse(job);
     			 totalPrintCost = totalPrintCost.add(a4Printjob.getJobCost())  ;
     		 }     		 
     	 }

     	// Outputs job details and total cost     	
     	System.out.println(totalPrintCost);
     		
    }
}
