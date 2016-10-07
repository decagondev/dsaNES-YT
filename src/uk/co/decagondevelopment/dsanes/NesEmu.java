package uk.co.decagondevelopment.dsanes;

public class NesEmu {

	public static void main(String args[]) {
		NesMemory wRam = new NesMemory();
		
		CPU cpu0 = new CPU(wRam);
		
		int[] code = Utility.readRom("c://nestest.nes");
		cpu0.init();
		cpu0.run(code);
		
		
	}
}
