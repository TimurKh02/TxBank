package com.example.txbank.dto;

public class SupportMessageResponse {

	private String userEmail;
	private String userMessage;
	private String status;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SupportMessageResponse() {
	}

	public SupportMessageResponse(String userEmail, String userMessage, String status) {
		this.userEmail = userEmail;
		this.userMessage = userMessage;
		this.status = status;
	}

}
