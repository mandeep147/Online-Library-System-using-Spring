package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Patron not found!")
public class PatronNotFoundException extends RuntimeException {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 123467L;

	public PatronNotFoundException() {
		super();
	}
	
	public PatronNotFoundException(String message) {
		super(message);
	}
}