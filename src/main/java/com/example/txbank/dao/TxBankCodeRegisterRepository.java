package com.example.txbank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.txbank.entity.CodeRegisterEmail;

@Repository
public interface TxBankCodeRegisterRepository extends JpaRepository <CodeRegisterEmail, Integer> {

}
