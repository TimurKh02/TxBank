package com.example.txbank.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;

@Document(collection = "temporary_users_txbank")
public class TemporaryUserBaseBank {

	@Id
	@Field("id")
	private String id;
	@Field("login")
	private String login;
	@Field("password")
	private String password;
	@Field("name")
	private String name;
	@Field("created_date")
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

	public TemporaryUserBaseBank() {

	}

	public TemporaryUserBaseBank(String login, String password, String name) {
		this.id = UUID.randomUUID().toString();
		this.login = login;
		this.password = password;
		this.name = name;
		this.createdDate = LocalDateTime.now();
	}

}
