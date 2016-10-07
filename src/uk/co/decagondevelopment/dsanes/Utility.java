package uk.co.decagondevelopment.dsanes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
	
	public static int[] readRom(final String path) {
		
		File rom = new File(path);
		byte[] rom_buf = new byte[(int) rom.length()];
		FileInputStream stream;
		try {
			stream = new FileInputStream(rom);
			stream.read(rom_buf);
		} catch (IOException e) {
			System.err.println("Failed to open file");;
			e.printStackTrace();
		}
		int[] instructions = new int[rom_buf.length];
		
		for (int i = 0; i < rom_buf.length; i++) {
			instructions[i] = (short) (rom_buf[i] & 0xFF);
		}
		
		return instructions;
	}

}
