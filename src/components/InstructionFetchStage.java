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

	public static void main(String[] args) {
		RegisterFile.initRegistersWithAddresses();
		RegisterFile.initRegistersWithZeros();
		InstructionMemory.instructions.add(new Instruction("label1", "beq", "", "t1", "t2", "", "", ""));
		InstructionMemory.instructions.add(new Instruction("", "beq", "", "t1", "t2", "", "", "label1"));
		currentPC = 1;
		ExecuteStage();
	}
	
	public static void ExecuteStage() {
		fetch();
		InstructionDecodeStage.ExecuteStage();
	}

	public static void fetch() {
		currentInstruction = InstructionMemory.instructions.get(currentPC);
		currentPC += 1;
		jumpBranchInstruction(currentInstruction);
	}

	public static void jumpBranchInstruction(Instruction instruction) {
		switch (instruction.getInstructionName()) {
		case "beq": {
			if (hexToInt(RegisterFile.readRegister1(instruction.getRs()))
					- hexToInt(RegisterFile.readRegister2(instruction.getRt())) == 0) {
				currentPC = getLabelIndex(instruction.getJumpLabel());
			}
			break;
		}
		case "bne": {
			if (hexToInt(RegisterFile.readRegister1(instruction.getRs()))
					- hexToInt(RegisterFile.readRegister2(instruction.getRt())) != 0) {
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
					+ hexToInt(RegisterFile.readRegister1(instruction.getRs()));
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
		return Integer.parseInt(hex.substring(2), 16);
	}
}
