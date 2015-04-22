package components;

import java.util.Hashtable;

public class MemoryStage {
<<<<<<< HEAD
	public static Hashtable<String, String> tempRegisterWB;
	private static Hashtable<String, String> tempRegisterM;
	public static String currentPC;
	private static String jumpAddress;
=======
	public static Hashtable<String,String> tempRegisterWB;
	private static Hashtable<String,String> tempRegisterM;
	public static int currentPC; 
	private static int jumpAddress;
>>>>>>> origin/parser-under-construction
	private static String branchAddress;
	private static String aluZeroSignal;
	public static String memoryData; // set In Current Class
	public static String aluResult;
	private static String registerTwoData;
	public static String registerAddressToWriteTo;
<<<<<<< HEAD

	public static void init() { // called from previous stage (no need to call
								// it again)
=======
	
	
	public static void init() { // called from previous stage (no need to call it again)
>>>>>>> origin/parser-under-construction
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
<<<<<<< HEAD

=======
>>>>>>> origin/parser-under-construction
	public static void startNextStage() {
		WriteBackStage.init();
	}
}
