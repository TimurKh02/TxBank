package com.example.txbank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserBankomatResponse {

	private int id;

	private Long bankCard;

	private String name;

	private BigDecimal balanceCard;

	private BigDecimal amount;

	private LocalDateTime transactionDate;

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

	public BigDecimal getBalanceCard() {
		return balanceCard;
	}

	public void setBalanceCard(BigDecimal balanceCard) {
		this.balanceCard = balanceCard;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public UserBankomatResponse() {
	}

	public UserBankomatResponse(int id, Long bankCard, String name, BigDecimal balanceCard, BigDecimal amount,
			LocalDateTime transactionDate) {
		this.id = id;
		this.bankCard = bankCard;
		this.name = name;
		this.balanceCard = balanceCard;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "UserBankInfoResponse [id=" + id + ", bankCard=" + bankCard + ", name=" + name + ", balanceCard="
				+ balanceCard + ", transactionDate=" + transactionDate + ", amount=" + amount + "]";
	}

}
