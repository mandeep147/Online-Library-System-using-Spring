package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The exception occurs when user tries to add same book again.
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Book already exists!")
public class DuplicateBookException extends RuntimeException{

	private static final long serialVersionUID = 5022703621467706101L;

	public DuplicateBookException() {
		super();
	}
	
	public DuplicateBookException(String messsge) {
		super(messsge);
	}
}
