package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Transaction limit exceeded!")
public class TransactionLimitExceededException extends RuntimeException {

	private static final long serialVersionUID = -2651790345293999095L;
	
	public TransactionLimitExceededException() {
		super();
	}
	
	public TransactionLimitExceededException(String message) {
		super(message);
	}
}
