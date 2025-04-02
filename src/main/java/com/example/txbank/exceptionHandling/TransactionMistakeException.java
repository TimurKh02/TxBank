package com.example.txbank.exceptionHandling;

public class TransactionMistakeException extends RuntimeException {

	public TransactionMistakeException(String message, Throwable cause) {
		super(message, cause);
	}
}
