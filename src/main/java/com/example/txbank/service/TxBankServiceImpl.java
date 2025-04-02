package com.example.txbank.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.txbank.automation.AutomationCardFramework;
import com.example.txbank.automation.СreateTransactionsFramework;
import com.example.txbank.dao.TxBankRepository;
import com.example.txbank.dto.BalanceResponse;
import com.example.txbank.dto.UserBankInfoResponse;
import com.example.txbank.dto.UserBankTransactionResponse;
import com.example.txbank.dto.UserBankomatResponse;
import com.example.txbank.entity.UserBank;
import com.example.txbank.enums.Role;
import com.example.txbank.exceptionHandling.InsufficientFundsException;
import com.example.txbank.exceptionHandling.TransactionMistakeException;
import com.example.txbank.exceptionHandling.UserNotFoundException;

@Service
public class TxBankServiceImpl implements TxBankService {

	@Autowired
	private TxBankRepository txBankRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private СreateTransactionsFramework createTransactionsFramework;

	private static final Logger logger = LoggerFactory.getLogger(TxBankServiceImpl.class);

	@Override
	public UserBank saveUser(String login, String password, String name) {

		BigDecimal startBalance = new BigDecimal("0.00");
		AutomationCardFramework automationCard = new AutomationCardFramework();
		long cardNumberGeneration = automationCard.generationCardNumber();

		UserBank userBank = new UserBank();
		userBank.setLogin(login);
		userBank.setPassword(passwordEncoder.encode(password));
		userBank.setName(name);
		userBank.setBankCard(cardNumberGeneration);
		userBank.setBalanceCard(startBalance);
		userBank.setBalanceCredit(startBalance);
		userBank.setBalanceAccumulative(startBalance);
		userBank.setRoleUser(Role.ROLE_USER.name());
		userBank.setStatus(true);
		txBankRepository.save(userBank);

		return userBank;
	}

	@Override
	public List<UserBank> getUserBankAdmin() {

		List<UserBank> getAllUserBank = txBankRepository.findAll();

		if (getAllUserBank.isEmpty()) {
			logger.warn("\"Something went wrong, we are already working on solving this problem...!\"");
		} else {
			logger.info("All users were found successfully...!");
		}
		return getAllUserBank;
	}

	@Override
	public UserBankInfoResponse getUserByLogin(String login) {

		Optional<UserBank> optional = txBankRepository.findByLogin(login);

		if (optional.isPresent()) {
			UserBank user = optional.get();
			logger.info("User with login {} found", login);
			return new UserBankInfoResponse(user.getId(), user.getLogin());
		} else {
			logger.warn("User with login {} not found", login);
			throw new UserNotFoundException("User with login " + login + " not found");
		}

	}

	@Override
	public UserBank getUserBankIdAdmin(int id) {

		Optional<UserBank> optional = txBankRepository.findById(id);

		if (optional.isPresent()) {
			UserBank user = optional.get();
			logger.info("User with ID {} found", id);
			return user;
		} else {
			logger.warn("User with ID {} not found", id);
			throw new UserNotFoundException("User with ID " + id + " not found");
		}

	}

	@Override
	public BalanceResponse getMyBalanceCard(int id) {

		Optional<UserBank> optional = txBankRepository.findById(id);

		if (optional.isPresent()) {
			UserBank user = optional.get();
			logger.info("User balance for ID {}: {}", id, user.getBalanceCard(), user.getBalanceAccumulative(),
					user.getBalanceCredit());
			return new BalanceResponse(user.getBankCard(), user.getBalanceCard(), user.getBalanceAccumulative(),
					user.getBalanceCredit());
		} else {
			logger.warn("User with ID {} not found", id);
			throw new UserNotFoundException("User with ID " + id + " not found");
		}
	}

