package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when the Book with given ISBN is not found in the system.
 * @author pratiksanglikar
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Such Book")
public class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4612760197570522976L;

	/**
	 * 
	 */
	public BookNotFoundException() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public BookNotFoundException(String message) {
		super(message);
	}
}
