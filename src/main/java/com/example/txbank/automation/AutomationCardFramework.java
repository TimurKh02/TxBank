package com.example.txbank.automation;

import java.util.concurrent.ThreadLocalRandom;

public class AutomationCardFramework {

	public final long generationCardNumber() {

		long min = 4400000000000000L;
		long max = 4499999999999999L;
		long cardN = ThreadLocalRandom.current().nextLong(min, max + 1);
		long numberCard = cardN;
		
		return numberCard;
	}

}
