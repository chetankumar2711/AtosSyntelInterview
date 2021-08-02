package net.atossyntel.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BanknameParentException {

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
