package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The exception occurs when user tries to reserve same book again.
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "This book is already reserved for this user!")
public class DuplicateReservationException extends RuntimeException{

	private static final long serialVersionUID = 506170362167706101L;

	public DuplicateReservationException() {
		super();
	}
	
	public DuplicateReservationException(String messsge) {
		super(messsge);
	}
}
