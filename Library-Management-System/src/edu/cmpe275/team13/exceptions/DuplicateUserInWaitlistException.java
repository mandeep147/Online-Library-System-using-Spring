package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The exception occurs when user tries to join a waitlist for same book again.
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already present in waitlist!")
public class DuplicateUserInWaitlistException extends RuntimeException{

	private static final long serialVersionUID = 5061703621467746154L;

	public DuplicateUserInWaitlistException() {
		super();
	}
	
	public DuplicateUserInWaitlistException(String messsge) {
		super(messsge);
	}
}
