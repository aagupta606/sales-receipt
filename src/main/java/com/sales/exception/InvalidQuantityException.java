package com.sales.exception;

/**
 * The InvalidQuantityException class is used when the quantity entered in the input
 * file is less than zero, In case its not a number the same will be skipped by
 * regex and handled with InvalidInputException.
 * @author Abhishek
 * @version 1.0
 * @since 2019-Aug-04
 */
public class InvalidQuantityException extends InvalidInputException {

	public InvalidQuantityException(String message) {
		super(message);
	}

}
