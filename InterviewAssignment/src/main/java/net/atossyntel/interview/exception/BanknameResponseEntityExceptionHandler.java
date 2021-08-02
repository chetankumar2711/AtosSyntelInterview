package net.atossyntel.interview.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.atossyntel.interview.model.Customer;

@Component
@ControllerAdvice
public class BanknameResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Customer> handleAllExceptions() {
		StandardExceptionDetails exceptionDetails = new StandardExceptionDetails(LocalDateTime.now(),
				"Generic exception being thrown");
		return new ResponseEntity(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BanknameParentException.class)
	public ResponseEntity<Customer> handleBanknameParentException() {
		StandardExceptionDetails exceptionDetails = new StandardExceptionDetails(LocalDateTime.now(),
				"Bankname Parent exception being thrown");
		return new ResponseEntity(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Customer> handleCustomerNotFoundException() {
		StandardExceptionDetails exceptionDetails = new StandardExceptionDetails(LocalDateTime.now(),
				"Customer not found");
		return new ResponseEntity(exceptionDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceAlreadyPresentException.class)
	public ResponseEntity<Customer> handleCustomerAlreadyExistingException() {
		StandardExceptionDetails exceptionDetails = new StandardExceptionDetails(LocalDateTime.now(),
				"Customer already exists");
		return new ResponseEntity(exceptionDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

}
