package net.atossyntel.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
public class ResourceAlreadyPresentException extends BanknameParentException {

	public ResourceAlreadyPresentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
