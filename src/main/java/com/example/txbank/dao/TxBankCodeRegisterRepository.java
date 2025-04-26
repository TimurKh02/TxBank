package com.example.txbank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.txbank.entity.CodeRegisterEmail;

public interface TxBankCodeRegisterRepository extends JpaRepository <CodeRegisterEmail, Integer> {

}
