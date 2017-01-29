package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Librarian not found")
public class LibrarianNotFoundException extends RuntimeException {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 123467L;

	public LibrarianNotFoundException() {
		super();
	}
	
	public LibrarianNotFoundException(String message) {
		super(message);
	}
}
