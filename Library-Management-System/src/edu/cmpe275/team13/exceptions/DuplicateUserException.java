package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The exception occurs when user tries to sign up with already existing ID.
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already exists!")
public class DuplicateUserException extends RuntimeException{

	private static final long serialVersionUID = 5061703621467706101L;

	public DuplicateUserException() {
		super();
	}
	
	public DuplicateUserException(String messsge) {
		super(messsge);
	}
}
