package com.example.txbank.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.txbank.entity.UserTransactionHistory;

@Repository
public interface TxTransactionHistoryRepository extends JpaRepository<UserTransactionHistory, Integer> {

	@Query("SELECT th FROM UserTransactionHistory th " +
			"WHERE th.transactionDate = :transactionDate "+ 
			"AND th.bankTransactionInfo = :bankTransactionInfo")
	List<UserTransactionHistory> findTransactionsInfo(
			@Param("transactionDate") LocalDateTime transactionDate,
			@Param("bankTransactionInfo") String bankTransactionInfo);
}
