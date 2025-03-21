package com.example.txbank.exceptionHandling;

public class TransactionMistakeException extends RuntimeException {

	public TransactionMistakeException(String message) {
		super(message);
	}
}
