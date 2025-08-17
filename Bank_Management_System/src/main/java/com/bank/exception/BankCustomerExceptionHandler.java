package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BankCustomerExceptionHandler {

	@ExceptionHandler(BankCustomerDetailsException.class)
	public ResponseEntity<String> handleCustomerException(BankCustomerDetailsException cde) {
		return new ResponseEntity<String>(cde.getExceptionmsg(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(AdminDetailsException.class)
	public ResponseEntity<String> handleAdminException(AdminDetailsException ade) {
		return new ResponseEntity<String>(ade.getExceptionMsg(),HttpStatus.NOT_ACCEPTABLE);
	}
}
