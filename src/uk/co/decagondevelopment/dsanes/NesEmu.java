package uk.co.decagondevelopment.dsanes;

public class NesEmu {

	public static void main(String args[]) {
		//NesUnit unit0 = new NesUnit();
		// String path = args[1];
		int[] romData = Utility.readRom("c://nestest.nes");
		for (int i = 0; i < romData.length; i++) {
			System.out.println(Utility.toHex(romData[i]));
		}
		
	}
}
