package components;

import java.util.Hashtable;

public class ExecutionStage {
	public static boolean execute = false;
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
	public static String registerTwoDataOutput; 


	public static void executeStage() { // called from previous stage (no need to call
								// it again)
		if(execute){
		MemoryStage.execute = true;
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
		int immediate = Integer.parseUnsignedInt(immediateValue,2);
		immediateValueShift2=(Integer.toBinaryString(immediate*4));
		branchAddress=(Integer.parseUnsignedInt(immediateValueShift2,2)+Integer.parseInt(currentPC))+"";
		immediateValueShift16=(Integer.toBinaryString(immediate<<16));
		if (immediate>=0) {
			immediateValueShift2 = "0"+ immediateValueShift2;
			immediateValueShift16 = "0"+ immediateValueShift16;
			immediateValueShift2 = signExtendData(immediateValueShift2);
			immediateValueShift16 = signExtendData(immediateValueShift16);
		}else{
			immediateValueShift2 = "1"+ immediateValueShift2;
			immediateValueShift16 = "1"+ immediateValueShift16;
			immediateValueShift2 = signExtendData(immediateValueShift2);
			immediateValueShift16 = signExtendData(immediateValueShift16);
		}
		if(Integer.parseInt(tempRegisterEx.get("Shift-Left"))==0) {
			Registertwodata2ndInput=immediateValue;
		}
		else
			Registertwodata2ndInput=immediateValueShift16;
		if (Integer.parseInt(tempRegisterEx.get("Shift"))==1) {
			registerOneData = registerTwoData;
		}
		if(Integer.parseInt(tempRegisterEx.get("AluSrc"))==0)
			ALU2ndInput=registerTwoData;
		else
			ALU2ndInput=Registertwodata2ndInput;
		
		if(Integer.parseInt(tempRegisterEx.get("RegDest"))==0)
			registerToWriteTo=registerTAddress;
		else
			registerToWriteTo=registerDAddress;
		if(Integer.parseInt(tempRegisterEx.get("JALEX"))==0)
			registerAddressToWriteTo=registerToWriteTo;
		else
			registerAddressToWriteTo=registerRA;
		if(Integer.parseInt(tempRegisterEx.get("SB"))==0)
			registerTwoDataOutput=registerTwoData;
		else
		{
			int x = Integer.parseUnsignedInt(registerTwoData);
			registerTwoDataOutput=((x >> 3)&1)+""+((x >> 2)&1)+((x >> 1)&1)+((x >> 0)&1);
		}
		switch(tempRegisterEx.get("AluOp")){
		case"Add":{
			int result=(Integer.parseUnsignedInt(registerOneData,2)+Integer.parseUnsignedInt(ALU2ndInput,2));
			aluResult = Integer.toBinaryString(result);
			if (result >= 0 && aluResult.length()<32) {
				aluResult = "0"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			else if(result < 0 && aluResult.length()<32){
				aluResult = "1"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			break;
		}
		case"Sub":{
			
			int result=(Integer.parseUnsignedInt(registerOneData,2)-Integer.parseUnsignedInt(ALU2ndInput,2));
			aluResult = Integer.toBinaryString(result);
			if (result >= 0 && aluResult.length()<32) {
				aluResult = "0"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			else if (result < 0 && aluResult.length()<32){
				aluResult = "1"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			break;
		}
		case"AND":{
			int result=(Integer.parseUnsignedInt(registerOneData,2) & Integer.parseUnsignedInt(ALU2ndInput,2));
			aluResult = Integer.toBinaryString(result);
			if (result >= 0 && aluResult.length()<32) {
				aluResult = "0"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			else if(result < 0 && aluResult.length()<32){
				aluResult = "1"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			break;
		}
		case"OR":{
			int result=(Integer.parseUnsignedInt(registerOneData,2) | Integer.parseUnsignedInt(ALU2ndInput,2));
			aluResult = Integer.toBinaryString(result);
			if (result >= 0 && aluResult.length()<32) {
				aluResult = "0"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			else if (result < 0 && aluResult.length()<32){
				aluResult = "1"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			break;
		}
		case"SLL":{
			ALU2ndInput = ALU2ndInput.substring(21, 26);
			int result= Integer.parseUnsignedInt(registerOneData,2) << Integer.parseUnsignedInt(ALU2ndInput,2);
			aluResult = Integer.toBinaryString(result);
			if (result >= 0 && aluResult.length()<32) {
				aluResult = "0"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			else if (result < 0&& aluResult.length()<32){
				aluResult = "1"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			break;
		}
		case"SRL":{
			ALU2ndInput = ALU2ndInput.substring(21, 26);
			int result= Integer.parseUnsignedInt(registerOneData,2) >> Integer.parseUnsignedInt(ALU2ndInput,2);
			aluResult = Integer.toBinaryString(result);
			if (result >= 0&& aluResult.length()<32) {
				aluResult = "0"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			else if (result < 0&& aluResult.length()<32){
				aluResult = "1"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			break;
		}
		case"NOR":{
			int result=(~(Integer.parseUnsignedInt(registerOneData,2) | Integer.parseUnsignedInt(ALU2ndInput,2)));
			aluResult = Integer.toBinaryString(result);
			if (result >= 0&& aluResult.length()<32) {
				aluResult = "0"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			else if(result < 0&& aluResult.length()<32){
				aluResult = "1"+aluResult;
				aluResult=signExtendData(aluResult);
			}
			break;
		}
		case"SLT":{
			if(Integer.parseUnsignedInt(registerOneData,2) < Integer.parseUnsignedInt(ALU2ndInput,2))
				{
				aluResult="01";
				aluResult = signExtendData(aluResult);
				}
			else {
				aluResult="0";
				aluResult = signExtendData(aluResult);
			}
			break;
		}
		case"SLTU":{
			int x =Integer.compareUnsigned(Integer.parseUnsignedInt(registerOneData,2), Integer.parseUnsignedInt(ALU2ndInput,2));
			if(x <0 )
				{
				aluResult="01";
				aluResult = signExtendData(aluResult);
				}
			else {
				aluResult="0";
				aluResult = signExtendData(aluResult);
			}
			break;
		}
		case"LUI":{
			aluResult=ALU2ndInput;
			break;
		}
		case"JR":{
			aluResult=registerOneData;
			break;
		}
		}

		if(Integer.compare(Integer.parseUnsignedInt(registerOneData,2), Integer.parseUnsignedInt(ALU2ndInput,2))==0)
			aluZeroSignal="1";
		else aluZeroSignal = "0";
		//startNextStage();
		System.out.println("ExecuteStage");
		}else{
			MemoryStage.execute = false;
		}
	}
	private static String signExtendData(String data) {
        String binary = data;
		if(binary.charAt(0) == '1'){
      	  while(binary.length()<32) binary="1"+binary;
        }else{
        	while(binary.length()<32) binary="0"+binary;
        }
		return binary;
	}

}
