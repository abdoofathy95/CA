package components;

import java.util.Hashtable;
/* refer to this site to understand format 
* http://www.cs.umd.edu/class/sum2003/cmsc311/Notes/Mips/format.html
* here fetch currentPC from fetchStage (access using InstructionFetchStage.currentPC;
*/
public class InstructionDecodeStage {
	// generate control signals here according to method
	public static Hashtable<String,String> tempRegisterWB; // will be holding signals generated according to current instruction
	public static Hashtable<String,String> tempRegisterM; // will be holding signals generated according to current instruction
	public static Hashtable<String,String> tempRegisterEx; // will be holding signals generated according to current instruction
	public static int currentPC; // will be holding next pc value
	public static int jumpAddress; // it's calculated here in this class
	private static String currentInstruction; // will hold instuction values (it's private since it won't be called from next stage (only used in the current class)
	public static String registerOneData; //will be holding content of register s (source register)
	public static String registerTwoData; // will be holding content of register t (register after source)
	public static String immediateValue; // will be holding immediate value if instruction is i-format, else null
	public static String registerTAddress; // will be holding address of register t if any, else null
	public static String registerDAddress; // will be holding address of register d (destination register if any), else null . ex d can hold $t1 , $t2 or whatever (must be address or null)

	public static void init() { // called from previous stage
		currentPC = InstructionFetchStage.currentPC;
		currentInstruction = InstructionFetchStage.currentInstruction;
	}
	
	public static void startNextStage() { // calls next stage (call it when done)
		ExecutionStage.init();
	}
	
	public void generateControlSignals(String instructionName) {
	// fill temporary registers here
	}


}
