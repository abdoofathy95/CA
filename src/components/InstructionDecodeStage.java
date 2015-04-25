package components;

import java.util.Enumeration;
import java.util.Hashtable;

/* refer to this site to understand format 
 * http://www.cs.umd.edu/class/sum2003/cmsc311/Notes/Mips/format.html
 * here fetch currentPC from fetchStage (access using InstructionFetchStage.currentPC;
 */
public class InstructionDecodeStage {
	// generate control signals here according to method
	public static boolean execute = false;
	// will be holding signals generated according to current instruction
	public static Hashtable<String, String> tempRegisterWB = new Hashtable<String, String>();
	// will be holding signals generated according to current instruction
	public static Hashtable<String, String> tempRegisterM = new Hashtable<String, String>();
	// will be holding signals generated according to current instruction
	public static Hashtable<String, String> tempRegisterEx = new Hashtable<String, String>();
	// will be holding next pc value
	public static String currentPC;
	public static String instruction;
	// it's calculated here in this class
	public static String jumpAddress;
	// will hold instuction values (it's private since it won't be called from
	// next stage (only used in the current class)
	private static Instruction currentInstruction;
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
	/*
	 * public static void main(String[] args) {
	 * RegisterFile.initRegistersWithAddresses();
	 * RegisterFile.initRegistersWithZeros(); InstructionFetchStage.currentPC =
	 * 1; InstructionFetchStage.currentInstruction = new Instruction("kareem",
	 * "sll", "t2", null, "t1", null, "7", null);
	 * InstructionDecodeStage.ExecuteStage(); //
	 * InstructionFetchStage.ExecuteStage(); printStage(); }
	 */
	public static void executeStage() {
		tempRegisterWB = new Hashtable<String, String>();
		tempRegisterM = new Hashtable<String, String>();
		tempRegisterEx = new Hashtable<String, String>();

		if (execute) {
			ExecutionStage.execute = true;
			currentPC = Integer.toBinaryString(InstructionFetchStage.currentPC);
			while (currentPC.length() < 32) {
				currentPC = "0" + currentPC;
			}
			currentInstruction = InstructionFetchStage.currentInstruction;
			decodeInstruction(InstructionFetchStage.currentInstruction);
			printStage();
			System.out.println("InstructionDecodeStage");
		} else {
			ExecutionStage.execute = false;
		}
		// ExecutionStage.init();
	}

