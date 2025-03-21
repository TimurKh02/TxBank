package com.example.txbank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.txbank.entity.UserBank;

@Repository
public interface TxBankRepository extends JpaRepository<UserBank, Integer>{
	Optional<UserBank> findByLogin (String login);
	Optional<UserBank> findByBankCard (Long bankCard);
}
