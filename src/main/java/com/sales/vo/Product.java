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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public OriginType getOriginType() {
		return originType;
	}

	public void setOriginType(OriginType originType) {
		this.originType = originType;
	}

	public BigDecimal getNetPrice() {
		return getBasePrice().add(getSalesTax());
	}

	public BigDecimal getSalesTax() {
		return (TaxUtils.calculateApplicableTax(getBasePrice(), new BigDecimal(getCategory().applicableTax()),
				new BigDecimal(getOriginType().applicableTax())));
	}

	@Override
	public String toString() {
		return quantity + " " + name + ": " + TaxUtils.formatAmount(getNetPrice());
	}

}
