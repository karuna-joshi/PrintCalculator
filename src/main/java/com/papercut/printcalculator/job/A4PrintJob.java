package com.papercut.printcalculator.job;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class A4PrintJob implements Serializable
{

	private static final long serialVersionUID = -1546741420084852717L;

	/**
	 * Number of black and white pages to be printed.
	 */
	private int bwPages;

	/**
	 * Number of colour pages to be printed.
	 */
	private int colourPages;

	/**
	 * Whether to print both sides of a sheet or just one.
	 */
	private boolean doubleSided;

	/**
	 * Cost configuration for A4 paper sizes.
	 */
	private A4PrintJobCostConf costConf;

	public A4PrintJob(int bwPages, int colourPages) {
		this(bwPages, colourPages, false);
	}

	public A4PrintJob(int bwPages, int colourPages, boolean doubleSided) {
		super();
		this.bwPages = bwPages;
		this.colourPages = colourPages;
		this.setDoubleSided(doubleSided);
		this.setCostConf(new A4PrintJobCostConf()); // use default costs.
	}

	

	public int getBwPages() {
		return bwPages;
	}

	public void setBwPages(int bwPages) {
		this.bwPages = bwPages;
	}

	public int getColourPages() {
		return colourPages;
	}

	public void setColourPages(int colourPages) {
		this.colourPages = colourPages;
	}

	public boolean isDoubleSided() {
		return doubleSided;
	}

	public void setDoubleSided(boolean doubleSided) {
		this.doubleSided = doubleSided;
	}

	public A4PrintJobCostConf getCostConf() {
		return costConf;
	}

	public void setCostConf(A4PrintJobCostConf costConf) {
		this.costConf = costConf;
	}

	public String toString() {
		return "Job type: Paper size A4 " + (doubleSided? "double sided" : "single sided") +
				"; Black & White pages: " + bwPages +
				"; Colour pages: " + colourPages +
				"; Total cost: " + NumberFormat.getCurrencyInstance().format(getJobCost());
	}

	public String getDetails() {
		return toString();
	}

	/**
	 * 
	 * @param jobs
	 * @return
	 */
	public static A4PrintJob parse(PrintableJob job) {
		if (job == null )
			throw new RuntimeException("Invalid number of parameters for an A4 print job. Should be exactly 3.");

		int totalPages, colourPages;
		boolean doubleSided;

		// Check for invalid totalPages parameter:
		try 
		{
			totalPages = job.getTotalPagesCnt();
		} 
		catch (NumberFormatException e) {
			throw new RuntimeException("Expected total number of pages, got: " + job.getTotalPagesCnt());
		}

		// Check that totalPages is a positive integer:
		if (totalPages < 0)
			throw new RuntimeException(
					"Total number of pages must be a positive number, got: " + totalPages);

		// Check for invalid colourPages parameter:
		try {
			colourPages = job.getColorPagesCnt();
		} 
		catch (NumberFormatException e) {
			throw new RuntimeException(
					"Expected number of colour pages, got: " + job.getColorPagesCnt());
		}

		// Check that colourPages is a positive integer:
		if (colourPages < 0)
			throw new RuntimeException(
					"Number of colour pages must be a positive number, got: " + colourPages);

		// Check that colourPages <= totalPages:
		if (colourPages > totalPages)
			throw new RuntimeException(
					"Number of colour pages must not exceed total pages");

		// check for invalid double-sided parameter:		
		doubleSided = job.isDoubleSided();

		return new A4PrintJob(totalPages - colourPages, colourPages, doubleSided);
	}
	
	/**
	 * 
	 * @return
	 */
	public BigDecimal getJobCost() {
		BigDecimal cost = BigDecimal.ZERO;

		if (!doubleSided) {
			cost = cost.add(getCostConf().getBw1SidedPageCost().multiply(
					BigDecimal.valueOf(bwPages)));
			cost = cost.add(getCostConf().getColour1SidedPageCost().multiply(
					BigDecimal.valueOf(colourPages)));
		}
		else {
			cost = cost.add(getCostConf().getBw2SidedPageCost().multiply(
					BigDecimal.valueOf(bwPages)));
			cost = cost.add(getCostConf().getColour2SidedPageCost().multiply(
					BigDecimal.valueOf(colourPages)));
		}

		return cost;
	}

}
