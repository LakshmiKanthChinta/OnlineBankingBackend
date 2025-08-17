package com.bank.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankCustomerDetailsException extends RuntimeException {

	private String exceptionmsg;
	
	
}
