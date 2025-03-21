package com.example.txbank.dto;

import java.math.BigDecimal;

public class UserBankInfoResponse {
	
	private int id;
	
	private Long bankCard;

	private String name;

	private String login;

	private String password;

	private BigDecimal balanceCard;

	private boolean status;
	
	private String roleUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getBankCard() {
		return bankCard;
	}

	public void setBankCard(Long bankCard) {
		this.bankCard = bankCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public BigDecimal getBalanceCard() {
		return balanceCard;
	}

	public void setBalanceCard(BigDecimal balanceCard) {
		this.balanceCard = balanceCard;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}

	public UserBankInfoResponse() {
	}

	public UserBankInfoResponse(int id, String login) {
		this.id = id;
		this.login = login;
	}
	
}
