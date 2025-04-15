package com.example.txbank.automation;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomCodeGenerator {
	
	public String generateCodeRegistration() {

		SecureRandom secureRandom = new SecureRandom();
		byte[] randomBytes = new byte[6];
		secureRandom.nextBytes(randomBytes);
		
		return Base64.getEncoder().encodeToString(randomBytes);
	}
}
