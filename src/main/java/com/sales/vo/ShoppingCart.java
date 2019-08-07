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

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void addToCart(Product product) {
		if (product != null) {
			productList.add(product);
			salesTax = salesTax.add(product.getSalesTax().multiply(new BigDecimal(product.getQuantity())));
			totalPrice = totalPrice.add(product.getNetPrice().multiply(new BigDecimal(product.getQuantity())));
		}

	}

	public String printReceiptDetails() {
		StringBuilder receiptString = new StringBuilder();
		for (Product product : productList) {
			receiptString.append("\n").append(product.toString());
		}
		receiptString.append("\nSales Taxes: " + TaxUtils.formatAmount(salesTax));
		receiptString.append("\nTotal: " + TaxUtils.formatAmount(totalPrice));
		return receiptString.toString().trim();
	}

	public String toString() {
		StringBuilder cartString = new StringBuilder();
		for (Product product : productList) {
			cartString.append("\n").append(product.toString());
		}
		return cartString.toString().trim();
	}

	public int getProductsInCartSize() {
		return productList.size();
	}

}