	@Override
	public UserBankTransactionResponse transferMoney(String typePayment, int senderId, Long receiverBankCard,
			BigDecimal amount, String commentTransaction) {

		Optional<UserBank> optionalSenderId = txBankRepository.findById(senderId);
		Optional<UserBank> optionalReceiverBankCard = txBankRepository.findByBankCard(receiverBankCard);

		if (optionalSenderId.isPresent() && optionalReceiverBankCard.isPresent()) {
			UserBank userSender = optionalSenderId.get();
			UserBank userReceiver = optionalReceiverBankCard.get();

			BigDecimal balanceSender;
			BigDecimal balanceReceiver = userReceiver.getBalanceCard();

			switch (typePayment) {
			case "1":
				balanceSender = userSender.getBalanceCard();
				break;
			case "2":
				balanceSender = userSender.getBalanceAccumulative();
				break;
			case "3":
				balanceSender = userSender.getBalanceCredit();
				break;
			default:
				throw new IllegalArgumentException("Невідомий тип оплати: " + typePayment);
			}

			// Проверка на баланс
			if (balanceSender.compareTo(amount) < 0) { // Равно такому варианту if(balanceSender < amount)
				logger.warn("Insufficient funds for user with ID " + senderId);
				throw new InsufficientFundsException("Insufficient funds for user with ID " + senderId);
			}

			switch (typePayment) {
			case "1":
				userSender.setBalanceCard(balanceSender.subtract(amount));
				break;
			case "2":
				userSender.setBalanceAccumulative(balanceSender.subtract(amount));
				break;
			case "3":
				userSender.setBalanceCredit(balanceSender.subtract(amount));
				break;
			}

			userReceiver.setBalanceCard(balanceReceiver.add(amount));// Выполняет сложение + , равно такому варианту
																		// balanceSender + amount.
			// Сохранение обновленных пользователей
			txBankRepository.save(userSender);
			txBankRepository.save(userReceiver);
			// Создание транзакции
			createTransactionsFramework.transferTransaction(amount, userSender, userReceiver, commentTransaction);

			logger.info("Transferred {} from user {} to user {}", amount, senderId, receiverBankCard);

			return new UserBankTransactionResponse(userSender.getId(), userSender.getBankCard(), userSender.getName(),
					userSender.getBalanceCard(), userSender.getSentTransactions(),
					userSender.getReceivedTransactions());
		} else {

			createTransactionsFramework.mistakeTransferTransaction(amount);
			logger.warn("User with ID {} or ID {} not found", senderId, receiverBankCard);
			throw new UserNotFoundException("User with ID " + senderId + " or ID " + receiverBankCard + " not found");

		}
	}

	@Override
	public UserBankomatResponse depositMoney(int id, BigDecimal amount) {

		Optional<UserBank> optional = txBankRepository.findById(id);

		if (optional.isPresent()) {
			UserBank userBank = optional.get();
			BigDecimal balanceReceiver = userBank.getBalanceCard();
			userBank.setBalanceCard(balanceReceiver.add(amount));

			txBankRepository.save(userBank);
			logger.info(
					"The user with this " + id + " was successfully replenished balance with the amount: " + amount);

			createTransactionsFramework.depositTransaction(id, amount, userBank);

			return new UserBankomatResponse(userBank.getId(), userBank.getBankCard(), userBank.getName(),
					userBank.getBalanceCard(), amount, LocalDateTime.now());
		} else {

			createTransactionsFramework.mistakeDepositTransaction(amount);
			logger.warn("Error, no user with this " + id + " was found, replenishment cancelled.Try again ...");
			throw new UserNotFoundException("Error, no user with this " + id + " was found, replenishment cancelled");
		}
	}

	@Override
	public UserBankomatResponse withdrawMoney(int id, BigDecimal amount) {

		// Проверка на отрицательные значения
		if (amount.compareTo(BigDecimal.ZERO) <= 0) { // Равно такому варианту if(amount <= 0)
			throw new IllegalArgumentException("Withdrawal amount must be positive");
		}

		Optional<UserBank> optional = txBankRepository.findById(id);

		if (optional.isPresent()) {

			UserBank userBank = optional.get();
			BigDecimal userBalance = userBank.getBalanceCard();

			if (userBalance.compareTo(amount) < 0) { // Равно такому варианту if(userBalance < amount)

				logger.warn("Insufficient funds for user with ID " + id);
				throw new InsufficientFundsException("Insufficient funds for user with ID " + id);
			}

			userBank.setBalanceCard(userBalance.subtract(amount));
			txBankRepository.save(userBank);
			logger.info("Funds have been withdrawn from account ID {}: Amount withdrawn = {}. Have a good day..!", id,
					amount);

			createTransactionsFramework.withdrawTransaction(amount, userBank);
			return new UserBankomatResponse(userBank.getId(), userBank.getBankCard(), userBank.getName(),
					userBank.getBalanceCard(), amount, LocalDateTime.now());
		} else {

			createTransactionsFramework.mistakeWithdrawTransaction(amount);
			logger.warn("Error, account with this: " + id + " does not exist, withdrawal cancelled. Try again ...");
			throw new UserNotFoundException(
					"Error, account with this: " + id + " does not exist, withdrawal cancelled.");
		}
	}

