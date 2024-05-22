package com.example.carsystem.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LoginPasswordException.class)
	public ResponseEntity<StandardError> invalidLoginOrPasswordException(LoginPasswordException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardError err = new StandardError();

		err.setErrorCode(status.value());
		err.setMessage(e.getMessage());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(EmailException.class)
	public ResponseEntity<StandardError> emailAlreadyException(EmailException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;

		StandardError err = new StandardError();

		err.setErrorCode(status.value());
		err.setMessage(e.getMessage());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(LoginAlreadyException.class)
	public ResponseEntity<StandardError> loginAlreadyException(LoginAlreadyException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;

		StandardError err = new StandardError();

		err.setErrorCode(status.value());
		err.setMessage(e.getMessage());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MissingFieldsException.class)
	public ResponseEntity<StandardError> missingFieldsException(MissingFieldsException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError err = new StandardError();

		err.setErrorCode(status.value());
		err.setMessage(e.getMessage());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(TokenJWTExeption.class)
	public ResponseEntity<StandardError> invalidTokenJWTExeption(TokenJWTExeption e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;

		StandardError err = new StandardError();

		err.setErrorCode(status.value());
		err.setMessage(e.getMessage());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(LicensePlateException.class)
	public ResponseEntity<StandardError> licensePlateAlreadyExistsException(LicensePlateException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;

		StandardError err = new StandardError();

		err.setErrorCode(status.value());
		err.setMessage(e.getMessage());

		return ResponseEntity.status(status).body(err);
	}
}
