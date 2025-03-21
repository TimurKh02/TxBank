package com.example.txbank.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.txbank.dto.BalanceResponse;
import com.example.txbank.dto.UserBankInfoResponse;
import com.example.txbank.dto.UserBankTransactionResponse;
import com.example.txbank.dto.UserBankomatResponse;
import com.example.txbank.entity.SupportMessage;
import com.example.txbank.entity.UserBank;

public interface TxBankService {
	
	public UserBank saveUser(String login, String password, String name);
	
	public List<UserBank> getUserBankAdmin();
	
	public UserBank getUserBankIdAdmin (int id);
	
	public BalanceResponse getMyBalanceCard(int id);

	public UserBankTransactionResponse transferMoney(String typePayment, int senderId, Long receiverBankCard, BigDecimal  amount, String commentTransaction);
	
	public UserBankomatResponse depositMoney(int id, BigDecimal  amount);
	
	public UserBankomatResponse withdrawMoney(int id, BigDecimal  amount);
	
	public UserBankTransactionResponse getTransactionHistory(int id);
	
	public UserBankInfoResponse getUserByLogin(String login);	
	
	public List<BigDecimal> getMathematicalCredit(BigDecimal amount, BigDecimal clientMonth);
	
	public UserBankTransactionResponse transferAccumulativeBalance(BigDecimal amount, int userId);
	
//	public UserBankTransactionResponse getMoneyCredit();
	
}
