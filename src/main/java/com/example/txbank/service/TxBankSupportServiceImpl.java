package com.example.txbank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.txbank.dao.TxBankSupportMessageRepository;
import com.example.txbank.dto.SupportMessageResponse;
import com.example.txbank.entity.SupportMessage;
import com.example.txbank.enums.StatusMessageSupport;

@Service
public class TxBankSupportServiceImpl implements TxBankSupportService{
	
	@Autowired
	private TxBankSupportMessageRepository txBankSupportMessageRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(TxBankServiceImpl.class);

	@Override
	public SupportMessageResponse saveSupportMessage(String userEmail, String userMessage) {
		
		SupportMessage supportMessage = null;
		
		try {
			supportMessage = new SupportMessage();	
			supportMessage.setUserEmail(userEmail);
			supportMessage.setUserMessage(userMessage);
			supportMessage.setStatus(StatusMessageSupport.NOT_VERIFIED.name());
			txBankSupportMessageRepository.save(supportMessage);
			
			logger.info("Your application has been successfully sent MESSAGE");
			
		} catch (Exception e){
			logger.warn("Message for support can't save, mistake: " + e);
			return new SupportMessageResponse("Error", "Error", "Error");
		}
		return new SupportMessageResponse(supportMessage.getUserEmail(), supportMessage.getUserMessage(), supportMessage.getStatus());
		
	}

}
