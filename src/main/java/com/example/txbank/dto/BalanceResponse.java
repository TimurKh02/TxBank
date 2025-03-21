package com.example.txbank.dto;

import java.math.BigDecimal;

public class BalanceResponse {
	
	private Long bankCard;
	private BigDecimal balanceCard;	
	private BigDecimal balanceAccumulative;
	private BigDecimal balanceCredit;
	
	
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

	public Long getBankCard() {
		return bankCard;
	}

	public void setBankCard(Long bankCard) {
		this.bankCard = bankCard;
	}

	public BigDecimal getBalanceCard() {
		return balanceCard;
	}

	public void setBalanceCard(BigDecimal balanceCard) {
		this.balanceCard = balanceCard;
	}

	public BalanceResponse() {
	}

	public BalanceResponse(Long bankCard, BigDecimal balanceCard) {
		this.bankCard = bankCard;
		this.balanceCard = balanceCard;
	}

	public BalanceResponse(BigDecimal balanceCard) {
		this.balanceCard = balanceCard;
	}

	public BalanceResponse(BigDecimal balanceCard, BigDecimal balanceAccumulative, BigDecimal balanceCredit) {
		this.balanceCard = balanceCard;
		this.balanceAccumulative = balanceAccumulative;
		this.balanceCredit = balanceCredit;
	}

	public BalanceResponse(Long bankCard, BigDecimal balanceCard, BigDecimal balanceAccumulative,
			BigDecimal balanceCredit) {
		this.bankCard = bankCard;
		this.balanceCard = balanceCard;
		this.balanceAccumulative = balanceAccumulative;
		this.balanceCredit = balanceCredit;
	}

	@Override
	public String toString() {
		return "BalanceResponse [bankCard=" + bankCard + ", balanceCard=" + balanceCard + "]";
	}

}
