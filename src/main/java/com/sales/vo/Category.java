package com.sales.vo;

/**
 * The Category denotes whether its Food, Medicine, Book or Other Product
 * 
 * @author Abhishek
 * @version 1.0
 * @since 2019-Aug-05
 */
public enum Category {

	FOOD(true, 0), MEDICINE(true, 0), BOOK(true, 0), OTHER(false, 10);

	private final boolean isExempted;
	private final int applicableTax;

	private Category(boolean isExempted, int applicableTax) {
		this.isExempted = isExempted;
		this.applicableTax = applicableTax;
	}

	/**
	 * This returns whether the product is exempted from tax or not
	 * 
	 * @return if exempted from tax
	 */
	public boolean isExempted() {
		return isExempted;
	}

	/**
	 * This returns the tax applicable for this product
	 * 
	 * @return the tax applicable on this product
	 */
	public int applicableTax() {
		return applicableTax;
	}
}
