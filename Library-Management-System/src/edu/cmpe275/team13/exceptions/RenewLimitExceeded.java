package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Renew limit exceeded!")
public class RenewLimitExceeded extends RuntimeException {

	private static final long serialVersionUID = -4940123453631809925L;
	
	public RenewLimitExceeded() {
		super();
	}
	
	public RenewLimitExceeded(String message) {
		super(message);
	}
}
