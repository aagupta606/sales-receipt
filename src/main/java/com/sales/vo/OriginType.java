package com.sales.vo;

/**
 * The OriginType is used to represent whether the product is IMPORTED or
 * DOMESTIC PRODUCT
 * 
 * @author Abhishek
 * @version 1.0
 * @since 2019-Aug-06
 */
public enum OriginType {

	IMPORTED(true, 5), DOMESTIC(true, 0);

	private final boolean isImported;
	private final int applicableTax;

	private OriginType(boolean isImported, int applicableTax) {
		this.isImported = isImported;
		this.applicableTax = applicableTax;
	}

	/**
	 * This returns whether the product is imported or domestic
	 * 
	 * @return imported product
	 */
	public boolean isImported() {
		return isImported;
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
