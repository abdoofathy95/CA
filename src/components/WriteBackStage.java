package components;

import java.util.Hashtable;

public class WriteBackStage {
	public static boolean execute = false;
	private static Hashtable<String, String> tempRegisterWB;
	private static String currentPC;
	private static String memoryData;
	private static String aluResult;
	public static String registerAddressToWriteTo;
	public static String dataToBeWrittenToReg; // set in current class

	public static void executeStage() {
		if(execute){
		tempRegisterWB = MemoryStage.tempRegisterWB;
		currentPC = MemoryStage.currentPC;
		aluResult = MemoryStage.aluResult;
		registerAddressToWriteTo = MemoryStage.registerAddressToWriteTo;
		memoryData = MemoryStage.memoryData;
		findInstruction(tempRegisterWB);
		System.out.println("WriteBackStage");
		}
	}	
		public static void findInstruction(Hashtable<String,String> tempRegisterWB){
			
			if(tempRegisterWB.get("MemToReg").equals("1")){ //LOAD
				System.out.println(registerAddressToWriteTo+"UUUUUUUUUUUUUUUUUUUUUUUUUU");
				if(tempRegisterWB.get("Sign-Extended").equals("1")){ //lb
					dataToBeWrittenToReg = signExtendData(memoryData);
					lb(registerAddressToWriteTo,dataToBeWrittenToReg);
				}
				else{
					if(tempRegisterWB.get("Zero-Extended").equals("1")){ //lbu
						dataToBeWrittenToReg = "00000000000000000000000000"+memoryData.substring(memoryData.length()-8,memoryData.length());
						lbu(registerAddressToWriteTo,dataToBeWrittenToReg);
					}
					else{ //lw
						dataToBeWrittenToReg = memoryData;
						lw(registerAddressToWriteTo,dataToBeWrittenToReg);
					}
				}
			}else{
				if(tempRegisterWB.get("JALWB").equals("1")){ //JAL
					
					RegisterFile.writeToRegister("ra",currentPC);
					//JUMP HERE
					
				}else{
					// add, sub, and, addi, lui, sll, srl, nor, slt, sltu
					if(tempRegisterWB.get("RegWrite").equals("1")){ 
						RegisterFile.writeToRegister(registerAddressToWriteTo,aluResult);
					}
				}
			}
		}

		private static String signExtendData(String data) {
          String binary = data.substring(data.length()-8,data.length());
          if(binary.substring(0,1).equals("1")){
        	  return "11111111111111111111111111"+data.substring(data.length()-8,data.length());
          }else{
        	  return "00000000000000000000000000"+data.substring(data.length()-8,data.length());
          }
		}

		private static void lw(String register,
				String data) {
			
			//assuming registersAddress has address as key and register name as value
			RegisterFile.writeToRegister(register,data);
		}

		private static void lbu(String register,
				String data) {
			//assuming registersAddress has address as key and register name as value
			RegisterFile.writeToRegister(register,data);
		}

		private static void lb(String register,
				String data) {
			//assuming registersAddress has address as key and register name as value
			RegisterFile.writeToRegister(register,data);
		}
		
		
		public static String hexToBinary8Bits(String hex) {
			int i = Integer.parseInt(hex.substring(2), 16);
			String bin = Integer.toBinaryString(i);
			while (bin.length() < 8) {
				bin = "0" + bin;
			}
			return bin;
		}
		
		
		
	}



