package com.sales.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sales.exception.InvalidAmountException;
import com.sales.exception.InvalidInputException;
import com.sales.exception.InvalidQuantityException;
import com.sales.utils.ProductBuilder;
import com.sales.vo.Product;
import com.sales.vo.ShoppingCart;

public class ShoppingApplication {
	final static Logger logger = Logger.getLogger(ShoppingApplication.class);

	public static void main(String[] args) throws InvalidInputException {
		logger.debug("inside ShoppingApplication main");
		ShoppingApplication app = new ShoppingApplication();
		String filePath = app.getPropertiesPath();
		app.processInputFileDirectory(filePath);
		logger.debug("exiting ShoppingApplication main");
	}

	public String getPropertiesPath() {
		String filePath = "";
		try (InputStream input = ShoppingApplication.class.getClassLoader()
				.getResourceAsStream("application.properties")) {
			Properties prop = new Properties();
			if (input != null) {
				prop.load(input);
				filePath = prop.getProperty("files.path");
			}
		} catch (IOException ioe) {
			logger.error("ShoppingApplication: Unable to load application properties file", ioe);
		}
		return filePath;
	}

	public void processInputFileDirectory(String inputFileDirectory) throws InvalidInputException {
		System.out.println("ABBB::"+inputFileDirectory);
		File fileLocation = new File(inputFileDirectory);
		if (fileLocation.exists()) {
			for (final File receiptFile : fileLocation.listFiles()) {
				if (receiptFile.isFile()) {
					try {
						processReceiptsForTax(receiptFile);
					} catch (InvalidAmountException iae) {
						logger.error("ShoppingApplication: Amount is invalid ", iae);
					} catch (InvalidQuantityException iqe) {
						logger.error("ShoppingApplication: Quantity is invalid ", iqe);
					} catch (InvalidInputException iie) {
						logger.error("ShoppingApplication: Receipt format is invalid ", iie);
					}
				}
			}
		} else {
			logger.error("ShoppingApplication main: Specified input file path is not valid");
			throw new InvalidInputException("Invalid Input file");
		}
	}

	public void processReceiptsForTax(File receiptFile)
			throws InvalidQuantityException, InvalidAmountException, InvalidInputException {
		if (receiptFile != null) {
			logger.debug(
					"inside ShoppingApplication processReceipts method,inputFile: " + receiptFile.getAbsolutePath());
			try (BufferedReader reader = new BufferedReader(new FileReader(receiptFile));) {
				String itemLine;
				Product product;
				ShoppingCart cart = new ShoppingCart();
				ProductBuilder builder = new ProductBuilder();
				while ((itemLine = reader.readLine()) != null) {
					product = builder.buildProduct(itemLine);
					cart.addToCart(product);
				}
				// Print the receipt in expected format
				System.out.println(cart.printReceiptDetails());
				logger.info(cart.printReceiptDetails());
			} catch (IOException ioe) {
				logger.error("ShoppingApplication: An IO exception has occured while reading receipt", ioe);
			}
		} else {
			logger.error("ShoppingApplication main: Specified input file path is not valid");
			throw new InvalidInputException("Invalid Input file");
		}
	}
}
