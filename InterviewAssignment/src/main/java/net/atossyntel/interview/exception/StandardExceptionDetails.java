package net.atossyntel.interview.exception;

import java.time.LocalDateTime;

public class StandardExceptionDetails {

	private LocalDateTime timestamp;

	private String message;

	public StandardExceptionDetails(LocalDateTime timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}
}
