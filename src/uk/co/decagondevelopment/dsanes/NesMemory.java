package uk.co.decagondevelopment.dsanes;

import java.util.Arrays;

public class NesMemory {

	private int[] wRam = new int[2048];
	private Mapper mapper;
	private APU apu;
	private PPU ppu;
	
	
	public NesMemory(Mapper mapper) {
		this.mapper = mapper;
		Arrays.fill(wRam, 0xff);
	}


	public void write(int addr, int val) {
		// TODO: Create this method
		
	}


	public int read(int addr) {
		// TODO: create this method
		return 0;
	}
}
