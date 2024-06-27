package com.token.auth.exception.custom;

import org.springframework.http.HttpStatus;

public class ForgotPasswordModuleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	private HttpStatus errorStatusCode;

	public ForgotPasswordModuleException(String errorMessage, HttpStatus errorStatusCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorStatusCode = errorStatusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getErrorStatusCode() {
		return errorStatusCode;
	}

	public void setErrorStatusCode(HttpStatus errorStatusCode) {
		this.errorStatusCode = errorStatusCode;
	}

}