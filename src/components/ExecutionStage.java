package components;

import java.util.Hashtable;

public class ExecutionStage {
	public static Hashtable<String, String> tempRegisterWB;
	public static Hashtable<String, String> tempRegisterM;
	private static Hashtable<String, String> tempRegisterEx;
	public static String currentPC;
	public static String jumpAddress;
	// set in the current Class
	public static String branchAddress;
	// private since it won't be passed to next stage
	private static String registerOneData;
	public static String registerTwoData;
	// set in the current class
	public static String aluZeroSignal;
	// set in the current class
	public static String aluResult;
	// private since it won't be passed to next stage (used only in this class)
	private static String immediateValue;
	// pick one of (register t , register d) according to instruction itself
	public static String registerAddressToWriteTo;
	private static String registerTAddress;
	private static String registerDAddress;
	private static final String registerRA = "$RA";

	public static void init() { // called from previous stage (no need to call
								// it again)
		tempRegisterWB = InstructionDecodeStage.tempRegisterWB;
		tempRegisterM = InstructionDecodeStage.tempRegisterM;
		currentPC = InstructionDecodeStage.currentPC;
		jumpAddress = InstructionDecodeStage.jumpAddress;
		registerOneData = InstructionDecodeStage.registerOneData;
		registerTwoData = InstructionDecodeStage.registerTwoData;
		immediateValue = InstructionDecodeStage.immediateValue;
		registerTAddress = InstructionDecodeStage.registerTAddress;
		registerDAddress = InstructionDecodeStage.registerDAddress;
	}

	public static void startNextStage() {
		MemoryStage.init();
	}

}
