package com.example.txbank.service;

import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.txbank.automation.RandomCodeGenerator;
import com.example.txbank.dao.TxBankCodeRegisterRepository;
import com.example.txbank.dao.TxBankSupportMessageRepository;
import com.example.txbank.dto.SupportMessageResponse;
import com.example.txbank.entity.CodeRegisterEmail;
import com.example.txbank.entity.SupportMessage;
import com.example.txbank.enums.StatusMessageSupport;

@Service
public class TxBankSupportServiceImpl implements TxBankSupportService {

	@Value("${mail.username}")
	private String loginBank;
	@Value("${mail.password}")
	private String passwordBank;
	@Autowired
	private TxBankSupportMessageRepository txBankSupportMessageRepository;
	private RandomCodeGenerator randomCodeGenerator;
	@Autowired
	private TxBankCodeRegisterRepository txBankCodeRegisterRepository;
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

		} catch (Exception e) {
			logger.warn("Message for support can't save, mistake: " + e);
			return new SupportMessageResponse("Error", "Error", "Error");
		}
		return new SupportMessageResponse(supportMessage.getUserEmail(), supportMessage.getUserMessage(),
				supportMessage.getStatus());

	}

	@Override
	public void sendEmailCode(String emailRegister) {

		String code = randomCodeGenerator.generateCodeRegistration();
		LocalDateTime codeRegisterTime = LocalDateTime.now().plusMinutes(10);

		try {
			CodeRegisterEmail codeRegisterEmail = new CodeRegisterEmail();
			codeRegisterEmail.setCodeEmailGenerate(code);
			codeRegisterEmail.setCodeTime(codeRegisterTime);
			codeRegisterEmail.setEmailUsers(emailRegister);
			codeRegisterEmail.setVerifiedStatus(false);
			txBankCodeRegisterRepository.save(codeRegisterEmail);
		} catch (Exception e) {
			logger.error("Error save data-bases information.. (-method: sendEmailCode-)");
			throw new RuntimeException("Error save data-bases information..");
		}

		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(loginBank, passwordBank);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ваш_email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRegister));
			message.setSubject("Подтверждение почты");
			message.setText("Ваш код подтверждения: " + code);
			Transport.send(message);
		} catch (MessagingException e) {
			logger.error("Error sending email (-Register method-)" + e);
			throw new RuntimeException("Error sending email", e);
		}
	}

}