	@Override
	public UserBankTransactionResponse getTransactionHistory(int id) {
		// TODO Auto-generated method stub
		Optional<UserBank> optional = txBankRepository.findById(id);

		if (optional.isPresent()) {

			UserBank userBank = optional.get();
			userBank.getSentTransactions();
			userBank.getReceivedTransactions();

			logger.info(
					"Retrieving the transaction history of the user with this " + id + " was successfully received");
			return new UserBankTransactionResponse(userBank.getId(), userBank.getBankCard(), userBank.getName(),
					userBank.getBalanceCard(), userBank.getSentTransactions(), userBank.getReceivedTransactions());
		} else {
			logger.warn("Error, account with this: " + id + " does not exist, withdrawal cancelled. Try again ...");
			throw new UserNotFoundException(
					"Error, account with this: " + id + " does not exist, withdrawal cancelled.");
		}
	}

	@Override
	public List<BigDecimal> getMathematicalCredit(BigDecimal amountCredit, BigDecimal clientMonth) {

		BigDecimal annualInterestRate = new BigDecimal("44.29"); // Годовая процентная ставка

		// Формула расчета суммы за год 44.29 / 100 * 10000
		BigDecimal resultYear = annualInterestRate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_EVEN);
		BigDecimal algoritmResultYear = resultYear.multiply(amountCredit);
		BigDecimal resultYearFiniched = amountCredit.add(algoritmResultYear);

		// Получаем итоговую сумму за Месяц
		BigDecimal algoritmResultMonth = algoritmResultYear.divide(new BigDecimal("12"), 10, RoundingMode.HALF_EVEN);
		// Получаем итоговую сумму за День
		BigDecimal algoritmResultDay = algoritmResultYear.divide(new BigDecimal("365"), 10, RoundingMode.UP);
		// Получаем итоговую сумму за Указанный срок клиента
		BigDecimal algoritmResultClientMonth = algoritmResultMonth.multiply(clientMonth);

		List<BigDecimal> creditResult = new ArrayList<>();
		creditResult.add(algoritmResultClientMonth);
		creditResult.add(resultYearFiniched);
		creditResult.add(algoritmResultMonth);
		creditResult.add(algoritmResultDay);
//		creditResult.add(annualInterestRate);
//		creditResult.add(algoritmResultYear);

		return creditResult;
	}

	@Override
	public UserBankTransactionResponse transferAccumulativeBalance(BigDecimal amount, int userId) {

		Optional<UserBank> optional = txBankRepository.findById(userId);

		if (optional.isPresent()) {
			UserBank userBank = optional.get();
			BigDecimal userBalanceCard = userBank.getBalanceCard();
			BigDecimal userBalanceAccumulative = userBank.getBalanceAccumulative();

			if (userBalanceCard.compareTo(amount) < 0) {
				logger.warn("Insufficient funds for user with ID " + userId);
				throw new InsufficientFundsException("Insufficient funds for user with ID " + userId);
			}

			try {
				userBank.setBalanceCard(userBalanceCard.subtract(amount));
				userBank.setBalanceAccumulative(userBalanceAccumulative.add(amount));

			} catch (Exception e) {
				logger.warn("Oops...Mistake: ", e);
			}

			return new UserBankTransactionResponse(userBank.getId(), userBank.getBalanceCard(),
					userBank.getBalanceAccumulative());
		} else {
			logger.warn("User with ID {} not found", userId);
			throw new UserNotFoundException("User with ID " + userId + "not found");
		}
	}

	@Override
	public UserBankTransactionResponse getMoneyCredit(int userId, BigDecimal amountCredit, BigDecimal clientMonth) {

		Optional<UserBank> optional = txBankRepository.findById(userId);

		if (optional.isPresent()) {
			UserBank userBank = optional.get();
			BigDecimal userBalanceCredit = userBank.getBalanceCredit();

			try {
				
				if (amountCredit == null || amountCredit.compareTo(BigDecimal.ZERO) <= 0) {
					throw new IllegalArgumentException("Amount to credit must be greater than zero.");
				}

				userBank.setBalanceCredit(userBalanceCredit.add(amountCredit));
				txBankRepository.save(userBank);
				createTransactionsFramework.getCreditMoneyTransaction(amountCredit, userBank);
			} catch (Exception e) {
				logger.error("Failed to credit amount {} for user with ID {} due to an exception.", amountCredit,
						userId, e);
				throw new TransactionMistakeException("Error processing credit for user ID " + userId, e);
			}

			return new UserBankTransactionResponse(userBank.getId(), userBank.getBalanceCredit());

		} else {
			logger.warn("User with ID {} not found", userId);
			throw new UserNotFoundException("User with ID " + userId + "not found");
		}

	}

}
