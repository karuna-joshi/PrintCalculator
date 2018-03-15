package com.papercut.printcalculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.papercut.printcalculator.job.A4PrintJob;
import com.papercut.printcalculator.job.PrintableJob;


/**
 * {@link PrintCalc} class unit tests, using a simple fixture.
 */
public class PrintCalcTest 
{
	/**
	 * Failing {@link PrintCalc#getTotalCost()} test, using null as parameter.
	 */
	@Test
	public void shouldNot_getTotalCost_forNullData() {
		try {
			PrintableJob jobs = null;
			A4PrintJob.parse(jobs);
			fail("Should not be null");
		} catch (RuntimeException e) {
			assertEquals("Should not be null", e.getMessage());
		}
	}

	/**
	 * {@link PrintCalc#getTotalCost()} test, using the problem sample data.
	 */
	@Test
	public void should_getTotalCost_fromEmptyJobList() 
	{
		BigDecimal totalPrintCost = new BigDecimal("0");
		List<PrintableJob> jobsc = new ArrayList<PrintableJob>();
		if(jobsc != null && jobsc.size() > 1) {
    		 Iterator<PrintableJob> printJob = jobsc.iterator();
    		 while(printJob.hasNext())
    		 {
    			 PrintableJob job = printJob.next();
    			 A4PrintJob a4Printjob = A4PrintJob.parse(job);
    			totalPrintCost = totalPrintCost.add(a4Printjob.getJobCost())  ;
    		 }     		 
    	 }
		assertTrue(totalPrintCost.compareTo(BigDecimal.ZERO) == 0);
	}
	
	
	private List<PrintableJob> loadJobs()
	{
		List<PrintableJob> printJobs = new ArrayList<PrintableJob>();
		
		PrintableJob job = new PrintableJob();
        job.setTotalPagesCnt(25);
        job.setColorPagesCnt(10);
        job.setDoubleSided(false);
        printJobs.add(job);
        job = null;
        
        job = new PrintableJob();
        job.setTotalPagesCnt(55);
        job.setColorPagesCnt(13);
        job.setDoubleSided(true);
        printJobs.add(job); job = null;        

        job = new PrintableJob();
        job.setTotalPagesCnt(502);
        job.setColorPagesCnt(22);
        job.setDoubleSided(true);
        printJobs.add(job); job = null;        

        job = new PrintableJob();
        job.setTotalPagesCnt(1);
        job.setColorPagesCnt(0);
        job.setDoubleSided(false);
        printJobs.add(job); job = null;
        
        return printJobs;
	}
	
	/**
	 * Successful {@link PrintCalc#getTotalCost()} test, using the problem sample data.
	 */
	@Test
	public void should_getTotalCost() {
		BigDecimal totalPrintCost = new BigDecimal("0");
		List<PrintableJob> printJobs = loadJobs();
        
        if(printJobs != null && printJobs.size() > 1) {
    		 Iterator<PrintableJob> printJob = printJobs.iterator();
    		 while(printJob.hasNext())
    		 {
    			 PrintableJob job = printJob.next();
    			 A4PrintJob a4Printjob = A4PrintJob.parse(job);
    			 totalPrintCost = totalPrintCost.add(a4Printjob.getJobCost())  ;
    		 }
        }
		assertTrue(totalPrintCost.compareTo(new BigDecimal("64.10")) == 0);
	}

	
}
