package com.sales.exception;

/**
 * The InvalidInputException class is used when the input entered in the 
 * file is not formatted correctly and associated problem encountered in Input file
 * @author Abhishek
 * @version 1.0
 * @since 2019-Aug-04
 */
public class InvalidInputException extends Exception {

	public InvalidInputException(String message) {
		super(message);
	}

}
