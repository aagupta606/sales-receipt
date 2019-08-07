package com.sales.vo;

public enum Category {

	FOOD(true, 0), MEDICINE(true, 0), BOOK(true, 0), OTHER(false, 10);

	private final boolean isExempted;
	private final int applicableTax;

	private Category(boolean isExempted, int applicableTax) {
		this.isExempted = isExempted;
		this.applicableTax = applicableTax;
	}

	public boolean isExempted() {
		return isExempted;
	}
	
	public int applicableTax()
	{
		return applicableTax;
	}
}
