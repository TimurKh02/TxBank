package com.example.txbank.service;

import com.example.txbank.dto.UserBankInfoMongoDbResponse;
import com.example.txbank.entity.TemporaryUserBaseBank;

public interface TxBankServiceMongo {
	
	public TemporaryUserBaseBank saveUsersMongo(String login, String password, String name);
	
	public UserBankInfoMongoDbResponse getUserMongo(String id);
	
	public UserBankInfoMongoDbResponse getUserMongoId(String login);
	
	public UserBankInfoMongoDbResponse getUserMongoByLogin(String login);
}
