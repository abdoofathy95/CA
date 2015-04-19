package components;

import java.util.Hashtable;

/* refer to this site to understand format 
 * http://www.cs.umd.edu/class/sum2003/cmsc311/Notes/Mips/format.html
 * here fetch currentPC from fetchStage (access using InstructionFetchStage.currentPC;
 */
public class InstructionDecodeStage {
	// generate control signals here according to method

	// will be holding signals generated according to current instruction
	public static Hashtable<String, String> tempRegisterWB = new Hashtable<String, String>();
	// will be holding signals generated according to current instruction
	public static Hashtable<String, String> tempRegisterM = new Hashtable<String, String>();
	// will be holding signals generated according to current instruction
	public static Hashtable<String, String> tempRegisterEx = new Hashtable<String, String>();
	// will be holding next pc value
	public static String currentPC;
	// it's calculated here in this class
	public static String jumpAddress;
	// will hold instuction values (it's private since it won't be called from
	// next stage (only used in the current class)
	private static Object[] currentInstruction;
	// will be holding content of register s (source register)
	public static String registerOneData;
	// will be holding content of register t (register after source)
	public static String registerTwoData;
	// will be holding immediate value if instruction is i-format, else null
	public static String immediateValue;
	// will be holding address of register t if any, else null
	public static String registerTAddress;
	// will be holding address of register d (destination register if any), else
	// null . ex d can hold $t1 , $t2 or whatever (must be address or null)
	public static String registerDAddress;

	/*
	 * public static void init() { called from previous stage currentPC =
	 * InstructionFetchStage.currentPC; currentInstruction =
	 * InstructionFetchStage.currentInstruction; }
	 */
	/*
	 * public static void startNextStage() { calls next stage (call it when
	 * done) ExecutionStage.init(); }
	 */

	public static void main(String[] args) {
		RegisterFile.initRegistersWithAddresses();
		RegisterFile.initRegistersWithZeros();
		InstructionFetchStage.currentPC = 0;
		InstructionFetchStage.currentInstruction = new Object[] { "add", "t1",
				"t2", "t3" };
		InstructionDecodeStage.ExecuteStage();
		// InstructionFetchStage.ExecuteStage();
	}

	public static void ExecuteStage() {
		currentPC = Integer.toBinaryString(InstructionFetchStage.currentPC);
		while (currentPC.length() < 32) {
			currentPC = "0" + currentPC;
		}
		currentInstruction = InstructionFetchStage.currentInstruction;
		decodeInstruction(InstructionFetchStage.currentInstruction);
	}

