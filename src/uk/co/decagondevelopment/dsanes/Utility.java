package uk.co.decagondevelopment.dsanes;

import java.util.Locale;
import java.util.Random;

public class Utility {
	
	// setup random generator
	static Random randomGenerator = new Random();
	
	
	// tohex methods
	
	public static String toHex(int number) {
		return Integer.toHexString(number).toUpperCase(Locale.UK);
	}
	
	public static String toHex(long number) {
		return Long.toHexString(number).toUpperCase(Locale.UK);
	}
	
	
	// Generation methods
	
	public static int generateInt(int range) {
		return randomGenerator.nextInt(range + 1);
	}
	
	public static long generateLong(long startRange, long endRange) {
		return startRange + (long)(randomGenerator.nextDouble() * (endRange - startRange));		
	}

}
