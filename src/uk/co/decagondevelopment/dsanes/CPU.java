package uk.co.decagondevelopment.dsanes;

public class CPU {
	
	private NesMemory ram = new NesMemory();
	
	private int cycles;
	private int clocks;
	
	//registers
	private int a, x, y, p, s, pc;
	
	
	//status flags
	private boolean carryFlag = false;
	private boolean decimalModeFlag = false;
	private boolean interruptDissableFlag = true;
	private boolean negativeFlag = false;
	private boolean overflowFlag = false;
	private boolean zeroFlag = false;
	
	private static String[] opcodes = opcodes();

	public CPU(NesMemory nesmem) {
		ram = nesmem;
		
	}
	
	public void init() {
		for (int i = 0; i < 0x800; ++i) {
            ram.write(i, 0xFF);
        }

      //  ram.write(0x0008, Integer.parseInt(Utility.toHex(Utility.generateInt(256)))); // 0xf7
      //  ram.write(0x0009, Integer.parseInt(Utility.toHex(Utility.generateInt(256)))); // 0xef
      //  ram.write(0x000A, Integer.parseInt(Utility.toHex(Utility.generateInt(256)))); // 0xdf
     //   ram.write(0x000F, Integer.parseInt(Utility.toHex(Utility.generateInt(256)))); // 0xbf

        for (int i = 0x4000; i <= 0x4017; ++i) { //may need to change to 0x400f
            ram.write(i, 0x00);
        }


        cycles = 0; //TODO: calculate cycles based on vblank

        a = 0;
        x = 0;
        y = 0;
        s = 0;
        pc = 0;
	}
	
