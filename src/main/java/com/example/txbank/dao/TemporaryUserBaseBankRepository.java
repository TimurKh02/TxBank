package com.example.txbank.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.txbank.entity.TemporaryUserBaseBank;

@Repository
public interface TemporaryUserBaseBankRepository extends MongoRepository<TemporaryUserBaseBank, String> {
	@Query("{ 'login' : ?0 }")
	Optional<TemporaryUserBaseBank> findByLogin(String login);
}
