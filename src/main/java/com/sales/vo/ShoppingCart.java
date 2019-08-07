package com.sales.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sales.utils.TaxUtils;

public class ShoppingCart {

	private List<Product> productList;
	private BigDecimal salesTax = BigDecimal.ZERO;
	private BigDecimal totalPrice = BigDecimal.ZERO;

	public ShoppingCart() {
		productList = new ArrayList<Product>();
	}

	/**
	 * This returns the total sales tax applicable on cart
	 * 
	 * @return total sales tax applicable on cart
	 */
	public BigDecimal getSalesTax() {
		return salesTax;
	}

	/**
	 * This returns the total price of the products added in cart
	 * 
	 * @return the total price of the products added in cart
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	/**
	 * This method adds a given product to cart, while adding the product the sales
	 * tax and total price on cart is calculated and saved in respective fields.
	 * 
	 * @param product
	 *            the product that has to be added in cart
	 */
	public void addToCart(Product product) {
		if (product != null) {
			productList.add(product);
			salesTax = salesTax.add(product.getSalesTax().multiply(new BigDecimal(product.getQuantity())));
			totalPrice = totalPrice.add(product.getNetPrice().multiply(new BigDecimal(product.getQuantity())));
		}

	}

	/**
	 * This method returns the formatted cart information by iterating through each
	 * product and displaying the sales tax and total cost in required format
	 * 
	 * @return String
	 *            the formatted cart information
	 */
	public String printReceiptDetails() {
		StringBuilder receiptString = new StringBuilder();
		for (Product product : productList) {
			receiptString.append("\n").append(product.toString());
		}
		receiptString.append("\nSales Taxes: " + TaxUtils.formatAmount(salesTax));
		receiptString.append("\nTotal: " + TaxUtils.formatAmount(totalPrice));
		return receiptString.toString().trim();
	}

	/**
	 * This method returns the string representation of cart.
	 * 
	 * @return String
	 *            the string representation of cart.
	 */
	@Override
	public String toString() {
		StringBuilder cartString = new StringBuilder();
		for (Product product : productList) {
			cartString.append("\n").append(product.toString());
		}
		return cartString.toString().trim();
	}

	/**
	 * This method returns the size of cart, i.e. product elements present.
	 * 
	 * @return int
	 *            the size of cart
	 */
	public int getProductsInCartSize() {
		return productList.size();
	}

}