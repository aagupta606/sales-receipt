package com.sales.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sales.exception.InvalidAmountException;
import com.sales.exception.InvalidInputException;
import com.sales.exception.InvalidQuantityException;
import com.sales.utils.ProductBuilder;
import com.sales.vo.Category;
import com.sales.vo.OriginType;

public class ProductBuilderTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void shouldThrowInvalidAmountException()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		String inputLine = "1 book at -12.49";
		exception.expect(InvalidAmountException.class);
		exception.expectMessage("Price is less than zero");
		ProductBuilder prodBuilder = new ProductBuilder();
		prodBuilder.buildProduct(inputLine);
	}

	@Test
	public void shouldThrowInvalidInputException()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		String inputLine = "1 book atSS 12.49";
		exception.expect(InvalidInputException.class);
		exception.expectMessage("Provided input is not valid");
		ProductBuilder prodBuilder = new ProductBuilder();
		prodBuilder.buildProduct(inputLine);
	}

	@Test
	public void shouldThrowInvalidQuantityException()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		String inputLine = "-1 book at -12.49";
		exception.expect(InvalidQuantityException.class);
		exception.expectMessage("Quantity is less than zero");
		ProductBuilder prodBuilder = new ProductBuilder();
		prodBuilder.buildProduct(inputLine);
	}

	@Test
	public void shouldReturnImportedOriginType() {
		String inputLine = "imported bottle of perfume";
		ProductBuilder prodBuilder = new ProductBuilder();
		assertEquals(OriginType.IMPORTED, prodBuilder.findOriginType(inputLine));
	}

	@Test
	public void shouldReturnDomesticOriginType() {
		String inputLine = "important bottle of perfume";
		ProductBuilder prodBuilder = new ProductBuilder();
		assertEquals(OriginType.DOMESTIC, prodBuilder.findOriginType(inputLine));
	}

	@Test
	public void shouldReturnFoodCategory() {
		String inputLine = "box of imported chocolates";
		ProductBuilder prodBuilder = new ProductBuilder();
		assertEquals(Category.FOOD, prodBuilder.findCategory(inputLine));
	}

	@Test
	public void shouldReturnMedicineCategory() {
		String inputLine = "packet of headache pills";
		ProductBuilder prodBuilder = new ProductBuilder();
		assertEquals(Category.MEDICINE, prodBuilder.findCategory(inputLine));
	}

	@Test
	public void shouldReturnBookCategory() {
		String inputLine = "book";
		ProductBuilder prodBuilder = new ProductBuilder();
		assertEquals(Category.BOOK, prodBuilder.findCategory(inputLine));
	}

	@Test
	public void shouldReturnOtherCategory() {
		String inputLine = "important bottle of perfume";
		ProductBuilder prodBuilder = new ProductBuilder();
		assertEquals(Category.OTHER, prodBuilder.findCategory(inputLine));
	}

	@Test
	public void shouldNotReturnImportedOriginTypeWhenUpperCase() {
		String inputLine = "IMPORTED BOTTLE OF PERFUME";
		ProductBuilder prodBuilder = new ProductBuilder();
		assertNotEquals(OriginType.IMPORTED, prodBuilder.findOriginType(inputLine));
	}

	@Test
	public void shouldNotReturnMedicineCategoryWhenUpperCase() {
		String inputLine = "PACKET OF HEADACHE PILLS";
		ProductBuilder prodBuilder = new ProductBuilder();
		assertNotEquals(Category.MEDICINE, prodBuilder.findCategory(inputLine));
	}

}