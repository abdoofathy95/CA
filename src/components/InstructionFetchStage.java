package components;

import java.util.Iterator;

/*
 * HERE fetch the instruction
 * increment the pc and put it in the currentPC
 * prepare the fetch of next instruction here or in InstructionFetchStage (e3ml el fetch mn el a5er hna aw fi el stage elli gaia)
 */
public class InstructionFetchStage {
	public static int currentPC; // at beginning of a program execution (after
									// reading the file containing the program),
									// set this to zero
	public static Instruction currentInstruction;

	/*
	 * public static void startNextStage() { //InstructionDecodeStage.init(); }
	 */

	public static void executeStage() {
		fetch();
		
		//InstructionDecodeStage.ExecuteStage();
	}

	public static void fetch() {
		if(currentPC < InstructionMemory.instructions.size()){
		currentInstruction = InstructionMemory.instructions.get(currentPC);
		currentPC += 1;
		jumpBranchInstruction(currentInstruction);
		InstructionDecodeStage.execute = true;
		System.out.println("InstructionFetchStage");
		}else{
			InstructionDecodeStage.execute = false;
		}
		
	}

	public static void jumpBranchInstruction(Instruction instruction) {
		switch (instruction.getInstructionName()) {
		case "beq": {
			if (hexToInt(RegisterFile.readRegisterWithItsName(instruction
					.getRs()))
					- hexToInt(RegisterFile.readRegisterWithItsName(instruction
							.getRt())) == 0) {
				currentPC = getLabelIndex(instruction.getJumpLabel());
			}
			break;
		}
		case "bne": {
			if (hexToInt(RegisterFile.readRegisterWithItsName(instruction
					.getRs()))
					- hexToInt(RegisterFile.readRegisterWithItsName(instruction
							.getRt())) != 0) {
				currentPC = getLabelIndex(instruction.getJumpLabel());
			}
			break;
		}
		case "j": {
			currentPC = getLabelIndex(instruction.getJumpLabel());
			break;
		}
		case "jal": {
			currentPC = getLabelIndex(instruction.getJumpLabel());
			break;
		}
		case "jr": {
			currentPC = getInstructionIndex(instruction)
					+ hexToInt(RegisterFile.readRegisterWithItsName(instruction
							.getRs()));
			break;
		}
		}
	}

	public static int getLabelIndex(String label) {
		int index = 0;
		Iterator<Instruction> instructions = InstructionMemory.instructions
				.iterator();
		while (instructions.hasNext()) {
			Instruction instruction = instructions.next();
			if (instruction.getLabel().equals(label)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static int getInstructionIndex(Instruction lookupInstruction) {
		int index = 0;
		Iterator<Instruction> instructions = InstructionMemory.instructions
				.iterator();
		while (instructions.hasNext()) {
			Instruction instruction = instructions.next();
			if (instruction == lookupInstruction) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public static int hexToInt(String hex) {
		return Integer.parseUnsignedInt(hex.substring(2), 16);
	}

	public static void setPC(int i) {
		currentPC = i;
	}
}
