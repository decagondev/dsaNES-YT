package uk.co.decagondevelopment.dsanes;

public class CPU {
	
	private NesMemory ram;
	
	private int cycles;
	private int clocks;
	
	//registers
	private int a, x, y, p, s, pc;
	
	
	//status flags
	private boolean carryFlag = false;
	private boolean decimalMode = false;
	private boolean interruptDissable = true;
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

        ram.write(0x0008, Integer.parseInt(Utility.toHex(Utility.generateInt(256)))); // 0xf7
        ram.write(0x0009, Integer.parseInt(Utility.toHex(Utility.generateInt(256)))); // 0xef
        ram.write(0x000A, Integer.parseInt(Utility.toHex(Utility.generateInt(256)))); // 0xdf
        ram.write(0x000F, Integer.parseInt(Utility.toHex(Utility.generateInt(256)))); // 0xbf

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
		
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		opcodes[0x00] = "BRK";
		
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
	
	
}
