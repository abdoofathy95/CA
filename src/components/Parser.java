package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	private ArrayList<Instruction> Instructions = new ArrayList<Instruction>();
	
	public ArrayList<Instruction> getAllInstructions() {

		return null;
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
	
	public static void addLabelsAndInstructions(){
		
	}
	
	public static void validatColumnSyntax() throws IOException{
		int lineCounter = 1;
		String currentLine = "";
		FileReader fileReader = new FileReader("code.txt");
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			while(currentLine.matches("\\s*")){
				lineCounter++;
				currentLine = br.readLine();
			}
			//Line contains more than 1 ':'
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
			while(currentLine.matches("\\s*")){
				lineCounter++;
				currentLine = br.readLine();
			}
			if(!currentLine.contains(":")){
				String[] result = currentLine.split(" ");
				for (int i = 0; i < result.length; i++) {
					if(result[i].length() != 0){
						instruction = result[i];
						break;
					}
				}
				if(!instruction.equals("add") && !instruction.equals("addi") &&
						!instruction.equals("sub") && !instruction.equals("lw") &&
						!instruction.equals("lb") && !instruction.equals("lbu") &&
						!instruction.equals("sw") && !instruction.equals("sb") &&
						!instruction.equals("lui") && !instruction.equals("sll") && 
						!instruction.equals("srl") && !instruction.equals("and") &&
						!instruction.equals("nor") && !instruction.equals("beq") &&
						!instruction.equals("bne") && !instruction.equals("j") &&
						!instruction.equals("jal") && !instruction.equals("jr") &&
						!instruction.equals("slt") && !instruction.equals("sltu")){
					System.out.println(instruction+" in line "+lineCounter + " is not an instruction");
					System.exit(0);
				}
			}
			
			lineCounter++;
		}
		br.close();
		System.out.println("Validate-InstructionNames-Done");
	}


	public static void main(String[] args) throws IOException {
		ArrayList<Instruction> instructions = new ArrayList<Instruction>();
		validatColumnSyntax();
		validateInstructionNames();
		/*
		int lineCounter = 1;
		String currentLine = "";
		FileReader fileReader = new FileReader("code.txt");
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			while(currentLine.matches("\\s*")){
				currentLine = br.readLine();
			}
			System.out.println("what");
			if (currentLine.contains(":")) {
				if (countColumns(currentLine) > 1) {
					System.out
							.println("Invalid input: Unexpected \":\" in line "
									+ lineCounter);
					System.exit(0);
				}
				// String [] result= currentLine.split(":");

				if (currentLine
						.substring(getCharPosition(currentLine, ':') + 1)
						.matches("\\s*")) {
					currentLine = br.readLine();
					while(currentLine.matches("\\s*")){
						currentLine = br.readLine();
					}
					System.out.println(currentLine);
					System.out.println("fuck you");
				}
			}
			lineCounter++;
		}
		br.close();
		*/
	}
}
