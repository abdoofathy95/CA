package components;

import java.util.Hashtable;

public class WriteBackStage {
	private static Hashtable<String, String> tempRegisterWB;
	private static String currentPC;
	private static String memoryData;
	private static String aluResult;
	public static String registerAddressToWriteTo;
	public static String dataToBeWrittenToReg; // set in current class
	
	public static void init() {
		tempRegisterWB = MemoryStage.tempRegisterWB;
		currentPC = MemoryStage.currentPC;
		aluResult = MemoryStage.aluResult;
		registerAddressToWriteTo = MemoryStage.registerAddressToWriteTo;
		memoryData = MemoryStage.memoryData;
	}

}
