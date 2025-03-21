package com.example.txbank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.txbank.entity.SupportMessage;

@Repository
public interface TxBankSupportMessageRepository extends JpaRepository<SupportMessage, Integer> {

	Optional<SupportMessage> findByUserEmail(String userEmail);

}
