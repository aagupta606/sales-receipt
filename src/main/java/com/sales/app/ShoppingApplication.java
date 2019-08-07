package com.sales.app;

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

/**
 * The ShoppingApplication class is main class which is used to execute the
 * application. This class is responsible for getting the input file location,
 * iterating through each file and generating receipt
 * 
 * @author Abhishek
 * @version 1.0
 * @since 2019-Aug-04
 */
public class ShoppingApplication {
	final static Logger logger = Logger.getLogger(ShoppingApplication.class);

	public static void main(String[] args) throws InvalidInputException {
		logger.debug("inside ShoppingApplication main");
		ShoppingApplication app = new ShoppingApplication();
		String filePath = app.getFilePathFromProperties();
		try {
			app.processInputFileDirectory(filePath);
		} catch (InvalidAmountException iae) {
			logger.error("ShoppingApplication: Amount is invalid ", iae);
		} catch (InvalidQuantityException iqe) {
			logger.error("ShoppingApplication: Quantity is invalid ", iqe);
		} catch (InvalidInputException iie) {
			logger.error("ShoppingApplication: Receipt format is invalid ", iie);
		}
		logger.debug("exiting ShoppingApplication main");
	}

	/**
	 * This method is used to find the input file location based on the
	 * application.properties file, we will use this location to iterate all the
	 * files available in that location
	 * 
	 * @return This returns File Path.
	 */
	public String getFilePathFromProperties() {
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

	/**
	 * This method is used to process the files, the inputFileDirectory will contain
	 * all the files and for each file we wll invoke
	 * <code> processReceiptsForTax </code>
	 *
	 * 
	 * @param inputFileDirectory
	 *            This parameter denotes the directory where input reciept file are
	 *            kept.
	 * @throws InvalidInputException
	 *             If the <code>inputFileDirectory</code> is not valid
	 */
	public void processInputFileDirectory(String inputFileDirectory) throws InvalidInputException {
		File fileLocation = new File(inputFileDirectory);
		if (fileLocation.exists()) {
			for (final File receiptFile : fileLocation.listFiles()) {
				if (receiptFile.isFile()) {
					processReceiptsForTax(receiptFile);
				}
			}
		} else {
			logger.error("ShoppingApplication main: Specified input file path is not valid");
			throw new InvalidInputException("Invalid Input file");
		}
	}

	/**
	 * This method takes individual files and processes line by line, building
	 * product by calling <code>builder.buildProduct</code>
	 * <code> processReceiptsForTax </code>
	 * 
	 * @param receiptFile
	 *            This parameter is the single input file.
	 * @throws InvalidQuantityException
	 *             If the <code>quantity</code> is less than zero
	 * @throws InvalidAmountException
	 *             If the <code>basePrice</code> is less than zero
	 * @throws InvalidInputException
	 *             If the <code>receiptFile</code> is not valid
	 */
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