	public static void decodeInstruction(Object[] instruction) {
		// fill temporary registers here

		String insName = (String) instruction[0];
		switch (insName) {
		case "add": {
			storeControlSignals("1", "0", "0", "0", "0", "Add", "0", "0", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[3]));// destination
			instructionBinary += "00000";
			instructionBinary += "100000";
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "sub": {
			storeControlSignals("1", "0", "0", "0", "0", "Sub", "0", "0", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[3]));// destination
			instructionBinary += "00000";
			instructionBinary += "100010";
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "and": {
			storeControlSignals("1", "0", "0", "0", "0", "AND", "0", "0", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[3]));// destination
			instructionBinary += "00000";
			instructionBinary += "100100";
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "lw": {
			storeControlSignals("0", "0", "0", "1", "1", "Add", "0", "1", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "100011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			String signExtened = Integer.toBinaryString((int) instruction[3]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "sw": {
			storeControlSignals("x", "0", "0", "0", "x", "Add", "1", "1", "0",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			String signExtened = Integer.toBinaryString((int) instruction[3]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "beq": {
			storeControlSignals("x", "1", "0", "0", "x", "Sub", "0", "0", "0",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			String signExtened = Integer.toBinaryString((int) instruction[3]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "addi": {
			storeControlSignals("0", "0", "0", "0", "0", "Add", "0", "1", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			String signExtened = Integer.toBinaryString((int) instruction[3]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "lb": {
			storeControlSignals("0", "0", "0", "1", "1", "Add", "0", "1", "1",
					"0", "0", "0", "1", "0", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			String signExtened = Integer.toBinaryString((int) instruction[3]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend

			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "lbu": {
			storeControlSignals("0", "0", "0", "1", "1", "Add", "0", "1", "1",
					"0", "0", "0", "0", "1", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			String signExtened = Integer.toBinaryString((int) instruction[3]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend

			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "sb": {
			storeControlSignals("x", "0", "0", "0", "x", "Add", "1", "1", "0",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "101000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			String signExtened = Integer.toBinaryString((int) instruction[3]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "lui": {
			storeControlSignals("0", "0", "0", "0", "0", "DC", "0", "1", "1",
					"0", "0", "0", "0", "0", "1");
			String instructionBinary = "001111";
			instructionBinary += "00000";// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// target
			String signExtened = Integer.toBinaryString((int) instruction[2]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = "0x00000000";
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[1]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "sll": {
			storeControlSignals("1", "0", "0", "0", "0", "SLL", "0", "0", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[3]));// destination
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[4]));// shamt
			instructionBinary += "000000";

			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "srl": {
			storeControlSignals("1", "0", "0", "0", "0", "SRL", "0", "0", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[3]));// destination
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[4]));// shamt
			instructionBinary += "000010";

			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "nor": {
			storeControlSignals("1", "0", "0", "0", "0", "NOR", "0", "0", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[3]));// destination
			instructionBinary += "00000";
			instructionBinary += "100111";
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "bne": {
			storeControlSignals("x", "0", "1", "0", "x", "Sub", "0", "0", "0",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "000101";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			String signExtened = Integer.toBinaryString((int) instruction[3]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "j": {
			storeControlSignals("x", "x", "x", "0", "0", "DC", "0", "x", "0",
					"1", "0", "0", "0", "0", "0");
			String instructionBinary = "000010";
			String signExtened = Integer.toBinaryString((int) instruction[1]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(6, 32);

			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = "0x00000000";
			registerTwoData = "0x00000000";
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "jal": {
			storeControlSignals("x", "x", "x", "0", "0", "DC", "0", "x", "1",
					"1", "0", "1", "0", "0", "0");
			String instructionBinary = "000011";
			String signExtened = Integer.toBinaryString((int) instruction[1]);
			if (signExtened.length() < 32) {
				for (int i = 0; i < 32 - signExtened.length(); i++) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(6, 32);

			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = "0x00000000";
			registerTwoData = "0x00000000";
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "jr": {
			storeControlSignals("x", "x", "x", "0", "0", "JR", "0", "x", "0",
					"0", "1", "0", "0", "0", "0");
			String instructionBinary = "000000";
			String signExtened = Integer.toBinaryString((int) instruction[1]);// source
			instructionBinary += "0000000000000000010000";
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = "0x00000000";
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "slt": {
			storeControlSignals("1", "0", "0", "0", "0", "SLT", "0", "0", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[3]));// destination
			instructionBinary += "00000";
			instructionBinary += "101010";

			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		case "sltu": {
			storeControlSignals("1", "0", "0", "0", "0", "SLTU", "0", "0", "1",
					"0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[1]));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[2]));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction[3]));// destination
			instructionBinary += "00000";
			instructionBinary += "101011";
			immediateValue = instructionBinary.substring(16, 32);
			registerTAddress = instructionBinary.substring(11, 16);
			registerDAddress = instructionBinary.substring(17, 22);
			registerOneData = RegisterFile
					.readRegister1((String) instruction[1]);
			registerTwoData = RegisterFile
					.readRegister2((String) instruction[2]);
			jumpAddress = currentPC.substring(0, 4)
					+ instructionBinary.substring(6) + "00";
			break;
		}
		}

	}

	private static void storeControlSignals(String RegDest, String Branch,
			String BNE, String memRead, String memToReg, String AluOp,
			String MemWrite, String AluSrc, String RegWrite, String Jump,
			String JumpR, String JALEX, String SignExtended,
			String ZeroExtended, String ShiftLeft) {

		tempRegisterWB.put("RegWrite", RegWrite);
		tempRegisterWB.put("MemToReg", memToReg);
		tempRegisterWB.put("Sign-Extended", SignExtended);
		tempRegisterWB.put("Zero-Extended", ZeroExtended);
		tempRegisterWB.put("JALEX", JALEX);

		tempRegisterM.put("memRead", memRead);
		tempRegisterM.put("MemWrite", MemWrite);
		tempRegisterM.put("Branch", Branch);
		tempRegisterM.put("BNE", BNE);
		tempRegisterM.put("Jump", Jump);
		tempRegisterM.put("JumpR", JumpR);

		tempRegisterEx.put("AluSrc", AluSrc);
		tempRegisterEx.put("AluOp", AluOp);
		tempRegisterEx.put("RegDest", RegDest);
		tempRegisterEx.put("Shift-Left", ShiftLeft);
		tempRegisterEx.put("JALWB", JALEX);// JALWB = JALEX
	}

	static String hexToBinary(String hex) {
		int i = Integer.parseInt(hex.substring(2), 16);
		String bin = Integer.toBinaryString(i);
		while (bin.length() < 5) {
			bin = "0" + bin;
		}
		return bin;
	}
}