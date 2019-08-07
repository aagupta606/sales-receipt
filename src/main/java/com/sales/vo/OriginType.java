package com.sales.vo;

public enum OriginType {

	IMPORTED(true,5), DOMESTIC(true,0);

	private final boolean isImported;
	private final int applicableTax;

	private OriginType(boolean isImported, int applicableTax) {
		this.isImported = isImported;
		this.applicableTax = applicableTax;
	}

	public boolean isImported() {
		return isImported;
	}
	
	public int applicableTax()
	{
		return applicableTax;
	}
}
