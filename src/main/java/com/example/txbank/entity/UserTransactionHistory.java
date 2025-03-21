package com.example.txbank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction_history")
public class UserTransactionHistory {

	@Id
	@Column(name = "id_transaction")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTransaction;
	@Column(name = "transaction_amount")
	private BigDecimal transactionAmount;
	@Column(name = "bank_transaction_info")
	private String bankTransactionInfo;
	@Column(name = "description")
	private String description;
	@Column(name = "status_transaction")
	private String statusTransaction;
	@Column(name = "type_transaction")
	private String typeTransaction;
	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;
	@Column(name = "comment_transaction")
	private String commentTransaction;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sender_id", referencedColumnName = "id")
	private UserBank sender;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "receiver_id", referencedColumnName = "id")
	private UserBank receiver;

	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getBankTransactionInfo() {
		return bankTransactionInfo;
	}

	public void setBankTransactionInfo(String bankTransactionInfo) {
		this.bankTransactionInfo = bankTransactionInfo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusTransaction() {
		return statusTransaction;
	}

	public void setStatusTransaction(String statusTransaction) {
		this.statusTransaction = statusTransaction;
	}

	public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public UserBank getSender() {
		return sender;
	}

	public void setSender(UserBank sender) {
		this.sender = sender;
	}

	public UserBank getReceiver() {
		return receiver;
	}

	public void setReceiver(UserBank receiver) {
		this.receiver = receiver;
	}

	public UserTransactionHistory() {
	}

	public String getCommentTransaction() {
		return commentTransaction;
	}

	public void setCommentTransaction(String commentTransaction) {
		this.commentTransaction = commentTransaction;
	}

	public UserTransactionHistory(int idTransaction, BigDecimal transactionAmount, String bankTransactionInfo,
			String description, String statusTransaction, String typeTransaction, LocalDateTime transactionDate,
			UserBank sender, UserBank receiver) {
		this.idTransaction = idTransaction;
		this.transactionAmount = transactionAmount;
		this.bankTransactionInfo = bankTransactionInfo;
		this.description = description;
		this.statusTransaction = statusTransaction;
		this.typeTransaction = typeTransaction;
		this.transactionDate = transactionDate;
		this.sender = sender;
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "TransactionHistory [idTransaction=" + idTransaction + ", transactionAmount=" + transactionAmount
				+ ", bankTransactionInfo=" + bankTransactionInfo + ", description=" + description
				+ ", statusTransaction=" + statusTransaction + ", typeTransaction=" + typeTransaction
				+ ", transactionDate=" + transactionDate + ", sender=" + sender + ", receiver=" + receiver + "]";
	}

}
