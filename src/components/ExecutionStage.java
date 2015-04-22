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
	private static String registerTwoDataOutput; 


	public static void init() { // called from previous stage (no need to call
								// it again)
		String immediateValueShift16;
		String immediateValueShift2;
		String Registertwodata2ndInput;	
		String ALU2ndInput;
		String registerToWriteTo;
		tempRegisterWB = InstructionDecodeStage.tempRegisterWB;
		tempRegisterM = InstructionDecodeStage.tempRegisterM;
		tempRegisterEx=InstructionDecodeStage.tempRegisterEx;
		currentPC = InstructionDecodeStage.currentPC;
		jumpAddress = InstructionDecodeStage.jumpAddress;
		registerOneData = InstructionDecodeStage.registerOneData;
		registerTwoData = InstructionDecodeStage.registerTwoData;
		immediateValue = InstructionDecodeStage.immediateValue;
		registerTAddress = InstructionDecodeStage.registerTAddress;
		registerDAddress = InstructionDecodeStage.registerDAddress;
		 immediateValueShift2=(Integer.parseInt(immediateValue)*4)+"";
		branchAddress=(Integer.parseInt(immediateValueShift2)+Integer.parseInt(currentPC))+"";
		immediateValueShift16=(Integer.parseInt(immediateValue)*32)+"";
		if(Integer.parseInt(tempRegisterEx.get("Shift-Left"))==0)
			Registertwodata2ndInput=immediateValue;
		else
			Registertwodata2ndInput=immediateValueShift16;
		
		if(Integer.parseInt(tempRegisterEx.get("ALUsrc"))==0)
			ALU2ndInput=registerTwoData;
		else
			ALU2ndInput=Registertwodata2ndInput;
		
		if(Integer.parseInt(tempRegisterEx.get("RegDst"))==0)
			registerToWriteTo=registerTAddress;
		else
			registerToWriteTo=registerDAddress;
		if(Integer.parseInt(tempRegisterEx.get("JAL"))==0)
			registerAddressToWriteTo=registerToWriteTo;
		else
			registerAddressToWriteTo=registerRA;
		if(Integer.parseInt(tempRegisterEx.get("sb"))==0)
			registerTwoDataOutput=registerTwoData;
		else
		{
			int x = Integer.parseInt(registerTwoData);
			
			registerTwoDataOutput=((x >> 3)&1)+""+((x >> 2)&1)+((x >> 1)&1)+((x >> 0)&1);
		}
		switch(tempRegisterEx.get("ALUop")){
		case"Add":{
			aluResult=(Integer.parseInt(registerOneData)+Integer.parseInt(ALU2ndInput))+"";
			break;
		}
		case"Sub":{
			aluResult=(Integer.parseInt(registerOneData)-Integer.parseInt(ALU2ndInput))+"";
			break;
		}
		case"AND":{
			aluResult=(Integer.parseInt(registerOneData) & Integer.parseInt(ALU2ndInput))+"";
			break;
		}
		case"OR":{
			aluResult=(Integer.parseInt(registerOneData) | Integer.parseInt(ALU2ndInput))+"";
			break;
		}
		case"SLL":{
			aluResult=(Integer.parseInt(registerOneData) * Integer.parseInt(ALU2ndInput)*2)+"";
			break;
		}
		case"SRL":{
			aluResult=(Integer.parseInt(registerOneData) / (Integer.parseInt(ALU2ndInput)*2))+"";
			break;
		}
		case"NOR":{
			aluResult=(~(Integer.parseInt(registerOneData) | Integer.parseInt(ALU2ndInput)))+"";
			break;
		}
		case"SLT":{
			if(Integer.parseInt(registerOneData) < Integer.parseInt(ALU2ndInput))
				aluResult="1";
			else aluResult="0";
			break;
		}
		case"SLTU":{
			int x =Integer.compareUnsigned(Integer.parseInt(registerOneData), Integer.parseInt(ALU2ndInput));
			if(x <0 )
				aluResult="1";
			else aluResult="0";
			break;
		}
		case"LUI":{
			aluResult=ALU2ndInput;
			break;
		}
		//JR mesh ma3moola
		}
		if(Integer.compare(Integer.parseInt(registerOneData), Integer.parseInt(ALU2ndInput))==0)
			aluZeroSignal="1";
		else aluZeroSignal = "0";
	}

	public static void startNextStage() {
		MemoryStage.init();
	}

}
