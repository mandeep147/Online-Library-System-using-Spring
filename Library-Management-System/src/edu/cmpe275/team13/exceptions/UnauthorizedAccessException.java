package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "User not authorized!")
public class UnauthorizedAccessException extends RuntimeException {
	
	private static final long serialVersionUID = 7653994932729587607L;

	public UnauthorizedAccessException() {
		super();
	}
	
	public UnauthorizedAccessException(String message) {
		super(message);
	}
}
