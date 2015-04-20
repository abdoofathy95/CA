
package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	private static ArrayList<Instruction> InstructionSet = new ArrayList<Instruction>();
	private static ArrayList<String> allLables = new ArrayList<String>();
			
	
	public Parser() throws IOException{
		getLabels();
		
		validatColumnSyntax();
		validateInstructionNames();
		validateInstructionFormat();
		validateInstructionRegisters();
		fillArray();
		
		
	}
	
	public static void getLabels() throws IOException{
		String currentLine = "";
		FileReader fileReader = new FileReader("code.txt");
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			while(currentLine != null && currentLine.matches("\\s*") ){
				currentLine = br.readLine();
			}
			//Line contains more than 1 ':'
			if(currentLine == null){
				break;
			}
			if (currentLine.contains(":")){
				int pos = getCharPosition(currentLine, ':');
				String label = currentLine.substring(0, pos);
				//System.out.println(label.replaceAll("\\s*", ""));
				allLables.add(label.replaceAll("\\s*", ""));
			}
		}
		br.close();
	}

	public static int countColumns(String x) {
		int count = 0;
		for (int i = 0; i < x.length(); i++) {
			if (x.charAt(i) == ':') {
				count++;
			}
		}
		return count;
	}

	public static int getCharPosition(String x, char c) {
		for (int i = 0; i < x.length(); i++) {
			if (x.charAt(i) == c) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	public static void validatColumnSyntax() throws IOException{
		int lineCounter = 1;
		String currentLine = "";
		FileReader fileReader = new FileReader("code.txt");
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			while(currentLine != null && currentLine.matches("\\s*") ){
				lineCounter++;
				currentLine = br.readLine();
			}
			//Line contains more than 1 ':'
			if(currentLine == null){
				break;
			}
				
			if (currentLine.contains(":")) {
				if (countColumns(currentLine) > 1) {
					System.out
							.println("Invalid input: Unexpected \":\" in line "
									+ lineCounter);
					System.exit(0);
				}
			}
			//Line contains only ':'
			if (currentLine.matches("\\s*"+":"+"\\s*")) {
				System.out
				.println("Invalid input: Unexpected \":\" in line "
						+ lineCounter);
		System.exit(0);
			}
			lineCounter++;
		}
		br.close();
		System.out.println("Validate-column-Done");
	}
	
	public static void validateInstructionNames() throws IOException{
		int lineCounter = 1;
		String currentLine = "";
		FileReader fileReader = new FileReader("code.txt");
		BufferedReader br = new BufferedReader(fileReader);
		String instruction = "";
		while ((currentLine = br.readLine()) != null) {
			while(currentLine != null && currentLine.matches("\\s*") ){
				lineCounter++;
				currentLine = br.readLine();
			}
			//Line contains more than 1 ':'
			if(currentLine == null){
				break;
			}
			if(!currentLine.contains(":")){
				String[] result = currentLine.split(" ");
				for (int i = 0; i < result.length; i++) {
					if(result[i].length() != 0){
						instruction = result[i];
						break;
					}
				}
				if(!instruction.matches("\\s*"+"add"+"\\s*") && !instruction.matches("\\s*"+"addi"+"\\s*") &&
						!instruction.matches("\\s*"+"sub"+"\\s*") && !instruction.matches("\\s*"+"lw"+"\\s*") &&
						!instruction.matches("\\s*"+"lb"+"\\s*") && !instruction.matches("\\s*"+"lbu"+"\\s*") &&
						!instruction.matches("\\s*"+"sw"+"\\s*") && !instruction.matches("\\s*"+"sb"+"\\s*") &&
						!instruction.matches("\\s*"+"lui"+"\\s*") && !instruction.matches("\\s*"+"sll"+"\\s*") && 
						!instruction.matches("\\s*"+"srl"+"\\s*") && !instruction.matches("\\s*"+"and"+"\\s*") &&
						!instruction.matches("\\s*"+"nor"+"\\s*") && !instruction.matches("\\s*"+"beq"+"\\s*") &&
						!instruction.matches("\\s*"+"bne"+"\\s*") && !instruction.matches("\\s*"+"j"+"\\s*") &&
						!instruction.matches("\\s*"+"jal"+"\\s*") && !instruction.matches("\\s*"+"jr"+"\\s*") &&
						!instruction.matches("\\s*"+"slt"+"\\s*") && !instruction.matches("\\s*"+"sltu"+"\\s*")){
					System.out.println(instruction+" in line "+lineCounter + " is not an instruction");
					System.exit(0);
				}
			}
			else{
				int splitPosition = getCharPosition(currentLine, ':');
				String tempS = currentLine.substring(splitPosition+1);
				String[] result = tempS.split(" ");
				for (int i = 0; i < result.length; i++) {
					if(result[i].length() != 0){
						instruction = result[i];
						break;
					}
				}
				if(!instruction.matches("\\s*") && !instruction.matches("\\s*"+"add"+"\\s*") && !instruction.matches("\\s*"+"addi"+"\\s*") &&
						!instruction.matches("\\s*"+"sub"+"\\s*") && !instruction.matches("\\s*"+"lw"+"\\s*") &&
						!instruction.matches("\\s*"+"lb"+"\\s*") && !instruction.matches("\\s*"+"lbu"+"\\s*") &&
						!instruction.matches("\\s*"+"sw"+"\\s*") && !instruction.matches("\\s*"+"sb"+"\\s*") &&
						!instruction.matches("\\s*"+"lui"+"\\s*") && !instruction.matches("\\s*"+"sll"+"\\s*") && 
						!instruction.matches("\\s*"+"srl"+"\\s*") && !instruction.matches("\\s*"+"and"+"\\s*") &&
						!instruction.matches("\\s*"+"nor"+"\\s*") && !instruction.matches("\\s*"+"beq"+"\\s*") &&
						!instruction.matches("\\s*"+"bne"+"\\s*") && !instruction.matches("\\s*"+"j"+"\\s*") &&
						!instruction.matches("\\s*"+"jal"+"\\s*") && !instruction.matches("\\s*"+"jr"+"\\s*") &&
						!instruction.matches("\\s*"+"slt"+"\\s*") && !instruction.matches("\\s*"+"sltu"+"\\s*")){
					System.out.println(instruction+" in line "+lineCounter + " is not an instruction");
					System.exit(0);
				}
			}
			
			lineCounter++;
		}
		br.close();
		System.out.println("Validate-InstructionNames-Done");
	}
	
	public static void validateInstructionFormat() throws IOException{
		int lineCounter = 1;
		String currentLine = "";
		FileReader fileReader = new FileReader("code.txt");
		BufferedReader br = new BufferedReader(fileReader);
		String instruction = "";
		while ((currentLine = br.readLine()) != null) {
			while(currentLine != null && currentLine.matches("\\s*") ){
				lineCounter++;
				currentLine = br.readLine();
			}
			//Line contains more than 1 ':'
			if(currentLine == null){
				break;
			}
			if(!currentLine.contains(":")){
				String[] result = currentLine.split(" ");
				for (int i = 0; i < result.length; i++) {
					if(result[i].length() != 0){
						instruction = result[i];
						break;
					}
				}
				if(instruction.matches("\\s*"+"add"+"\\s*") || instruction.matches("\\s*"+"addi"+"\\s*") ||
						instruction.matches("\\s*"+"sub"+"\\s*") || instruction.matches("\\s*"+"sll"+"\\s*") ||
						instruction.matches("\\s*"+"srl"+"\\s*") || instruction.matches("\\s*"+"and"+"\\s*") ||
						instruction.matches("\\s*"+"nor"+"\\s*") || instruction.matches("\\s*"+"beq"+"\\s*") ||
						instruction.matches("\\s*"+"bne"+"\\s*") || instruction.matches("\\s*"+"slt"+"\\s*") ||
						instruction.matches("\\s*"+"sltu"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr.length < 3){
						System.out.println("Missing parameter in Line "+lineCounter);
						System.exit(0);
					}
					if(tempArr.length > 3){
						System.out.println("Extra parameter in Line "+lineCounter);
						System.exit(0);
					}
					
				}
				
				if(instruction.matches("\\s*"+"lw"+"\\s*") || instruction.matches("\\s*"+"lb"+"\\s*") ||
						instruction.matches("\\s*"+"lbu"+"\\s*") || instruction.matches("\\s*"+"sw"+"\\s*") ||
						instruction.matches("\\s*"+"sb"+"\\s*") || instruction.matches("\\s*"+"lui"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr.length < 2){
						System.out.println("Missing parameter in Line "+lineCounter);
						System.exit(0);
					}
					if(tempArr.length > 2){
						System.out.println("Extra parameter in Line "+lineCounter);
						System.exit(0);
					}
					
				}
				
				if(instruction.matches("\\s*"+"j"+"\\s*") || instruction.matches("\\s*"+"jal"+"\\s*") ||
						instruction.matches("\\s*"+"jr"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr.length < 1 || tempArr[0].matches("\\s") || tempArr[0].equals("")){
						System.out.println("Missing parameter in Line "+lineCounter);
						System.exit(0);
					}
					if(tempArr.length > 1){
						System.out.println("Extra parameter in Line "+lineCounter);
						System.exit(0);
					}
					
					if(instruction.matches("\\s*"+"j"+"\\s*") || instruction.matches("\\s*"+"jal"+"\\s*")){
						if(!allLables.contains(currentLine.substring(instruction.length()).replaceAll("\\s*", ""))){
							System.out.println("Invalid label name in line: " + lineCounter);
							System.exit(0);
						}
					}
					
				}
				
			}
			if(currentLine.contains(":")){
				int splitPosition = getCharPosition(currentLine, ':');
				String tempS = currentLine.substring(splitPosition+1);
				String[] result = tempS.split(" ");
				for (int i = 0; i < result.length; i++) {
					if(result[i].length() != 0){
						instruction = result[i];
						break;
					}
				}
				if(instruction.matches("\\s*"+"add"+"\\s*") || instruction.matches("\\s*"+"addi"+"\\s*") ||
						instruction.matches("\\s*"+"sub"+"\\s*") || instruction.matches("\\s*"+"sll"+"\\s*") ||
						instruction.matches("\\s*"+"srl"+"\\s*") || instruction.matches("\\s*"+"and"+"\\s*") ||
						instruction.matches("\\s*"+"nor"+"\\s*") || instruction.matches("\\s*"+"beq"+"\\s*") ||
						instruction.matches("\\s*"+"bne"+"\\s*") || instruction.matches("\\s*"+"slt"+"\\s*") ||
						instruction.matches("\\s*"+"sltu"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr.length < 3){
						System.out.println("Missing parameter in Line "+lineCounter);
						System.exit(0);
					}
					if(tempArr.length > 3){
						System.out.println("Extra parameter in Line "+lineCounter);
						System.exit(0);
					}
					
				}
				
				if(instruction.matches("\\s*"+"lw"+"\\s*") || instruction.matches("\\s*"+"lb"+"\\s*") ||
						instruction.matches("\\s*"+"lbu"+"\\s*") || instruction.matches("\\s*"+"sw"+"\\s*") ||
						instruction.matches("\\s*"+"sb"+"\\s*") || instruction.matches("\\s*"+"lui"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr.length < 2){
						System.out.println("Missing parameter in Line "+lineCounter);
						System.exit(0);
					}
					if(tempArr.length > 2){
						System.out.println("Extra parameter in Line "+lineCounter);
						System.exit(0);
					}
					
				}
				
				if(instruction.matches("\\s*"+"j"+"\\s*") || instruction.matches("\\s*"+"jal"+"\\s*") ||
						instruction.matches("\\s*"+"jr"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr.length < 1 || tempArr[0].matches("\\s") || tempArr[0].equals("")){
						System.out.println("Missing parameter in Line "+lineCounter);
						System.exit(0);
					}
					if(tempArr.length > 1){
						System.out.println("Extra parameter in Line "+lineCounter);
						System.exit(0);
					}
					if(instruction.matches("\\s*"+"j"+"\\s*") || instruction.matches("\\s*"+"jal"+"\\s*")){
						if(!allLables.contains(currentLine.substring(instruction.length()).replaceAll("\\s*", ""))){
							System.out.println("Invalid label name in line: " + lineCounter);
							System.exit(0);
						}
					}
				}
			}
			
			lineCounter++;
		}
		br.close();
		System.out.println("Validate-Instruction-Format-Done");
	}

	public static void validateInstructionRegisters() throws IOException{
		int lineCounter = 1;
		String currentLine = "";
		FileReader fileReader = new FileReader("code.txt");
		BufferedReader br = new BufferedReader(fileReader);
		String instruction = "";
		while ((currentLine = br.readLine()) != null) {
			while(currentLine != null && currentLine.matches("\\s*") ){
				lineCounter++;
				currentLine = br.readLine();
			}
			//Line contains more than 1 ':'
			if(currentLine == null){
				break;
			}
			if(currentLine.contains(":")){
				int begin = getCharPosition(currentLine, ':')+1;
				currentLine = currentLine.substring(begin);
			}
			if(!currentLine.contains(":")){
				String[] result = currentLine.split(" ");
				String temp5 = "";
				for (int i = 0; i < result.length; i++) {
					 temp5 = " "+ temp5;
					if(result[i].length() != 0){
						instruction = temp5 + result[i];
						//System.out.println(instruction);
						break;
					}
				}
				
				//System.out.println(currentLine.substring(instruction.length()));
				if(instruction.matches("\\s*"+"add"+"\\s*") || instruction.matches("\\s*"+"sub"+"\\s*")
						|| instruction.matches("\\s*"+"and"+"\\s*") || instruction.matches("\\s*"+"nor"+"\\s*")
						|| instruction.matches("\\s*"+"slt"+"\\s*") || instruction.matches("\\s*"+"sltu"+"\\s*")
						|| instruction.matches("\\s*"+"jr"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					
					//System.out.println(temp);
					String[] tempArr = temp.split(",");
					if(tempArr[0].matches("\\s*"+"\\$zero"+"\\s*")){
						System.out.println("ERROR Line "+lineCounter+", Register Zero cannot be overwritten");
						System.exit(0);
					}
					else{
						for (int i = 0; i < tempArr.length; i++) {
							if(!isValidRegister(tempArr[i])){
							System.out.println("Invalid register name in line: "+ lineCounter);
							System.exit(0);
							}
						}
					}
				}
				else if(instruction.matches("\\s*"+"addi"+"\\s*") || instruction.matches("\\s*"+"sll"+"\\s*")
						|| instruction.matches("\\s*"+"srl"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr[0].matches("\\s*"+"\\$zero"+"\\s*")){
						System.out.println("ERROR Line "+lineCounter+", Register Zero cannot be overwritten");
						System.exit(0);
					}
					else{
						for (int i = 0; i < tempArr.length - 1; i++) {
							if(!isValidRegister(tempArr[i])){
							System.out.println("Invalid register name in line: "+ lineCounter);
							System.exit(0);
							}
						}
						String x = "";
						try{							
							//System.out.println(tempArr[1].replaceAll("\\s", ""));
							x = tempArr[2].replaceAll("\\s", "");
							Short.parseShort(x);
							//System.out.println(x+"ACSAD");
							
						}
						catch(Exception E){
							System.out.println("Invalid number format in line: " + lineCounter);
							System.exit(0);
						}
						if(!instruction.matches("\\s*"+"addi"+"\\s*")){
							if(Short.parseShort(x) < 0 || Short.parseShort(x) > 31){
								System.out.println("Invalid number format in line: " + lineCounter);
								System.exit(0);
							}
						}
						
					}
				}
				else if(instruction.matches("\\s*"+"lw"+"\\s*") || instruction.matches("\\s*"+"lb"+"\\s*")
						|| instruction.matches("\\s*"+"lbu"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr[0].matches("\\s*"+"\\$zero"+"\\s*")){
						System.out.println("ERROR Line "+lineCounter+", Register Zero cannot be overwritten");
						System.exit(0);
					}
					else{
						if(!isValidRegister(tempArr[0])){
							System.out.println("Invalid register name in line: "+ lineCounter);
							System.exit(0);
						}
						int begin = getCharPosition(tempArr[1], '(');
						int end = getCharPosition(tempArr[1], ')');
						String reg = tempArr[1].substring(begin + 1, end);
						if(!isValidRegister(reg)){
							System.out.println("Invalid register name "+ reg +" in line: "+ lineCounter);
							System.exit(0);
						}
						
						
					}
				}
				else if(instruction.matches("\\s*"+"sw"+"\\s*") || instruction.matches("\\s*"+"sb"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr[1].matches("\\s*"+"\\$zero"+"\\s*")){
						System.out.println("ERROR Line "+lineCounter+", Register Zero cannot be overwritten");
						System.exit(0);
					}
					else{
						
						if(!isValidRegister(tempArr[0])){
							System.out.println("Invalid register name in line: "+ lineCounter);
							System.exit(0);
						}
						
						int begin = getCharPosition(tempArr[1], '(');
						int end = getCharPosition(tempArr[1], ')');
						String reg = tempArr[1].substring(begin + 1, end);
						//System.out.println(reg);
						if(!isValidRegister(reg)){
							System.out.println("Invalid register name \""+ reg +"\" in line: "+ lineCounter);
							System.exit(0);
						}
					}
				}
				else if(instruction.matches("\\s*"+"beq"+"\\s*") || instruction.matches("\\s*"+"bne"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr[0].matches("\\s*"+"\\$zero"+"\\s*")){
						System.out.println("ERROR Line "+lineCounter+", Register Zero cannot be overwritten");
						System.exit(0);
					}
					else{
						for (int i = 0; i < tempArr.length - 1; i++) {
							if(!isValidRegister(tempArr[i])){
							System.out.println("Invalid register name in line: "+ lineCounter);
							System.exit(0);
							}
						}
						if(!allLables.contains(tempArr[2].replaceAll("\\s*", ""))){
							System.out.println("Invalid label name in line: " + lineCounter);
							System.exit(0);
						}
					}
				}
				else if(instruction.matches("\\s*"+"lui"+"\\s*")){
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if(tempArr[0].matches("\\s*"+"\\$zero"+"\\s*")){
						System.out.println("ERROR Line "+lineCounter+", Register Zero cannot be overwritten");
						System.exit(0);
					}
					else{
						if(!isValidRegister(tempArr[0])){
							System.out.println("Invalid register in Line: " + lineCounter);
							System.exit(0);
						}
						// -32768 to 32767
						//System.out.println(tempArr[1].replaceAll("\\s", ""));
						
						try{							
							//System.out.println(tempArr[1].replaceAll("\\s", ""));
							String x = tempArr[1].replaceAll("\\s", "");
							Short.parseShort(x);
							//System.out.println(x+"ACSAD");
							
						}
						catch(Exception E){
							System.out.println("Invalid number format in line: " + lineCounter);
							System.exit(0);
						}
					}
				}
			}
			lineCounter++;
		}
		br.close();
		System.out.println("Validate-Instruction-Registers-Done");
	}
	
	public static boolean isValidRegister(String x){
		if(!(x.matches("\\s*"+"\\$zero"+"\\s*") || x.matches("\\s*"+"\\$at"+"\\s*") ||
				x.matches("\\s*"+"\\$v0"+"\\s*") || x.matches("\\s*"+"\\$v1"+"\\s*") ||
				x.matches("\\s*"+"\\$a0"+"\\s*") || x.matches("\\s*"+"\\$a1"+"\\s*") ||
				x.matches("\\s*"+"\\$a2"+"\\s*") || x.matches("\\s*"+"\\$a3"+"\\s*") ||
				x.matches("\\s*"+"\\$t0"+"\\s*") || x.matches("\\s*"+"\\$t1"+"\\s*") ||
				x.matches("\\s*"+"\\$t2"+"\\s*") || x.matches("\\s*"+"\\$t3"+"\\s*") ||
				x.matches("\\s*"+"\\$t4"+"\\s*") || x.matches("\\s*"+"\\$t5"+"\\s*") ||
				x.matches("\\s*"+"\\$t6"+"\\s*") || x.matches("\\s*"+"\\$t7"+"\\s*") ||
				x.matches("\\s*"+"\\$t8"+"\\s*") || x.matches("\\s*"+"\\$t9"+"\\s*") ||
				x.matches("\\s*"+"\\$s0"+"\\s*") || x.matches("\\s*"+"\\$s1"+"\\s*") ||
				x.matches("\\s*"+"\\$s2"+"\\s*") || x.matches("\\s*"+"\\$s3"+"\\s*") ||
				x.matches("\\s*"+"\\$s4"+"\\s*") || x.matches("\\s*"+"\\$s5"+"\\s*") ||
				x.matches("\\s*"+"\\$s6"+"\\s*") || x.matches("\\s*"+"\\$s7"+"\\s*") ||
				x.matches("\\s*"+"\\$k0"+"\\s*") || x.matches("\\s*"+"\\$k1"+"\\s*") ||
				x.matches("\\s*"+"\\$gp"+"\\s*") || x.matches("\\s*"+"\\$sp"+"\\s*") ||
				x.matches("\\s*"+"\\$ra"+"\\s*"))){
			return false;
		}
		return true;
	}
	
	public static void fillArray() throws IOException{
		String currentLine = "";
		FileReader fileReader = new FileReader("code.txt");
		BufferedReader br = new BufferedReader(fileReader);
		String instruction = "";
		String label = "";
		String finalInst = "";
		while ((currentLine = br.readLine()) != null) {
			while(currentLine != null && currentLine.matches("\\s*") ){
				currentLine = br.readLine();
			}
			//Line contains more than 1 ':'
			if(currentLine == null){
				break;
			}
			if(currentLine.contains(":")){
				label = currentLine.substring(0, getCharPosition(currentLine, ':'));
				label = label.replaceAll("\\s", "");
				//System.out.print("*"+label+"*");
			//System.out.println(currentLine.substring(getCharPosition(currentLine, ':')+1));
				if(currentLine.substring(getCharPosition(currentLine, ':')+1).matches("\\s*")){
					
					if((currentLine = br.readLine()) != null){
					}
					while(currentLine.matches("\\s*")){
						if((currentLine = br.readLine()) != null){
							}
					}
					instruction = currentLine;
					//System.out.println(instruction);
					String[] tempI = instruction.split("\\s+");
					for (int i = 0; i < tempI.length; i++) {
						//System.out.println(i + " " +tempI[i] + " L " + tempI.length);
						if(!tempI[i].matches("\\s*")){
							finalInst  =finalInst + tempI[i] + " ";
						}
					}
					//System.out.println(instruction);
				}
				else{
					instruction = currentLine.substring(getCharPosition(currentLine, ':')+1);
					//System.out.println(instruction);
					String[] tempI = instruction.split("\\s+");
					for (int i = 0; i < tempI.length; i++) {
						//System.out.println(i + " " +tempI[i] + " L " + tempI.length);
						if(!tempI[i].matches("\\s*")){
							finalInst  =finalInst + tempI[i] + " ";
						}
					}
					
				}
			}
			else{
				instruction = currentLine;
				//System.out.println(instruction);
				//System.out.println(instruction);
				String[] tempI = instruction.split("\\s+");
				for (int i = 0; i < tempI.length; i++) {
					//System.out.println(i + " " +tempI[i] + " L " + tempI.length);
					if(!tempI[i].matches("\\s*")){
						finalInst  =finalInst + tempI[i] + " ";
					}
				}
			}
			//System.out.println(label + ": "+finalInst);
			Instruction temp = new Instruction(label, finalInst);
			//System.out.println(temp.toString());
			InstructionSet.add(temp);
			finalInst="";
			label="";
		}
		br.close();
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		Parser x = new Parser();
		for (int i = 0; i < x.InstructionSet.size(); i++) {
			System.out.println(x.InstructionSet.get(i).toString());
			// BEQ Takes a label as a third parameter (Existing LABEL)
			// BNE Takes a label as a third parameter (Existing LABEL)
			// LB customize the exception (lb $t1 , anything) error should say bad offset 
		}
	}
}
