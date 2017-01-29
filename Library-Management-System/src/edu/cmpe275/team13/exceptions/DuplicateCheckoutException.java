package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The exception occurs when user tries to checkout same book again!
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Same book can not be checked out again!!")
public class DuplicateCheckoutException extends RuntimeException{

	private static final long serialVersionUID = 5061343621467706101L;

	public DuplicateCheckoutException() {
		super();
	}
	
	public DuplicateCheckoutException(String messsge) {
		super(messsge);
	}
}
