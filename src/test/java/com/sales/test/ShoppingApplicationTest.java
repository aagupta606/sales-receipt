package com.sales.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sales.app.ShoppingApplication;
import com.sales.exception.InvalidAmountException;
import com.sales.exception.InvalidInputException;
import com.sales.exception.InvalidQuantityException;

/**
 * This is a test class to unit test the ShoppingApplication functionality
 *
 * @see com.sales.app.ShoppingApplication
 * @author Abhishek
 * @since 2019-Aug-07
 */
public class ShoppingApplicationTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testProcessReceiptsForTaxWhenNullFile()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		ShoppingApplication app = new ShoppingApplication();
		exception.expect(InvalidInputException.class);
		exception.expectMessage("Invalid Input file");
		app.processReceiptsForTax(null);
	}

	@Test
	public void testProcessReceiptsForTaxWhenNotNullFile()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		ShoppingApplication app = new ShoppingApplication();
		app.processReceiptsForTax(
				new File(ShoppingApplicationTest.class.getClassLoader().getResource("testinput.txt").getFile()));
	}

	@Test
	public void testMain() throws InvalidInputException {
		String[] args = null;
		ShoppingApplication.main(args);
	}

	@Test
	public void testGetFilePathFromProperties() {
		String expectedValue = "C:\\Users\\Public\\inputs";
		ShoppingApplication app = new ShoppingApplication();
		assertEquals(expectedValue, app.getFilePathFromProperties());
	}

}
