package com.sales.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;

import org.apache.log4j.Logger;

public class TaxUtils {

	private static final Format decimalFormat = new DecimalFormat("0.00");
	private static final BigDecimal roundingValue = new BigDecimal("0.05");
	final static Logger logger = Logger.getLogger(TaxUtils.class);

	public static BigDecimal roundOffTax(BigDecimal tax) {
		logger.debug("inside TaxUtils roundOffTax method");
		
		BigDecimal divided = tax.divide(roundingValue, 0, RoundingMode.UP);
		BigDecimal result = divided.multiply(roundingValue);
		return result;
	}

	public static String formatAmount(BigDecimal amount) {
		logger.debug("inside TaxUtils formatAmount method");
		return decimalFormat.format(amount);
	}

	public static BigDecimal calculateApplicableTax(BigDecimal basePrice, BigDecimal categoryTax,
			BigDecimal importedTax) {
		logger.debug("inside TaxUtils calculateApplicableTax method");
		return TaxUtils.roundOffTax(basePrice.multiply(categoryTax.add(importedTax).divide(new BigDecimal("100.0"))));
	}

}
