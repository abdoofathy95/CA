package components;

import java.util.Hashtable;

public class ExecutionStage {
	public static Hashtable<String,String> tempRegisterWB;
	public static Hashtable<String,String> tempRegisterM;
	private static Hashtable<String,String> tempRegisterEx;
	public static int currentPC;
	public static int jumpAddress;
	public static String branchAddress;  // set in the current Class
	private static String registerOneData; // private since it won't be passed to next stage
	public static String registerTwoData;
	public static String aluZeroSignal; // set in the current class
	public static String aluResult; // set in the current class
	private static String immediateValue; // private since it won't be passed to next stage (used only in this class)
	public static String registerAddressToWriteTo;  // pick one of (register t , register d) according to instruction itself
	private static String registerTAddress;
	private static String registerDAddress;
	private static final String registerRA = "$RA";
	
	public static void init() { // called from previous stage (no need to call it again)
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
