package com.sales.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.sales.exception.InvalidAmountException;
import com.sales.exception.InvalidInputException;
import com.sales.exception.InvalidQuantityException;
import com.sales.utils.ProductBuilder;
import com.sales.vo.Product;
import com.sales.vo.ShoppingCart;

/**
 * This is a test class to unit test the ShoppingCart functionality
 *
 * @see com.sales.vo.ShoppingCart
 * @author Abhishek
 * @since 2019-Aug-07
 */
public class ShoppingCartTest {

	@Test
	public void testPrintReceiptDetailsInputCase1()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		ShoppingCart cart = new ShoppingCart();
		ProductBuilder prodBuilder = new ProductBuilder();
		Product product = prodBuilder.buildProduct("1 book at 12.49");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 music CD at 14.99");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 chocolate bar at 0.85");
		cart.addToCart(product);
		// Problem statement has extra space after book in output, inconsistent with
		// other output
		String expOutput = "1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83";
		assertEquals(expOutput, cart.printReceiptDetails());
	}

	@Test
	public void testPrintReceiptDetailsInputCase2()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		ShoppingCart cart = new ShoppingCart();
		ProductBuilder prodBuilder = new ProductBuilder();
		Product product = prodBuilder.buildProduct("1 imported box of chocolates at 10.00");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 imported bottle of perfume at 47.50");
		cart.addToCart(product);
		String expOutput = "1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15";
		assertEquals(expOutput, cart.printReceiptDetails());
	}

	@Test
	public void testPrintReceiptDetailsInputCase3()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		ShoppingCart cart = new ShoppingCart();
		ProductBuilder prodBuilder = new ProductBuilder();
		Product product = prodBuilder.buildProduct("1 imported bottle of perfume at 27.99");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 bottle of perfume at 18.99");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 packet of headache pills at 9.75");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 box of imported chocolates at 11.25");
		cart.addToCart(product);
		String expOutput = "1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 imported box of chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68";
		assertEquals(expOutput, cart.printReceiptDetails());
	}

	@Test
	public void testPrintReceiptDetailsInputCase3WhenMultipleImported()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		ShoppingCart cart = new ShoppingCart();
		ProductBuilder prodBuilder = new ProductBuilder();
		Product product = prodBuilder.buildProduct("1 imported bottle of perfume at 27.99");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 bottle of perfume at 18.99");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 packet of headache pills at 9.75");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 box of imported superimported chocolates at 11.25");
		cart.addToCart(product);
		String expOutput = "1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 imported box of superimported chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68";
		assertEquals(expOutput, cart.printReceiptDetails());
	}

	@Test
	public void testGetSalesTax() throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		BigDecimal expectedValue = new BigDecimal("7.65");
		ShoppingCart cart = new ShoppingCart();
		ProductBuilder prodBuilder = new ProductBuilder();
		Product product = prodBuilder.buildProduct("1 imported box of chocolates at 10.00");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 imported bottle of perfume at 47.50");
		cart.addToCart(product);
		assertEquals(expectedValue, cart.getSalesTax());
	}

	@Test
	public void testGetTotalPrice() throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		BigDecimal expectedValue = new BigDecimal("65.15");
		ShoppingCart cart = new ShoppingCart();
		ProductBuilder prodBuilder = new ProductBuilder();
		Product product = prodBuilder.buildProduct("1 imported box of chocolates at 10.00");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 imported bottle of perfume at 47.50");
		cart.addToCart(product);
		assertEquals(expectedValue, cart.getTotalPrice());
	}

	@Test
	public void testToStringWhenCartIsEmpty()
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		String expectedValue = "";
		ShoppingCart cart = new ShoppingCart();
		assertEquals(expectedValue, cart.toString());
	}

	@Test
	public void testToString() throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		String expectedValue = "1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65";
		ShoppingCart cart = new ShoppingCart();
		ProductBuilder prodBuilder = new ProductBuilder();
		Product product = prodBuilder.buildProduct("1 imported box of chocolates at 10.00");
		cart.addToCart(product);
		product = prodBuilder.buildProduct("1 imported bottle of perfume at 47.50");
		cart.addToCart(product);
		assertEquals(expectedValue, cart.toString());
	}

	@Test
	public void testAddToCartNull() throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		int expectedValue = 1;
		ShoppingCart cart = new ShoppingCart();
		ProductBuilder prodBuilder = new ProductBuilder();
		Product product = prodBuilder.buildProduct("1 imported box of chocolates at 10.00");
		cart.addToCart(product);
		cart.addToCart(null);
		assertEquals(expectedValue, cart.getProductsInCartSize());
	}

}
