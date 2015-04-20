package components;

/*
 * HERE fetch the instruction
 * increment the pc and put it in the currentPC
 * prepare the fetch of next instruction here or in InstructionFetchStage (e3ml el fetch mn el a5er hna aw fi el stage elli gaia)
 */
public class InstructionFetchStage {
	public static int currentPC; // at beginning of a program execution (after
									// reading the file containing the program),
									// set this to zero
	public static Object[] currentInstruction;

	/*
	 * public static void startNextStage() { //InstructionDecodeStage.init(); }
	 */

	public static void ExecuteStage() {
		fetch();
		currentPC += 1;
	}

	private static void fetch() {
		currentInstruction = InstructionMemory.instructions.get(currentPC);
	}

}
