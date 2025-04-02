package com.example.txbank.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.txbank.entity.UserTransactionHistory;

import jakarta.persistence.Column;

public class UserBankTransactionResponse {

	private int id;

	private Long bankCard;

	private String name;

	private BigDecimal balanceCard;

	private BigDecimal balanceAccumulative;
	
	private BigDecimal balanceCredit;
	
	private List<UserTransactionHistory> sentTransactions;

	private List<UserTransactionHistory> receivedTransactions;

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

	public List<UserTransactionHistory> getSentTransactions() {
		return sentTransactions;
	}

	public void setSentTransactions(List<UserTransactionHistory> sentTransactions) {
		this.sentTransactions = sentTransactions;
	}

	public List<UserTransactionHistory> getReceivedTransactions() {
		return receivedTransactions;
	}

	public void setReceivedTransactions(List<UserTransactionHistory> receivedTransactions) {
		this.receivedTransactions = receivedTransactions;
	}

	public BigDecimal getBalanceAccumulative() {
		return balanceAccumulative;
	}

	public void setBalanceAccumulative(BigDecimal balanceAccumulative) {
		this.balanceAccumulative = balanceAccumulative;
	}

	public BigDecimal getBalanceCredit() {
		return balanceCredit;
	}

	public void setBalanceCredit(BigDecimal balanceCredit) {
		this.balanceCredit = balanceCredit;
	}

	public UserBankTransactionResponse() {
	}

	public UserBankTransactionResponse(int id, BigDecimal balanceCredit) {
		super();
		this.id = id;
		this.balanceCredit = balanceCredit;
	}

	public UserBankTransactionResponse(int id, BigDecimal balanceCard, BigDecimal balanceAccumulative) {
		this.id = id;
		this.balanceCard = balanceCard;
		this.balanceAccumulative = balanceAccumulative;
	}

	public UserBankTransactionResponse(int id, Long bankCard, String name, BigDecimal balanceCard,
			List<UserTransactionHistory> sentTransactions, List<UserTransactionHistory> receivedTransactions) {
		this.id = id;
		this.bankCard = bankCard;
		this.name = name;
		this.balanceCard = balanceCard;
		this.sentTransactions = sentTransactions;
		this.receivedTransactions = receivedTransactions;
	}

	@Override
	public String toString() {
		return "UserBankTransactionResponse [id=" + id + ", bankCard=" + bankCard + ", name=" + name + ", balanceCard="
				+ balanceCard + ", sentTransactions=" + sentTransactions + ", receivedTransactions="
				+ receivedTransactions + "]";
	}

	public List<UserTransactionHistory> getAllTransaction() {

		return Stream
				.concat(sentTransactions != null ? sentTransactions.stream() : Stream.empty(),
						receivedTransactions != null ? receivedTransactions.stream() : Stream.empty())
				.sorted((t1, t2) -> t2.getTransactionDate().compareTo(t1.getTransactionDate()))
				.collect(Collectors.toList());
		
	}

}
