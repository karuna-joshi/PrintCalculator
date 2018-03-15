package com.papercut.printcalculator.job;

import java.io.Serializable;

public class PrintableJob implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int totalPagesCnt;
	private int colorPagesCnt;
	private boolean isDoubleSided;
	
	
	
	public int getTotalPagesCnt() {
		return totalPagesCnt;
	}
	public void setTotalPagesCnt(int totalPagesCnt) {
		this.totalPagesCnt = totalPagesCnt;
	}
	public int getColorPagesCnt() {
		return colorPagesCnt;
	}
	public void setColorPagesCnt(int colorPagesCnt) {
		this.colorPagesCnt = colorPagesCnt;
	}
	public boolean isDoubleSided() {
		return isDoubleSided;
	}
	public void setDoubleSided(boolean isDoubleSided) {
		this.isDoubleSided = isDoubleSided;
	}
	
	
}
