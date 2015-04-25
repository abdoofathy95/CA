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

		// InstructionDecodeStage.ExecuteStage();
	}

	public static void fetch() {
		if (currentPC < InstructionMemory.instructions.size()) {
			currentInstruction = InstructionMemory.instructions.get(currentPC);
			currentPC += 1;
			if (jumpBranchInstruction(currentInstruction)) {
				InstructionDecodeStage.execute = false;
			} else {
				InstructionDecodeStage.execute = true;
			}
			System.out.println("InstructionFetchStage");
		} else {
			InstructionDecodeStage.execute = false;
		}

	}

	public static boolean jumpBranchInstruction(Instruction instruction) {
		switch (instruction.getInstructionName()) {
		case "beq": {
			if (binToInt(RegisterFile.readRegisterWithItsName(instruction
					.getRs()))
					- binToInt(RegisterFile.readRegisterWithItsName(instruction
							.getRt())) == 0) {
				currentPC = getLabelIndex(instruction.getJumpLabel());
			}
			return true;
		}
		case "bne": {
			if (binToInt(RegisterFile.readRegisterWithItsName(instruction
					.getRs()))
					- binToInt(RegisterFile.readRegisterWithItsName(instruction
							.getRt())) != 0) {
				currentPC = getLabelIndex(instruction.getJumpLabel());
			}
			return true;
		}
		case "j": {
			currentPC = getLabelIndex(instruction.getJumpLabel());
			return true;
		}

		case "jal": {
			currentPC = getLabelIndex(instruction.getJumpLabel());
			String nextInstructionIndex = Integer
					.toBinaryString(getInstructionIndex(instruction) + 1);
			if (nextInstructionIndex.length() < 32) {
				while (nextInstructionIndex.length() < 32) {
					nextInstructionIndex = "0" + nextInstructionIndex;
				}
			}
			RegisterFile.writeToRegister("00000000000000000000000000011111",
					nextInstructionIndex);
			return true;
		}
		case "jr": {
			currentPC = binToInt(RegisterFile.readRegisterWithItsName("ra"));
			return true;
		}
		}
		return false;
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

	public static int binToInt(String bin) {
		return Integer.parseUnsignedInt(bin, 2);
	}

	public static void setPC(int i) {
		currentPC = i;
	}
}
