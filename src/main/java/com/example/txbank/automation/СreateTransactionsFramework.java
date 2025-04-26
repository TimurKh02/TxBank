package com.example.txbank.automation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.txbank.dao.TxTransactionHistoryRepository;
import com.example.txbank.entity.UserBank;
import com.example.txbank.entity.UserTransactionHistory;

@Component
public class СreateTransactionsFramework {

	@Autowired
	private TxTransactionHistoryRepository txTransactionHistoryRepository;

	public void transferTransaction(BigDecimal amount, UserBank userSender, UserBank userReceiver, String commentTransaction) {
		
		UserTransactionHistory senderTransaction = new UserTransactionHistory();
		senderTransaction.setTransactionAmount(amount.negate());
		senderTransaction.setDescription("Transfer: " + userSender.getName());
		senderTransaction.setTransactionDate(LocalDateTime.now());
		senderTransaction.setSender(userSender);
		senderTransaction.setBankTransactionInfo("Unknown bank");
		senderTransaction.setStatusTransaction("COMPLETED");
		senderTransaction.setTypeTransaction("TRANSFER");		
		if(!commentTransaction.isEmpty()) {
			senderTransaction.setCommentTransaction(commentTransaction);
		} else {
			senderTransaction.setCommentTransaction("No additional information");
		}
		txTransactionHistoryRepository.save(senderTransaction);
		
		UserTransactionHistory receiverTransaction = new UserTransactionHistory();
		receiverTransaction.setTransactionAmount(amount);
		receiverTransaction.setDescription("Transfer: " + userSender.getName());
		receiverTransaction.setTransactionDate(LocalDateTime.now());
		receiverTransaction.setReceiver(userReceiver);
		receiverTransaction.setBankTransactionInfo("Unknown bank");
		receiverTransaction.setStatusTransaction("COMPLETED");
		receiverTransaction.setTypeTransaction("TRANSFER");
		if(!commentTransaction.isEmpty()) {
			receiverTransaction.setCommentTransaction(commentTransaction);
		} else {
			receiverTransaction.setCommentTransaction("No additional information");
		}
		txTransactionHistoryRepository.save(receiverTransaction);
		
	}

	public void mistakeTransferTransaction(BigDecimal amount) {
		
		UserTransactionHistory transaction = new UserTransactionHistory();
		transaction.setTransactionAmount(amount);
		transaction.setDescription("Transfer: " + "UNKNOWN MISTAKE");
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setSender(null);
		transaction.setReceiver(null);
		transaction.setBankTransactionInfo("Unknown bank");
		transaction.setStatusTransaction("FAILED");
		transaction.setTypeTransaction("TRANSFER");
		transaction.setCommentTransaction("NULL");
		txTransactionHistoryRepository.save(transaction);
	}

	public void depositTransaction(int id, BigDecimal amount, UserBank userBank) {

		UserTransactionHistory transaction = new UserTransactionHistory();
		transaction.setTransactionAmount(amount);
		transaction.setDescription("Deposit: " + userBank.getName());
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setSender(null);
		transaction.setReceiver(userBank);
		transaction.setBankTransactionInfo("BANKOMAT");
		transaction.setStatusTransaction("COMPLETED");
		transaction.setTypeTransaction("DEPOSIT");
		txTransactionHistoryRepository.save(transaction);
	}
	
	public void mistakeDepositTransaction(BigDecimal amount) {

		UserTransactionHistory transaction = new UserTransactionHistory();
		transaction.setTransactionAmount(amount);
		transaction.setDescription("Deposit: " + "UNKNOWN MISTAKE");
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setBankTransactionInfo("BANKOMAT");
		transaction.setStatusTransaction("UNFINISHED");
		transaction.setTypeTransaction("DEPOSIT");
		txTransactionHistoryRepository.save(transaction);
	}
	
	public void withdrawTransaction (BigDecimal amount, UserBank userBank) {
		
		UserTransactionHistory transaction = new UserTransactionHistory();
		transaction.setTransactionAmount(amount.negate());
		transaction.setDescription("Withdraw: " + userBank.getName());
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setBankTransactionInfo("BANKOMAT");
		transaction.setSender(null);
		transaction.setReceiver(userBank);
		transaction.setStatusTransaction("COMPLETED");
		transaction.setTypeTransaction("WITHDRAW");
		txTransactionHistoryRepository.save(transaction);
	}
	
	public void mistakeWithdrawTransaction (BigDecimal amount) {
		
		UserTransactionHistory transaction = new UserTransactionHistory();
		transaction.setTransactionAmount(amount);
		transaction.setDescription("Withdraw: " + "UNKNOWN ORDER");
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setBankTransactionInfo("BANKOMAT");
		transaction.setStatusTransaction("UNFINISHED");
		transaction.setTypeTransaction("WITHDRAW");
		txTransactionHistoryRepository.save(transaction);
	}
	
	public void getCreditMoneyTransaction (BigDecimal amount, UserBank userBank) {
		
		UserTransactionHistory transaction = new UserTransactionHistory();
		transaction.setTransactionAmount(amount);
		transaction.setDescription("Credit: " + "TxBank");
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setReceiver(userBank);
		transaction.setBankTransactionInfo("TxBank Credit");
		transaction.setStatusTransaction("COMPLETED");
		transaction.setTypeTransaction("GET CREDIT");
		txTransactionHistoryRepository.save(transaction);
	}
	
	public void mistakeCreditMoneyTransaction (BigDecimal amount, UserBank userBank) {
		
		UserTransactionHistory transaction = new UserTransactionHistory();
		transaction.setTransactionAmount(amount);
		transaction.setDescription("Credit: " + "TxBank");
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setReceiver(userBank);
		transaction.setBankTransactionInfo("TxBank Credit");
		transaction.setStatusTransaction("UNFINISHED");
		transaction.setTypeTransaction("GET CREDIT");
		txTransactionHistoryRepository.save(transaction);
	}

}
