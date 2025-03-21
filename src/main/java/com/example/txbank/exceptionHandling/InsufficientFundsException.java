package com.example.txbank.exceptionHandling;

public class InsufficientFundsException extends RuntimeException {

	public InsufficientFundsException(String message) {
		super(message);
	}
}
