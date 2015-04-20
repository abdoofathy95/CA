package components;

import java.util.Hashtable;

public class MemoryStage {
	public static Hashtable<String, String> tempRegisterWB;
	private static Hashtable<String, String> tempRegisterM;
	public static String currentPC;
	private static String jumpAddress;
	private static String branchAddress;
	private static String aluZeroSignal;
	public static String memoryData; // set In Current Class
	public static String aluResult;
	private static String registerTwoData;
	public static String registerAddressToWriteTo;

	public static void init() { // called from previous stage (no need to call
								// it again)
		tempRegisterWB = ExecutionStage.tempRegisterWB;
		tempRegisterM = ExecutionStage.tempRegisterM;
		currentPC = ExecutionStage.currentPC;
		jumpAddress = ExecutionStage.jumpAddress;
		branchAddress = ExecutionStage.branchAddress;
		aluZeroSignal = ExecutionStage.aluZeroSignal;
		aluResult = ExecutionStage.aluResult;
		registerTwoData = ExecutionStage.registerTwoData;
		registerAddressToWriteTo = ExecutionStage.registerAddressToWriteTo;
	}

	public static void startNextStage() {
		WriteBackStage.init();
	}
}