	//opcodes
	public static String[] opcodes() {
		String[] opcodes;
		opcodes = new String[0x100];
		
		for(int i = 0; i < 0x100; ++i) {
			opcodes[i] = Utility.toHex(i);
		}
		
        opcodes[0x00] = "BRK";
        opcodes[0x01] = "ORA $(%2%1,x)";
        opcodes[0x02] = "KIL";
        opcodes[0x03] = "SLO $(%2%1,x)";
        opcodes[0x04] = "NOP $%1";
        opcodes[0x05] = "ORA $%1";
        opcodes[0x06] = "ASL $%1";
        opcodes[0x07] = "SLO $%1";
        opcodes[0x08] = "PHP";
        opcodes[0x09] = "ORA #$%1";
        opcodes[0x0A] = "ASL A";
        opcodes[0x0B] = "ANC #$%1";
        opcodes[0x0C] = "NOP $%2%1";
        opcodes[0x0D] = "ORA $%2%1";
        opcodes[0x0E] = "ASL $%2%1";
        opcodes[0x0F] = "SLO $%2%1";
		
		opcodes[0x10] = "BRK";
		opcodes[0x11] = "BRK";
		opcodes[0x12] = "BRK";
		opcodes[0x13] = "BRK";
		opcodes[0x14] = "BRK";
		opcodes[0x15] = "BRK";
		opcodes[0x16] = "BRK";
		opcodes[0x17] = "BRK";
		opcodes[0x18] = "CLC";
		opcodes[0x19] = "BRK";
		opcodes[0x1A] = "NOP"; // done housekeeping
		opcodes[0x1B] = "BRK";
		opcodes[0x1C] = "BRK";
		opcodes[0x1D] = "BRK";
		opcodes[0x1E] = "BRK";
		opcodes[0x1F] = "BRK";
		
		opcodes[0x20] = "JSR $%2$02X%1$02X";
		opcodes[0x21] = "BRK";
		opcodes[0x22] = "BRK";
		opcodes[0x23] = "BRK";
		opcodes[0x24] = "BRK";
		opcodes[0x25] = "BRK";
		opcodes[0x26] = "ROL $%1$02X";
		opcodes[0x27] = "BRK";
		opcodes[0x28] = "BRK";
		opcodes[0x29] = "BRK";
		opcodes[0x2A] = "BRK";
		opcodes[0x2B] = "BRK";
		opcodes[0x2C] = "BRK";
		opcodes[0x2D] = "BRK";
		opcodes[0x2E] = "BRK";
		opcodes[0x2F] = "BRK";
		
		opcodes[0x30] = "BRK";
		opcodes[0x31] = "BRK";
		opcodes[0x32] = "BRK";
		opcodes[0x33] = "BRK";
		opcodes[0x34] = "BRK";
		opcodes[0x35] = "BRK";
		opcodes[0x36] = "BRK";
		opcodes[0x37] = "BRK";
		opcodes[0x38] = "BRK";
		opcodes[0x39] = "BRK";
		opcodes[0x3A] = "BRK";
		opcodes[0x3B] = "BRK";
		opcodes[0x3C] = "BRK";
		opcodes[0x3D] = "BRK";
		opcodes[0x3E] = "BRK";
		opcodes[0x3F] = "BRK";
		
		opcodes[0x40] = "BRK";
		opcodes[0x41] = "BRK";
		opcodes[0x42] = "BRK";
		opcodes[0x43] = "BRK";
		opcodes[0x44] = "BRK";
		opcodes[0x45] = "BRK";
		opcodes[0x46] = "LSR $%1$02X";
		opcodes[0x47] = "BRK";
		opcodes[0x48] = "PHA";
		opcodes[0x49] = "BRK";
		opcodes[0x4A] = "BRK";
		opcodes[0x4B] = "BRK";
		opcodes[0x4C] = "BRK";
		opcodes[0x4D] = "BRK";
		opcodes[0x4E] = "BRK";
		opcodes[0x4F] = "BRK";
		
		opcodes[0x50] = "BRK";
		opcodes[0x51] = "BRK";
		opcodes[0x52] = "BRK";
		opcodes[0x53] = "BRK";
		opcodes[0x54] = "BRK";
		opcodes[0x55] = "BRK";
		opcodes[0x56] = "BRK";
		opcodes[0x57] = "BRK";
		opcodes[0x58] = "BRK";
		opcodes[0x59] = "BRK";
		opcodes[0x5A] = "BRK";
		opcodes[0x5B] = "BRK";
		opcodes[0x5C] = "BRK";
		opcodes[0x5D] = "BRK";
		opcodes[0x5E] = "BRK";
		opcodes[0x5F] = "BRK";
		
		opcodes[0x60] = "BRK";
		opcodes[0x61] = "BRK";
		opcodes[0x62] = "BRK";
		opcodes[0x63] = "BRK";
		opcodes[0x64] = "BRK";
		opcodes[0x65] = "BRK";
		opcodes[0x66] = "ROR $%1$02X";
		opcodes[0x67] = "BRK";
		opcodes[0x68] = "PLA";
		opcodes[0x69] = "ADC #$%1$02X";
		opcodes[0x6A] = "BRK";
		opcodes[0x6B] = "BRK";
		opcodes[0x6C] = "BRK";
		opcodes[0x6D] = "BRK";
		opcodes[0x6E] = "BRK";
		opcodes[0x6F] = "BRK";
		
		opcodes[0x70] = "BRK";
		opcodes[0x71] = "BRK";
		opcodes[0x72] = "BRK";
		opcodes[0x73] = "BRK";
		opcodes[0x74] = "BRK";
		opcodes[0x75] = "BRK";
		opcodes[0x76] = "BRK";
		opcodes[0x77] = "BRK";
		opcodes[0x78] = "BRK";
		opcodes[0x79] = "BRK";
		opcodes[0x7A] = "BRK";
		opcodes[0x7B] = "BRK";
		opcodes[0x7C] = "BRK";
		opcodes[0x7D] = "BRK";
		opcodes[0x7E] = "BRK";
		opcodes[0x7F] = "BRK";
		
		opcodes[0x80] = "BRK";
		opcodes[0x81] = "BRK";
		opcodes[0x82] = "BRK";
		opcodes[0x83] = "BRK";
		opcodes[0x84] = "BRK";
		opcodes[0x85] = "STA $%1$02X";
		opcodes[0x86] = "BRK";
		opcodes[0x87] = "BRK";
		opcodes[0x88] = "BRK";
		opcodes[0x89] = "BRK";
		opcodes[0x8A] = "BRK";
		opcodes[0x8B] = "BRK";
		opcodes[0x8C] = "BRK";
		opcodes[0x8D] = "STA $%2$02X$%1$02X";
		opcodes[0x8E] = "BRK";
		opcodes[0x8F] = "BRK";
		
		opcodes[0x90] = "BRK";
		opcodes[0x91] = "BRK";
		opcodes[0x92] = "BRK";
		opcodes[0x93] = "BRK";
		opcodes[0x94] = "BRK";
		opcodes[0x95] = "BRK";
		opcodes[0x96] = "BRK";
		opcodes[0x97] = "BRK";
		opcodes[0x98] = "BRK";
		opcodes[0x99] = "BRK";
		opcodes[0x9A] = "BRK";
		opcodes[0x9B] = "BRK";
		opcodes[0x9C] = "BRK";
		opcodes[0x9D] = "BRK";
		opcodes[0x9E] = "BRK";
		opcodes[0x9F] = "BRK";
		
		opcodes[0xA0] = "BRK";
		opcodes[0xA1] = "NOP"; // TODO: change to LDA
		opcodes[0xA2] = "BRK";
		opcodes[0xA3] = "BRK";
		opcodes[0xA4] = "BRK";
		opcodes[0xA5] = "LDA $%1$02X";
		opcodes[0xA6] = "BRK";
		opcodes[0xA7] = "BRK";
		opcodes[0xA8] = "BRK";
		opcodes[0xA9] = "LDA #$%1$02X";
		opcodes[0xAA] = "BRK";
		opcodes[0xAB] = "BRK";
		opcodes[0xAC] = "BRK";
		opcodes[0xAD] = "BRK";
		opcodes[0xAE] = "BRK";
		opcodes[0xAF] = "BRK";
		
		opcodes[0xB0] = "BRK";
		opcodes[0xB1] = "BRK";
		opcodes[0xB2] = "BRK";
		opcodes[0xB3] = "BRK";
		opcodes[0xB4] = "BRK";
		opcodes[0xB5] = "BRK";
		opcodes[0xB6] = "BRK";
		opcodes[0xB7] = "BRK";
		opcodes[0xB8] = "BRK";
		opcodes[0xB9] = "BRK";
		opcodes[0xBA] = "BRK";
		opcodes[0xBB] = "BRK";
		opcodes[0xBC] = "BRK";
		opcodes[0xBD] = "BRK";
		opcodes[0xBE] = "BRK";
		opcodes[0xBF] = "BRK";
		
		opcodes[0xC0] = "BRK";
		opcodes[0xC1] = "BRK";
		opcodes[0xC2] = "BRK";
		opcodes[0xC3] = "BRK";
		opcodes[0xC4] = "BRK";
		opcodes[0xC5] = "BRK";
		opcodes[0xC6] = "BRK";
		opcodes[0xC7] = "BRK";
		opcodes[0xC8] = "BRK";
		opcodes[0xC9] = "BRK";
		opcodes[0xCA] = "BRK";
		opcodes[0xCB] = "BRK";
		opcodes[0xCC] = "BRK";
		opcodes[0xCD] = "BRK";
		opcodes[0xCE] = "BRK";
		opcodes[0xCF] = "BRK";
		
		opcodes[0xD0] = "BRK";
		opcodes[0xD1] = "BRK";
		opcodes[0xD2] = "BRK";
		opcodes[0xD3] = "BRK";
		opcodes[0xD4] = "BRK";
		opcodes[0xD5] = "BRK";
		opcodes[0xD6] = "DEC $%1$02X,x";
		opcodes[0xD7] = "BRK";
		opcodes[0xD8] = "BRK";
		opcodes[0xD9] = "BRK";
		opcodes[0xDA] = "BRK";
		opcodes[0xDB] = "BRK";
		opcodes[0xDC] = "BRK";
		opcodes[0xDE] = "BRK";
		opcodes[0xDF] = "BRK";
		
		opcodes[0xE0] = "BRK";
		opcodes[0xE1] = "BRK";
		opcodes[0xE2] = "BRK";
		opcodes[0xE3] = "BRK";
		opcodes[0xE4] = "BRK";
		opcodes[0xE5] = "BRK";
		opcodes[0xE6] = "BRK";
		opcodes[0xE7] = "BRK";
		opcodes[0xE8] = "BRK";
		opcodes[0xE9] = "BRK";
		opcodes[0xEA] = "NOP";
		opcodes[0xEB] = "BRK";
		opcodes[0xEC] = "BRK";
		opcodes[0xED] = "BRK";
		opcodes[0xEE] = "BRK";
		opcodes[0xEF] = "BRK";
		
		opcodes[0xF0] = "BEQ $%3$02X";
		opcodes[0xF1] = "BRK";
		opcodes[0xF2] = "BRK";
		opcodes[0xF3] = "BRK";
		opcodes[0xF4] = "BRK";
		opcodes[0xF5] = "BRK";
		opcodes[0xF6] = "BRK";
		opcodes[0xF7] = "BRK";
		opcodes[0xF8] = "BRK";
		opcodes[0xF9] = "BRK";
		opcodes[0xFA] = "BRK";
		opcodes[0xFB] = "BRK";
		opcodes[0xFC] = "BRK";
		opcodes[0xFD] = "BRK";
		opcodes[0xFE] = "BRK";
		opcodes[0xFF] = "BRK";
		
		return opcodes;
		
	}
	
