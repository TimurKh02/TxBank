package com.example.txbank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "support_message")
public class SupportMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "user_email")
	private String userEmail;
	@Column(name = "user_message")
	private String userMessage;
	@Column(name = "status")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public SupportMessage() {
	}

	public SupportMessage(String userEmail, String userMessage) {
		this.userEmail = userEmail;
		this.userMessage = userMessage;
	}

	@Override
	public String toString() {
		return "SupportMessage [userEmail=" + userEmail + ", userMessage=" + userMessage + "]";
	}

}
