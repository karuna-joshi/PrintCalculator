package com.papercut.printcalculator;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.papercut.printcalculator.job.PrintableJob;
import com.papercut.printcalculator.util.CsvFileLoader;


/**
 * {@link A4PrintJob} class unit tests, using a simple fixture.
 */
public class CsvFileLoaderTest
{
	@Test
	public void shouldNot_loadLines_fromNullFile() {
		try {
			new CsvFileLoader().parseCSVFile(null);
			fail("Should fail on null filename");
		} catch (RuntimeException e) {
			assertEquals("Invalid file name.", e.getMessage());
		}
	}

	@Test
	public void shouldNot_loadLines_fromInvalidFile() {
		try {
			new CsvFileLoader().parseCSVFile("");
			fail("Should fail on null filename");
		} catch (RuntimeException e) {
			assertEquals("Invalid file name.", e.getMessage());
		}
	}

	@Test
	public void shouldNotFail_loadingLines_fromEmptyFile() {
		List<PrintableJob> lines = new CsvFileLoader().parseCSVFile("empty.csv");
		assertEquals(0, lines.size());
	}

}
