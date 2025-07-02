package com.example.txbank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.txbank.automation.AutomationCardFramework;
import com.example.txbank.automation.RandomCodeGenerator;

public class TxBankAutomationTests {

	@Test
	public void automationCardFrameworkShouldLenght() {

		AutomationCardFramework varCard = new AutomationCardFramework();
		long var = varCard.generationCardNumber();
		long varLength = String.valueOf(var).length();

		Assertions.assertEquals(16, varLength, "The length should be 16");

	}

	@Test
	public void generateCodeRegistration() {

		RandomCodeGenerator randomCodeGenerator = new RandomCodeGenerator();
		String valueCode = randomCodeGenerator.generateCodeRegistration();

		Assertions.assertEquals(8, valueCode.length());
		Assertions.assertTrue(valueCode.matches("^[A-Za-z0-9+/]{8}$"));
	}
	
	

}
