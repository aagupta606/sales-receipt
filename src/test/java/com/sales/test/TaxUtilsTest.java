package com.sales.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.sales.utils.TaxUtils;

public class TaxUtilsTest {

	@Test
	public void testRoundOffTax() {
		BigDecimal expectedValue = new BigDecimal("1.90");
		assertEquals(expectedValue, TaxUtils.roundOffTax(new BigDecimal("1.899")));
	}

	@Test
	public void testFormatAmount() {
		String expectedValue = "16.50";
		assertEquals(expectedValue, TaxUtils.formatAmount(new BigDecimal("16.500")));
	}

	@Test
	public void testCalculateApplicableTax() {
		BigDecimal expectedValue = new BigDecimal("1.90");
		assertEquals(expectedValue, TaxUtils.calculateApplicableTax(new BigDecimal("18.99"), new BigDecimal("10"), new BigDecimal("0")));
	}

}
