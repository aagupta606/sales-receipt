package com.sales.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.sales.exception.InvalidAmountException;
import com.sales.exception.InvalidInputException;
import com.sales.exception.InvalidQuantityException;
import com.sales.vo.Category;
import com.sales.vo.OriginType;
import com.sales.vo.Product;

/**
 * The ProductBuilder class is responsible for forming the product object, eaxh
 * line contains the product data in file and the regex will retrieve quantity,
 * name, price from input file. Also, based on the name of product, the Origin
 * and Category is determined.
 * 
 * @author Abhishek
 * @version 1.0
 * @since 2019-Aug-04
 */
public class ProductBuilder {
	private static final String INPUT_REGEX = "^(-?\\d+) ([\\w\\s]*) at (-?\\d+.\\d{2})";

	final static Logger logger = Logger.getLogger(ProductBuilder.class);

	/**
	 * This method is used to build the Product Object. The quantity, name and price
	 * values are extracted based on regex match and the Imported type and Product
	 * category is calculated and set.
	 * 
	 * @param productString
	 *            This parameter is passed in form of '1 book at 12.49'
	 * @return This returns well formed Product based on input.
	 * @throws InvalidQuantityException
	 *             If the <code>quantity</code> is less than zero
	 * @throws InvalidAmountException
	 *             If the <code>basePrice</code> is less than zero
	 * @throws InvalidInputException
	 *             If the <code>receiptFile</code> is not valid
	 */
	public Product buildProduct(String productString)
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		logger.debug("inside ProductBuilder buildProduct method");
		String name;
		int quantity;
		BigDecimal basePrice;
		Pattern pattern = Pattern.compile(INPUT_REGEX);
		Matcher matcher = pattern.matcher(productString);
		matcher.find();
		try {
			quantity = Integer.parseInt(matcher.group(1));
			name = matcher.group(2);
			basePrice = new BigDecimal(matcher.group(3));
		} catch (IllegalStateException ise) {
			throw new InvalidInputException("Provided input is not valid");
		}
		if (quantity < 0) {
			throw new InvalidQuantityException("Quantity is less than zero");
		}
		if (basePrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new InvalidAmountException("Price is less than zero");
		}
		Category category = findCategory(name);
		OriginType type = findOriginType(name);
		// handle the jumbled imported in input (only one occurence as we are
		// discounting input such as 1 box of imported imported chocolates at 11.25) and
		// bring to the first position
		if (type == OriginType.IMPORTED && !name.startsWith("imported")) {
			name = "imported " + name.replaceFirst("imported ", "");
		}
		Product product = new Product(quantity, name, basePrice, category, type);
		logger.debug("Product :" + product);
		return product;
		// return Optional.of(new Product(quantity, name, basePrice, category, type));
		// return Optional.empty();
	}

	/**
	 * This method is used to find the Origin Type of product. The Origin can either
	 * be DOMESTIC or IMPORTED, based on the name field containing imported word in
	 * it. Cannot Convert to lower case explicitly to handle the boundary case that
	 * application receives product name in upper case for IMPORTED or PILL because
	 * doing so will break test case e.g. CD is received in upper case
	 * 
	 * @param name
	 *            This parameter is passed in form of 'imported book'
	 * @return This returns OriginType [IMPORTED|DOMESTIC].
	 */
	public OriginType findOriginType(String name) {
		logger.debug("inside ProductBuilder findOriginType method");
		Pattern pattern = Pattern.compile("imported");
		Matcher m = pattern.matcher(name);
		if (m.find())
			return OriginType.IMPORTED;
		return OriginType.DOMESTIC;

	}

	/**
	 * This method is used to find the category of product. The Category can be enum
	 * values of FOOD, MEDICINE, BOOK or OTHER, based on the name field containing
	 * chocolate, pill, book respectively it. Cannot Convert to lower case
	 * explicitly to handle the boundary case that application receives product name
	 * in upper case for IMPORTED or PILL because doing so will break test case e.g.
	 * CD is received in upper case
	 * 
	 * @param name
	 *            This parameter is passed in form of 'imported book'
	 * @return This returns OriginType [FOOD|MEDICINE|BOOK|OTHER].
	 */
	public Category findCategory(String name) {
		logger.debug("inside ProductBuilder findCategory method");
		Pattern pattern = Pattern.compile("chocolate");
		Matcher m = pattern.matcher(name);
		if (m.find())
			return Category.FOOD;

		pattern = Pattern.compile("pill");
		m = pattern.matcher(name);
		if (m.find())
			return Category.MEDICINE;

		pattern = Pattern.compile("book");
		m = pattern.matcher(name);
		if (m.find())
			return Category.BOOK;

		// music CD | bottle of perfume |
		return Category.OTHER;
	}

}