package com.example.txbank.dto;

import java.time.LocalDateTime;

public class UserBankInfoMongoDbResponse {

	private String id;

	private String login;

	private String password;

	private String name;

	private LocalDateTime createdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public UserBankInfoMongoDbResponse() {
		super();
	}

	public UserBankInfoMongoDbResponse(String id) {
		super();
		this.id = id;
	}

	public UserBankInfoMongoDbResponse(String login, String password, String name) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
	}

}
