package com.example.txbank.service;

import com.example.txbank.dto.SupportMessageResponse;

public interface TxBankSupportService {
	
	public SupportMessageResponse saveSupportMessage(String userEmail, String userMessage);
	
	public void sendEmailCode(String emailRegister);

}