	private static void printStage() {
		// TODO Auto-generated method stub
		// printing values
		// --------------------------------------------------------------------
		System.out.println("-------------------------------------");
		System.out.println("WB REGISTER :");
		Enumeration e = tempRegisterWB.keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println(key + " : " + tempRegisterWB.get(key));
		}
		System.out.println("");
		System.out.println("M REGISTER :");
		e = tempRegisterM.keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println(key + " : " + tempRegisterM.get(key));
		}
		System.out.println("");
		System.out.println("EX REGISTER :");
		e = tempRegisterEx.keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println(key + " : " + tempRegisterEx.get(key));
		}
		System.out.println("");

		System.out.println("currentPC" + " : " + currentPC);
		System.out.println("jumpAddress" + " : " + jumpAddress);
		System.out.println("currentInstruction" + " : "
				+ currentInstruction.getInstructionName());
		System.out.println("registerOneData" + " : " + registerOneData);
		System.out.println("registerTwoData" + " : " + registerTwoData);
		System.out.println("immediateValue" + " : " + immediateValue);
		System.out.println("registerTAddress" + " : " + registerTAddress);
		System.out.println("registerDAddress" + " : " + registerDAddress);
		System.out.println("instruction" + " : " + instruction);
		System.out.println("-------------------------------------");
		// --------------------------------------------------------------------
	}

	private static void stageOutput(String binInstruction,
			String registerOneData1, String registerTwoData1) {
		// TODO Auto-generated method stub

		immediateValue = binInstruction.substring(16, 32);
		if (immediateValue.length() < 32) {
			while (immediateValue.length() < 32) {
				immediateValue = immediateValue.charAt(0) + immediateValue;
			}
		}

		registerTAddress = binInstruction.substring(11, 16);
		registerDAddress = binInstruction.substring(16, 21);
		jumpAddress = currentPC.substring(0, 4) + binInstruction.substring(6)
				+ "00";
		registerOneData = registerOneData1;
		registerTwoData = registerTwoData1;
		instruction = binInstruction;
	}

	public static void decodeInstruction(Instruction instruction) {
		// fill temporary registers here

		String insName = (String) instruction.getInstructionName();
		switch (insName) {
		case "add": {
			storeControlSignals("1", "0", "0", "0", "0", "Add", "0", "0", "1",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRd()));// destination
			instructionBinary += "00000";
			instructionBinary += "100000";

			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}

		case "sub": {
			storeControlSignals("1", "0", "0", "0", "0", "Sub", "0", "0", "1",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRd()));// destination
			instructionBinary += "00000";
			instructionBinary += "100010";
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "and": {
			storeControlSignals("1", "0", "0", "0", "0", "AND", "0", "0", "1",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRd()));// destination
			instructionBinary += "00000";
			instructionBinary += "100100";
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "lw": {
			storeControlSignals("0", "0", "0", "1", "1", "Add", "0", "1", "1",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "100011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// target
			String signExtened = Integer.toBinaryString((int) Integer
					.parseInt(instruction.getOffset()));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "sw": {
			storeControlSignals("0", "0", "0", "0", "0", "Add", "1", "1", "0",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			String signExtened = Integer.toBinaryString((int) Integer
					.parseInt(instruction.getOffset()));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "addi": {
			storeControlSignals("0", "0", "0", "0", "0", "Add", "0", "1", "1",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			String signExtened = Integer.toBinaryString((int) Integer
					.parseInt(instruction.getConstant()));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "lb": {
			storeControlSignals("0", "0", "0", "1", "1", "Add", "0", "1", "1",
					"0", "0", "0", "1", "0", "0", "0", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			String signExtened = Integer.toBinaryString((int) Integer
					.parseInt(instruction.getOffset()));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "lbu": {
			storeControlSignals("0", "0", "0", "1", "1", "Add", "0", "1", "1",
					"0", "0", "0", "0", "1", "0", "0", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			String signExtened = Integer.toBinaryString((int) Integer
					.parseInt(instruction.getOffset()));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);

			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "sb": {
			storeControlSignals("0", "0", "0", "0", "0", "Add", "1", "1", "0",
					"0", "0", "0", "0", "0", "1", "0", "0");
			String instructionBinary = "101000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			String signExtened = Integer.toBinaryString((int) Integer
					.parseInt(instruction.getOffset()));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);

			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "lui": {
			storeControlSignals("0", "0", "0", "0", "0", "Add", "0", "1", "1",
					"0", "0", "0", "0", "0", "0", "1", "0");
			String instructionBinary = "001111";
			instructionBinary += "00000";// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			String signExtened = Integer.toBinaryString((int) Integer
					.parseInt(instruction.getConstant()));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			stageOutput(instructionBinary, "00000000000000000000000000000000",
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "sll": {
			storeControlSignals("1", "0", "0", "0", "0", "SLL", "0", "1", "1",
					"0", "0", "0", "0", "0", "0", "0", "1");
			String instructionBinary = "000000";
			instructionBinary += "00000";// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRd()));// destination
			String signExtened = Integer.toBinaryString((int) Integer
					.parseInt(instruction.getConstant()));
			if (signExtened.length() < 5) {
				while (5 - signExtened.length() != 0) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened;
			instructionBinary += "000000";

			stageOutput(instructionBinary, "00000000000000000000000000000000",
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "srl": {
			storeControlSignals("1", "0", "0", "0", "0", "SRL", "0", "1", "1",
					"0", "0", "0", "0", "0", "0", "0", "1");
			String instructionBinary = "000000";
			instructionBinary += "00000";// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRd()));// destination
			String signExtened = Integer.toBinaryString((int) Integer
					.parseInt(instruction.getConstant()));
			if (signExtened.length() < 5) {
				while (5 - signExtened.length() != 0) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened;
			instructionBinary += "000010";

			stageOutput(instructionBinary, "00000000000000000000000000000000",
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "nor": {
			storeControlSignals("1", "0", "0", "0", "0", "NOR", "0", "0", "1",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRd()));// destination
			instructionBinary += "00000";
			instructionBinary += "100111";
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}

		case "beq": {
			storeControlSignals("0", "1", "0", "0", "0", "Sub", "0", "0", "0",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "101011";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			String signExtened = Integer
					.toBinaryString((int) InstructionFetchStage
							.getLabelIndex(instruction.getJumpLabel())
							- InstructionFetchStage
									.getInstructionIndex(instruction));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "bne": {
			storeControlSignals("0", "0", "1", "0", "0", "Sub", "0", "0", "0",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "000101";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			String signExtened = Integer
					.toBinaryString((int) InstructionFetchStage
							.getLabelIndex(instruction.getJumpLabel())
							- InstructionFetchStage
									.getInstructionIndex(instruction));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(16, 32);
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "j": {
			storeControlSignals("0", "0", "0", "0", "0", "Add", "0", "0", "0",
					"1", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "000010";
			String signExtened = Integer
					.toBinaryString((int) InstructionFetchStage
							.getLabelIndex(instruction.getJumpLabel()));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(6, 32);
			stageOutput(instructionBinary, "00000000000000000000000000000000",
					"00000000000000000000000000000000");
			break;
		}
		case "jal": {
			storeControlSignals("0", "0", "0", "0", "0", "Add", "0", "0", "1",
					"1", "0", "1", "0", "0", "0", "0", "0");
			String instructionBinary = "000011";
			String signExtened = Integer
					.toBinaryString((int) InstructionFetchStage
							.getLabelIndex(instruction.getJumpLabel()));
			if (signExtened.length() < 32) {
				while (signExtened.length() < 32) {
					signExtened = "0" + signExtened;
				}
			}// sign extend
			instructionBinary += signExtened.substring(6, 32);

			stageOutput(instructionBinary, "00000000000000000000000000000000",
					"00000000000000000000000000000000");
			break;
		}
		case "jr": {
			storeControlSignals("0", "0", "0", "0", "0", "JR", "0", "0", "0",
					"0", "1", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary = hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += "0000000000000000010000";
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					"00000000000000000000000000000000");
			break;
		}

		case "slt": {
			storeControlSignals("1", "0", "0", "0", "0", "SLT", "0", "0", "1",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRd()));// destination
			instructionBinary += "00000";
			instructionBinary += "101010";

			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		case "sltu": {
			storeControlSignals("1", "0", "0", "0", "0", "SLTU", "0", "0", "1",
					"0", "0", "0", "0", "0", "0", "0", "0");
			String instructionBinary = "000000";
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRs()));// source
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRt()));// target
			instructionBinary += hexToBinary(RegisterFile.registersAddress
					.get(instruction.getRd()));// destination
			instructionBinary += "00000";
			instructionBinary += "101011";
			stageOutput(instructionBinary,
					RegisterFile.readRegisterWithItsName(instruction.getRs()),
					RegisterFile.readRegisterWithItsName(instruction.getRt()));
			break;
		}
		}
		// immediateValue=signExtendData(immediateValue);
	}

	private static void storeControlSignals(String RegDest, String Branch,
			String BNE, String memRead, String memToReg, String AluOp,
			String MemWrite, String AluSrc, String RegWrite, String Jump,
			String JumpR, String JALEX, String SignExtended,
			String ZeroExtended, String SB, String ShiftLeft, String Shift) {

		tempRegisterWB.put("RegWrite", RegWrite);
		tempRegisterWB.put("MemToReg", memToReg);
		tempRegisterWB.put("Sign-Extended", SignExtended);
		tempRegisterWB.put("Zero-Extended", ZeroExtended);
		tempRegisterWB.put("JALWB", JALEX);

		tempRegisterM.put("memRead", memRead);
		tempRegisterM.put("MemWrite", MemWrite);
		tempRegisterM.put("Branch", Branch);
		tempRegisterM.put("BNE", BNE);
		tempRegisterM.put("Jump", Jump);
		tempRegisterM.put("JumpR", JumpR);

		tempRegisterEx.put("AluSrc", AluSrc);
		tempRegisterEx.put("AluOp", AluOp);
		tempRegisterEx.put("Shift", Shift);
		tempRegisterEx.put("RegDest", RegDest);
		tempRegisterEx.put("Shift-Left", ShiftLeft);
		tempRegisterEx.put("SB", SB);
		tempRegisterEx.put("JALEX", JALEX);// JALWB = JALEX
	}

	static String hexToBinary(String hex) {
		int i = Integer.parseInt(hex.substring(2), 16);
		String bin = Integer.toBinaryString(i);
		while (bin.length() < 5) {
			bin = "0" + bin;
		}
		return bin;
	}

	private static String signExtendData(String data) {
		String binary = data;
		if (binary.substring(0, 1).equals("1")) {
			while (binary.length() < 32)
				binary = "1" + binary;
		} else {
			while (binary.length() < 32)
				binary = "0" + binary;
		}
		return binary;
	}
}