	//setters
	
	public void setPC(int val) {
		pc = val & 0xffff;
		//TODO: deal with idle and delays etc;
	}
	
	public void setClocks(int val) {
		this.clocks = val;
	}
	


	
	
	//getters
	
	public int getClocks() {
		return this.clocks;
	}
	
	public void run(int[] inst) {
		for (int i = 0; i < inst.length; i++) {
			switch(inst[i]) {
			case 0x78: // SEI imm
				sei(imm());
				System.out.println("SEI at " + (String.format("0x%04X", i)));
				break;
			case 0xD8:
				cld();
				System.out.println("CLD at " + (String.format("0x%04X", i)));	
				break;
			case 0xA9:
				lda(imm());
				System.out.println("LDA at " + (String.format("0x%04X", i)));	
				break;
			case 0xCA: // dec on x and 
				System.out.println("DEX at " + (String.format("0x%04X", i)));		
				dex();
				break;
			}	
		}
		
	}
	
	private void sei(int addr) {
		//TODO: implement delayInterrupt() method
		interruptDissableFlag = true;
	}
	
	private void cld() {
		decimalModeFlag = false;
	}
	
	private void lda(int addr) {
		//TODO implement method
	}
	
	private void dex() {
		x--;
		x &= 0xFF;
		setFlags(x);
	}
	
	private int imm() { //TODO: complete method
		return 0;
	}
	
	private void setFlags(int flag) {
		// TODO: implement setFlags method
	}
	
	
	
}
