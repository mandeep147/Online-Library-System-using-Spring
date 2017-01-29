package edu.cmpe275.team13.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Can not delete book when patron has checked out!")
public class DeleteBookNotPermitted extends RuntimeException {
	
	private static final long serialVersionUID = 1234325346L;

	public DeleteBookNotPermitted() {
		super();
	}

	public DeleteBookNotPermitted(String message) {
		super(message);
	}

}