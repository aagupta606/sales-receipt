package com.sales.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;

import org.apache.log4j.Logger;

/**
 * The TaxUtils class provides Utility function for formatting and rounding the
 * data.
 * 
 * @author Abhishek
 * @version 1.0
 * @since 2019-Aug-06
 */
public class TaxUtils {

	private static final Format decimalFormat = new DecimalFormat("0.00");
	private static final BigDecimal roundingValue = new BigDecimal("0.05");
	final static Logger logger = Logger.getLogger(TaxUtils.class);

	/**
	 * This method is used to round off the BigDecimal tax to be used for display in
	 * receipt.
	 * 
	 * @param tax
	 *            This parameter is BigDecimal which needs to be rounded to 0.05
	 *            np/100 rounded up to the nearest 0.05
	 * @return rounded off value
	 */
	public static BigDecimal roundOffTax(BigDecimal tax) {
		logger.debug("inside TaxUtils roundOffTax method");

		BigDecimal divided = tax.divide(roundingValue, 0, RoundingMode.UP);
		BigDecimal result = divided.multiply(roundingValue);
		return result;
	}

	/**
	 * This method is used to format the BigDecimal amount to be used for display in
	 * receipt in 2 decimal places.
	 * 
	 * @param amount
	 *            This parameter is BigDecimal which needs to be formatted to 2
	 *            decimal places
	 * @return formatted off value
	 */
	public static String formatAmount(BigDecimal amount) {
		logger.debug("inside TaxUtils formatAmount method");
		return decimalFormat.format(amount);
	}

	/**
	 * This method is used to calculate applicable tax. the BigDecimal tax is the
	 * combination of category and Origin type tax. The tax percentage is then
	 * multiplied with base price to get the applicable tax .
	 * 
	 * @param basePrice
	 *            This parameter is base price of product
	 * @param categoryTax
	 *            This parameter is category tax on account of Food, Medicine, Book,
	 *            Other
	 * @param importedTax
	 *            This parameter is Origin type on account of Imported or Domestic
	 * @return Calculated applicable tax
	 */
	public static BigDecimal calculateApplicableTax(BigDecimal basePrice, BigDecimal categoryTax,
			BigDecimal importedTax) {
		logger.debug("inside TaxUtils calculateApplicableTax method");
		return TaxUtils.roundOffTax(basePrice.multiply(categoryTax.add(importedTax).divide(new BigDecimal("100.0"))));
	}

}
