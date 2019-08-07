package com.sales.test;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sales.exception.InvalidAmountException;
import com.sales.exception.InvalidInputException;
import com.sales.exception.InvalidQuantityException;
import com.sales.main.ShoppingApplication;
import static org.junit.Assert.assertEquals;

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
	public void testGetPropertiesPath() {
		String expectedValue = "C:\\Users\\Public\\inputs";
		ShoppingApplication app = new ShoppingApplication();
		assertEquals(expectedValue, app.getPropertiesPath());
	}

	@Test
	public void shouldThrowInvalidAmountException()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		exception.expect(InvalidAmountException.class);
		exception.expectMessage("Price is less than zero");
		ShoppingApplication app = new ShoppingApplication();
		app.processInputFiles(
				ShoppingApplicationTest.class.getClassLoader().getResource("invalidamount_testinput.txt").getFile());
	}

	@Test
	public void shouldThrowInvalidInputException()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		exception.expect(InvalidInputException.class);
		exception.expectMessage("Provided input is not valid");
		ShoppingApplication app = new ShoppingApplication();
		app.processInputFiles("invalidinput_testinput.txt");
	}

	@Test
	public void shouldThrowInvalidQuantityException()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		exception.expect(InvalidQuantityException.class);
		exception.expectMessage("Quantity is less than zero");
		ShoppingApplication app = new ShoppingApplication();
		app.processInputFiles("invalidquantity_testinput.txt");
	}

}
