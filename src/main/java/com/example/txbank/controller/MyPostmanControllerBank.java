package com.example.txbank.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.txbank.dto.BalanceResponse;
import com.example.txbank.dto.UserBankTransactionResponse;
import com.example.txbank.dto.UserBankomatResponse;
import com.example.txbank.entity.UserBank;
import com.example.txbank.exceptionHandling.InsufficientFundsException;
import com.example.txbank.exceptionHandling.UserNotFoundException;
import com.example.txbank.service.TxBankService;


@RestController
@RequestMapping("/api")
public class MyPostmanControllerBank {

	@Autowired
	private TxBankService txBankService;

	@GetMapping("/getUserBankAdmin")
	public ResponseEntity<List<UserBank>> getUserBankAdmin() {
		try {
			List<UserBank> getUserBank = txBankService.getUserBankAdmin();
			return ResponseEntity.ok(getUserBank);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/getBankIdAdmin/{id}")
	public ResponseEntity<UserBank> getUserBankIdAdmin(@PathVariable int id) {
		try {
			UserBank userBankIdAdmin = txBankService.getUserBankIdAdmin(id);
			return ResponseEntity.ok(userBankIdAdmin);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/getMyBalance/{id}")
	public ResponseEntity<BalanceResponse> getMyBalanceCard(@PathVariable int id) {
		try {
			BalanceResponse balanceResponse = txBankService.getMyBalanceCard(id);
			return ResponseEntity.ok(balanceResponse);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/transferMoney/{typePayment}/{senderId}/{receiverBankCard}/{amount}/{commentTransaction}")
	public ResponseEntity<UserBankTransactionResponse> transferMoney(@PathVariable String typePayment, @PathVariable int senderId,
			@PathVariable Long receiverBankCard, @PathVariable BigDecimal amount, @PathVariable String commentTransaction) {
		try {
			UserBankTransactionResponse userTranferMoney = txBankService.transferMoney(typePayment ,senderId, receiverBankCard, amount, commentTransaction);
			return ResponseEntity.ok(userTranferMoney);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (InsufficientFundsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}

	@PutMapping("/depositMoney/{id}/{amount}")
	public ResponseEntity<UserBankomatResponse> depositMoney(@PathVariable int id, @PathVariable BigDecimal amount) {
		try {
			UserBankomatResponse userDepositMoney = txBankService.depositMoney(id, amount);
			return ResponseEntity.ok(userDepositMoney);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/withdrawMoney/{id}/{amount}")
	public ResponseEntity<UserBankomatResponse> withdrawMoney(@PathVariable int id, @PathVariable BigDecimal amount) {
		try {
			UserBankomatResponse userWithdrawMoney = txBankService.withdrawMoney(id, amount);
			return ResponseEntity.ok(userWithdrawMoney);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (InsufficientFundsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("/getTransactionHistory/{id}")
	public ResponseEntity<UserBankTransactionResponse> getTransactionHistory(@PathVariable int id) {
		try {
			UserBankTransactionResponse userTransactionHistory = txBankService.getTransactionHistory(id);
			return ResponseEntity.ok(userTransactionHistory);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
