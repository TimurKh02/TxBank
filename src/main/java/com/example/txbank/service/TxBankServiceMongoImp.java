package com.example.txbank.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.txbank.dao.TemporaryUserBaseBankRepository;
import com.example.txbank.dto.UserBankInfoMongoDbResponse;
import com.example.txbank.entity.TemporaryUserBaseBank;

@Service
public class TxBankServiceMongoImp implements TxBankServiceMongo {

	@Autowired
	private TemporaryUserBaseBankRepository temporaryUserBaseBankRepository;
	private static final Logger logger = LoggerFactory.getLogger(TxBankServiceMongoImp.class);

	@Override
	public TemporaryUserBaseBank saveUsersMongo(String login, String password, String name) {

		try {
			TemporaryUserBaseBank users = new TemporaryUserBaseBank();
			String hashedPassword = new BCryptPasswordEncoder().encode(password);
			users.setLogin(login);
			users.setPassword(hashedPassword);
			users.setName(name);
			temporaryUserBaseBankRepository.save(users);
			return users;
		} catch (Exception e) {
			logger.error("Failed to save user", e);
			throw new RuntimeException("Failed to save user", e);
		}
	}

	@Override
	public UserBankInfoMongoDbResponse getUserMongo(String id) {

		Optional<TemporaryUserBaseBank> optional = temporaryUserBaseBankRepository.findById(id);

		if (optional.isPresent()) {
			TemporaryUserBaseBank user = optional.get();
			return new UserBankInfoMongoDbResponse(user.getLogin(), user.getPassword(), user.getName());
		} else {
			logger.error("User not found with id: " + id);
			throw new RuntimeException("User not found with id: " + id);
		}
	}

	@Override
	public UserBankInfoMongoDbResponse getUserMongoId(String login) {
		
		Optional<TemporaryUserBaseBank> optional = temporaryUserBaseBankRepository.findByLogin(login);
		
		if(optional.isPresent()) {
			TemporaryUserBaseBank user = optional.get();
			return new UserBankInfoMongoDbResponse(user.getId());
		} else {
			logger.error("User not found with login: " + login);
			throw new RuntimeException("User not found with login: " + login);
		}
	}

	@Override
	public UserBankInfoMongoDbResponse getUserMongoByLogin(String login) {
		
		Optional<TemporaryUserBaseBank> optional = temporaryUserBaseBankRepository.findByLogin(login);
		
		if(optional.isPresent()) {
			TemporaryUserBaseBank user = optional.get();
			return new UserBankInfoMongoDbResponse(user.getLogin(),user.getPassword(),user.getName());
		} else {
			logger.error("User not found with login: " + login);
			throw new RuntimeException("User not found with login: " + login);
		}
	}

}
