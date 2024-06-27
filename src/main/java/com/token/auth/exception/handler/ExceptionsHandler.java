package com.token.auth.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.token.auth.exception.custom.ForgotPasswordModuleException;
import com.token.auth.response.dto.Response;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(ForgotPasswordModuleException.class)
	public ResponseEntity<Object> handleForgotPasswordModuleException(ForgotPasswordModuleException ex) {
		Response response = new Response("error",ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
