package components;

import java.util.Hashtable;

public class MemoryStage {
	public static boolean execute = false;
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
	
	public static void executeStage() {
		
		if(execute){
		WriteBackStage.execute = true;
		init();
		//memRead , memWrite , Branch , BNE , Jump , JumpR
		String memRead = tempRegisterM.get("memRead");
		String memWrite = tempRegisterM.get("MemWrite");
		String branch = tempRegisterM.get("Branch");
		String bne = tempRegisterM.get("BNE");
		String jump = tempRegisterM.get("Jump");
		String jumpR = tempRegisterM.get("JumpR");
		if (memRead.matches("1")){
			// read from memory
			int address = Integer.parseInt(aluResult);
			memoryData = Memory.readFromMemory(address);
		}
		if (memWrite.matches("1")){
			int address = Integer.parseInt(aluResult,2);
			Memory.writeToMemory(address, registerTwoData);
		}
		if (branch.matches("1")){
			//branch
			//if (aluZeroSignal.matches("1"))	currentPC = branchAddress;
		}
		if (bne.matches("1")){
			//branch
			//if (aluZeroSignal.matches("1"))	currentPC = branchAddress;
		}
		if (jump.matches("1")){
			//branch
			//currentPC = jumpAddress;
		}
		if (jumpR.matches("1")){
			//branch
			//currentPC = aluResult;
		}
		//startNextStage();
		System.out.println("MemoryStage");
		}else{
			WriteBackStage.execute = false;
		}
	}
}
