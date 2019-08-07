package com.sales.vo;

import java.math.BigDecimal;

import com.sales.utils.TaxUtils;

/**
 * This is a model class to hold Product information
 *
 * @see java.lang.Object
 * @author abhishek
 */
public class Product {
	private int quantity;
	private String name;
	private BigDecimal basePrice;

	private Category category;
	private OriginType originType;

	/**
	 * Product constructor
	 * 
	 * @param quantity
	 *            is a positive integer value
	 * @param name
	 *            is a String value
	 * @param basePrice
	 *            is a decimal value
	 * @param category
	 *            is a enum value with choice of [FOOD|MEDICINE|BOOK|OTHER]
	 * @param originType
	 *            is a enum value with choice of [IMPORTED|DOMESTIC]
	 */

	public Product(int quantity, String name, BigDecimal basePrice, Category category, OriginType originType) {
		super();
		this.quantity = quantity;
		this.name = name;
		this.basePrice = basePrice;
		this.category = category;
		this.originType = originType;
	}

	/**
	 * This returns the quantity of this product
	 * 
	 * @return the quantity of this product
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * This returns the quantity of this product
	 * 
	 * @param quantity
	 *            the quantity of this product
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * This returns the name of this product
	 * 
	 * @return String the name of this product
	 */
	public String getName() {
		return name;
	}

	/**
	 * This sets the name of this product
	 * 
	 * @param name
	 *            the name of this product
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This returns the base price of this product
	 * 
	 * @return BigDecimal the base price of this product
	 */
	public BigDecimal getBasePrice() {
		return basePrice;
	}

	/**
	 * This sets the base price of this product
	 * 
	 * @param basePrice
	 *            sets the base price of this product
	 */
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * This returns the Category of this product
	 * 
	 * @return Category the Category of this product FOOD, MEDICINE, BOOK, OTHER
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * This sets the Category of this product
	 * 
	 * @param category
	 *            sets the Category of this product
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * This returns the origin type of this product
	 * 
	 * @return OriginType the origin type of this product IMPORTED or DOMESTIC
	 */
	public OriginType getOriginType() {
		return originType;
	}

	/**
	 * This sets the origin type of this product
	 * 
	 * @param originType
	 *            the origin type of this product
	 */
	public void setOriginType(OriginType originType) {
		this.originType = originType;
	}

	/**
	 * This returns the Net price of this product
	 * 
	 * @return int the net price of this product, sum of base price and applicable
	 *         sales tax
	 */
	public BigDecimal getNetPrice() {
		return getBasePrice().add(getSalesTax());
	}

	/**
	 * This returns the Sales tax of this product
	 * 
	 * @return BigDecimal the calculated value of sales tax of this product
	 */
	public BigDecimal getSalesTax() {
		return (TaxUtils.calculateApplicableTax(getBasePrice(), new BigDecimal(getCategory().applicableTax()),
				new BigDecimal(getOriginType().applicableTax())));
	}

	/**
	 * This returns the String representation of product object
	 * 
	 * @return String the representation of product object
	 */
	@Override
	public String toString() {
		return quantity + " " + name + ": " + TaxUtils.formatAmount(getNetPrice());
	}

}
