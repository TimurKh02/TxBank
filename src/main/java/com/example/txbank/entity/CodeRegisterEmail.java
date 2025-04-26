package com.example.txbank.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;

@Entity
@Table(name = "code_register_email")
public class CodeRegisterEmail {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "emailUsers")
	private String emailUsers;
	@Column(name = "codeEmailGenerate")
	private String codeEmailGenerate;
	@Column(name = "verifiedStatus", nullable = false)
	private boolean verifiedStatus;
	@Future
	@Column(name = "codeTime", nullable = false)
	private LocalDateTime codeTime;

	public CodeRegisterEmail() {
		super();
	}

	public CodeRegisterEmail(int id, String emailUsers, String codeEmailGenerate, boolean verifiedStatus,
			@Future LocalDateTime codeTime) {
		super();
		this.id = id;
		this.emailUsers = emailUsers;
		this.codeEmailGenerate = codeEmailGenerate;
		this.verifiedStatus = verifiedStatus;
		this.codeTime = codeTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailUsers() {
		return emailUsers;
	}

	public void setEmailUsers(String emailUsers) {
		this.emailUsers = emailUsers;
	}

	public String getCodeEmailGenerate() {
		return codeEmailGenerate;
	}

	public void setCodeEmailGenerate(String codeEmailGenerate) {
		this.codeEmailGenerate = codeEmailGenerate;
	}

	public boolean isVerifiedStatus() {
		return verifiedStatus;
	}

	public void setVerifiedStatus(boolean verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
	}

	public LocalDateTime getCodeTime() {
		return codeTime;
	}

	public void setCodeTime(LocalDateTime codeTime) {
		this.codeTime = codeTime;
	}

}
