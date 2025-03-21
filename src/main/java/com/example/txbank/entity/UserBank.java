package com.example.txbank.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "userBank")
public class UserBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "bank_card")
	private Long bankCard;
	@Column(name = "name")
	private String name;
	@Column(name = "login")
	@NotBlank(message = "Login cannot be empty")
	private String login;
	@Column(name = "password")
	@NotBlank(message = "Password cannot be empty")
	private String password;
	@Column(name = "balance_card")
	private BigDecimal balanceCard;
	@Column(name = "balance_accumulative")
	private BigDecimal balanceAccumulative;
	@Column(name = "balance_credit")
	private BigDecimal balanceCredit;
	@Column(name = "status", columnDefinition = "BOOLEAN")
	private boolean status;
	@Column(name = "role_user")
	private String roleUser;

	@OneToMany(mappedBy = "sender") // Связь с отправителем
	private List<UserTransactionHistory> sentTransactions;
	@OneToMany(mappedBy = "receiver") // Связь с получателем
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

	public UserBank() {
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

	public UserBank(int id, Long bankCard, String name, @NotBlank(message = "Login cannot be empty") String login,
			@NotBlank(message = "Password cannot be empty") String password, BigDecimal balanceCard, boolean status,
			String roleUser, List<UserTransactionHistory> sentTransactions,
			List<UserTransactionHistory> receivedTransactions) {
		this.id = id;
		this.bankCard = bankCard;
		this.name = name;
		this.login = login;
		this.password = password;
		this.balanceCard = balanceCard;
		this.status = status;
		this.roleUser = roleUser;
		this.sentTransactions = sentTransactions;
		this.receivedTransactions = receivedTransactions;
	}

	@Override
	public String toString() {
		return "UserBank [id=" + id + ", bankCard=" + bankCard + ", name=" + name + ", login=" + login
				+ ", balanceCard=" + balanceCard + ", status=" + status + ", roleUser=" + roleUser
				+ ", sentTransactions=" + sentTransactions + ", receivedTransactions=" + receivedTransactions + "]";
	}

}
