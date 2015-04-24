package components;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	public static InstructionMemory InstructionSet;
	private static ArrayList<String> allLables = new ArrayList<String>();

	@SuppressWarnings("static-access")
	public Parser() throws IOException {
		this.InstructionSet = new InstructionMemory();
		getLabels();
		validatColumnSyntax();
		validateInstructionNames();
		validateInstructionFormat();
		validateInstructionRegisters();
		fillArray();
	}

	public static void getLabels() throws IOException {
		String currentLine = "";
		BufferedReader br = readFromFile();
		while ((currentLine = br.readLine()) != null) {
			while (currentLine != null && currentLine.matches("\\s*")) {
				currentLine = br.readLine();
			}
			if (currentLine == null) {
				break;
			}
			if (currentLine.contains(":")) {
				int pos = getCharPosition(currentLine, ':');
				String label = currentLine.substring(0, pos).replaceAll("\\s*", "");
				if (!allLables.contains(label)) {
					allLables.add(label.replaceAll("\\s*", ""));
				} else {
					System.out.println("Dublicate label name");
					System.exit(0);
				}
				if (label.matches("\\s*" + "add" + "\\s*")
						|| label.matches("\\s*" + "addi" + "\\s*")
						|| label.matches("\\s*" + "sub" + "\\s*")
						|| label.matches("\\s*" + "lw" + "\\s*")
						|| label.matches("\\s*" + "lb" + "\\s*")
						|| label.matches("\\s*" + "lbu" + "\\s*")
						|| label.matches("\\s*" + "sw" + "\\s*")
						|| label.matches("\\s*" + "sb" + "\\s*")
						|| label.matches("\\s*" + "lui" + "\\s*")
						|| label.matches("\\s*" + "sll" + "\\s*")
						|| label.matches("\\s*" + "srl" + "\\s*")
						|| label.matches("\\s*" + "and" + "\\s*")
						|| label.matches("\\s*" + "nor" + "\\s*")
						|| label.matches("\\s*" + "beq" + "\\s*")
						|| label.matches("\\s*" + "bne" + "\\s*")
						|| label.matches("\\s*" + "j" + "\\s*")
						|| label.matches("\\s*" + "jal" + "\\s*")
						|| label.matches("\\s*" + "jr" + "\\s*")
						|| label.matches("\\s*" + "slt" + "\\s*")
						|| label.matches("\\s*" + "sltu" + "\\s*")) {
					System.out.println("Reserver instruction name: \""+label + "\" cannot be a label name");
					System.exit(0);
				}
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

	public static void validatColumnSyntax() throws IOException {
		int lineCounter = 1;
		String currentLine = "";
		BufferedReader br = readFromFile();
		while ((currentLine = br.readLine()) != null) {
			while (currentLine != null && currentLine.matches("\\s*")) {
				lineCounter++;
				currentLine = br.readLine();
			}
			if (currentLine == null) {
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
			if (currentLine.matches("\\s*" + ":" + "\\s*")) {
				System.out.println("Invalid input: Unexpected \":\" in line "
						+ lineCounter);
				System.exit(0);
			}
			lineCounter++;
		}
		br.close();
		System.out.println("Validate-column-Done");
	}

	public static void validateInstructionNames() throws IOException {
		int lineCounter = 1;
		String currentLine = "";
		BufferedReader br = readFromFile();
		String instruction = "";
		while ((currentLine = br.readLine()) != null) {
			while (currentLine != null && currentLine.matches("\\s*")) {
				lineCounter++;
				currentLine = br.readLine();
			}
			if (currentLine == null) {
				break;
			}
			if (!currentLine.contains(":")) {
				String[] result = currentLine.split("\\s");
				for (int i = 0; i < result.length; i++) {
					if (result[i].length() != 0 && !result[i].equals("\\s*")
							&& !result[i].equals("\\t*")) {
						instruction = result[i];
						break;
					}
				}
				if (!instruction.matches("\\s*" + "add" + "\\s*")
						&& !instruction.matches("\\s*" + "addi" + "\\s*")
						&& !instruction.matches("\\s*" + "sub" + "\\s*")
						&& !instruction.matches("\\s*" + "lw" + "\\s*")
						&& !instruction.matches("\\s*" + "lb" + "\\s*")
						&& !instruction.matches("\\s*" + "lbu" + "\\s*")
						&& !instruction.matches("\\s*" + "sw" + "\\s*")
						&& !instruction.matches("\\s*" + "sb" + "\\s*")
						&& !instruction.matches("\\s*" + "lui" + "\\s*")
						&& !instruction.matches("\\s*" + "sll" + "\\s*")
						&& !instruction.matches("\\s*" + "srl" + "\\s*")
						&& !instruction.matches("\\s*" + "and" + "\\s*")
						&& !instruction.matches("\\s*" + "nor" + "\\s*")
						&& !instruction.matches("\\s*" + "beq" + "\\s*")
						&& !instruction.matches("\\s*" + "bne" + "\\s*")
						&& !instruction.matches("\\s*" + "j" + "\\s*")
						&& !instruction.matches("\\s*" + "jal" + "\\s*")
						&& !instruction.matches("\\s*" + "jr" + "\\s*")
						&& !instruction.matches("\\s*" + "slt" + "\\s*")
						&& !instruction.matches("\\s*" + "sltu" + "\\s*")) {
					System.out.println(instruction + " in line " + lineCounter
							+ " is not an instruction");
					System.exit(0);
				}
			} else {
				int splitPosition = getCharPosition(currentLine, ':');
				String tempS = currentLine.substring(splitPosition + 1);
				String[] result = tempS.split("\\s");
				for (int i = 0; i < result.length; i++) {
					if (result[i].length() != 0 && !result[i].equals("\\s*")
							&& !result[i].equals("\\t")) {
						instruction = result[i];
						break;
					}
				}
				if (!instruction.matches("\\s*")
						&& !instruction.matches("\\s*" + "add" + "\\s*")
						&& !instruction.matches("\\s*" + "addi" + "\\s*")
						&& !instruction.matches("\\s*" + "sub" + "\\s*")
						&& !instruction.matches("\\s*" + "lw" + "\\s*")
						&& !instruction.matches("\\s*" + "lb" + "\\s*")
						&& !instruction.matches("\\s*" + "lbu" + "\\s*")
						&& !instruction.matches("\\s*" + "sw" + "\\s*")
						&& !instruction.matches("\\s*" + "sb" + "\\s*")
						&& !instruction.matches("\\s*" + "lui" + "\\s*")
						&& !instruction.matches("\\s*" + "sll" + "\\s*")
						&& !instruction.matches("\\s*" + "srl" + "\\s*")
						&& !instruction.matches("\\s*" + "and" + "\\s*")
						&& !instruction.matches("\\s*" + "nor" + "\\s*")
						&& !instruction.matches("\\s*" + "beq" + "\\s*")
						&& !instruction.matches("\\s*" + "bne" + "\\s*")
						&& !instruction.matches("\\s*" + "j" + "\\s*")
						&& !instruction.matches("\\s*" + "jal" + "\\s*")
						&& !instruction.matches("\\s*" + "jr" + "\\s*")
						&& !instruction.matches("\\s*" + "slt" + "\\s*")
						&& !instruction.matches("\\s*" + "sltu" + "\\s*")) {
					System.out.println(instruction + " in line " + lineCounter
							+ " is not an instruction");
					System.exit(0);
				}
			}

			lineCounter++;
		}
		br.close();
		System.out.println("Validate-InstructionNames-Done");
	}

	public static void validateInstructionFormat() throws IOException {
		int lineCounter = 1;
		String currentLine = "";
		BufferedReader br = readFromFile();
		String instruction = "";
		while ((currentLine = br.readLine()) != null) {

			while (currentLine != null && currentLine.matches("\\s*")) {
				lineCounter++;
				currentLine = br.readLine();
			}
			if (currentLine == null) {
				break;
			}
			if (!currentLine.contains(":")) {
				String[] result = currentLine.split("\\s");
				String temp5 = "";
				for (int i = 0; i < result.length; i++) {
					temp5 = " " + temp5;
					if (result[i].length() != 0) {
						instruction = temp5 + result[i];
						break;
					}
				}
				if (instruction.matches("\\s*" + "add" + "\\s*")
						|| instruction.matches("\\s*" + "addi" + "\\s*")
						|| instruction.matches("\\s*" + "sub" + "\\s*")
						|| instruction.matches("\\s*" + "sll" + "\\s*")
						|| instruction.matches("\\s*" + "srl" + "\\s*")
						|| instruction.matches("\\s*" + "and" + "\\s*")
						|| instruction.matches("\\s*" + "nor" + "\\s*")
						|| instruction.matches("\\s*" + "beq" + "\\s*")
						|| instruction.matches("\\s*" + "bne" + "\\s*")
						|| instruction.matches("\\s*" + "slt" + "\\s*")
						|| instruction.matches("\\s*" + "sltu" + "\\s*")) {
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if (tempArr.length < 3) {
						System.out.println("Missing parameter in Line "
								+ lineCounter);
						System.exit(0);
					}
					if (tempArr.length > 3) {
						System.out.println("Extra parameter in Line "
								+ lineCounter);
						System.exit(0);
					}

				}

				if (instruction.matches("\\s*" + "lw" + "\\s*")
						|| instruction.matches("\\s*" + "lb" + "\\s*")
						|| instruction.matches("\\s*" + "lbu" + "\\s*")
						|| instruction.matches("\\s*" + "sw" + "\\s*")
						|| instruction.matches("\\s*" + "sb" + "\\s*")
						|| instruction.matches("\\s*" + "lui" + "\\s*")) {
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if (tempArr.length < 2) {
						System.out.println("Missing parameter in Line "
								+ lineCounter);
						System.exit(0);
					}
					if (tempArr.length > 2) {
						System.out.println("Extra parameter in Line "
								+ lineCounter);
						System.exit(0);
					}

				}

				if (instruction.matches("\\s*" + "j" + "\\s*")
						|| instruction.matches("\\s*" + "jal" + "\\s*")
						|| instruction.matches("\\s*" + "jr" + "\\s*")) {
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if (tempArr.length < 1 || tempArr[0].matches("\\s")
							|| tempArr[0].equals("")) {
						System.out.println("Missing parameter in Line "
								+ lineCounter);
						System.exit(0);
					}
					if (tempArr.length > 1) {
						System.out.println("Extra parameter in Line "
								+ lineCounter);
						System.exit(0);
					}

					if (instruction.matches("\\s*" + "j" + "\\s*")
							|| instruction.matches("\\s*" + "jal" + "\\s*")) {
						if (!allLables.contains(currentLine.substring(
								instruction.length()).replaceAll("\\s*", ""))) {
							System.out.println("Invalid label name in line: "
									+ lineCounter);
							System.exit(0);
						}
					}

				}

			}
			if (currentLine.contains(":")) {
				int splitPosition = getCharPosition(currentLine, ':');
				String tempS = currentLine.substring(splitPosition + 1);
				String[] result = tempS.split("\\s");
				for (int i = 0; i < result.length; i++) {
					if (result[i].length() != 0) {
						instruction = result[i];
						break;
					}
				}
				if (instruction.matches("\\s*" + "add" + "\\s*")
						|| instruction.matches("\\s*" + "addi" + "\\s*")
						|| instruction.matches("\\s*" + "sub" + "\\s*")
						|| instruction.matches("\\s*" + "sll" + "\\s*")
						|| instruction.matches("\\s*" + "srl" + "\\s*")
						|| instruction.matches("\\s*" + "and" + "\\s*")
						|| instruction.matches("\\s*" + "nor" + "\\s*")
						|| instruction.matches("\\s*" + "beq" + "\\s*")
						|| instruction.matches("\\s*" + "bne" + "\\s*")
						|| instruction.matches("\\s*" + "slt" + "\\s*")
						|| instruction.matches("\\s*" + "sltu" + "\\s*")) {
					String temp = tempS.substring(instruction.length() + 1);
					String[] tempArr = temp.split(",");
					if (tempArr.length < 3) {
						System.out.println("Missing parameter in Line "
								+ lineCounter);
						System.exit(0);
					}
					if (tempArr.length > 3) {
						System.out.println("Extra parameter in Line "
								+ lineCounter);
						System.exit(0);
					}

				}

				if (instruction.matches("\\s*" + "lw" + "\\s*")
						|| instruction.matches("\\s*" + "lb" + "\\s*")
						|| instruction.matches("\\s*" + "lbu" + "\\s*")
						|| instruction.matches("\\s*" + "sw" + "\\s*")
						|| instruction.matches("\\s*" + "sb" + "\\s*")
						|| instruction.matches("\\s*" + "lui" + "\\s*")) {
					String temp = tempS.substring(instruction.length() + 1);
					String[] tempArr = temp.split(",");
					if (tempArr.length < 2) {
						System.out.println("Missing parameter in Line "
								+ lineCounter);
						System.exit(0);
					}
					if (tempArr.length > 2) {
						System.out.println("Extra parameter in Line "
								+ lineCounter);
						System.exit(0);
					}

				}

				if (instruction.matches("\\s*" + "j" + "\\s*")
						|| instruction.matches("\\s*" + "jal" + "\\s*")
						|| instruction.matches("\\s*" + "jr" + "\\s*")) {
					String temp = tempS.substring(instruction.length() + 1);
					String[] tempArr = temp.split(",");
					if (tempArr.length < 1 || tempArr[0].matches("\\s")
							|| tempArr[0].equals("")) {
						System.out.println("Missing parameter in Line "
								+ lineCounter);
						System.exit(0);
					}
					if (tempArr.length > 1) {
						System.out.println("Extra parameter in Line "
								+ lineCounter);
						System.exit(0);
					}
					if (instruction.matches("\\s*" + "j" + "\\s*")
							|| instruction.matches("\\s*" + "jal" + "\\s*")) {
						if (!allLables.contains(currentLine.substring(
								instruction.length()).replaceAll("\\s*", ""))) {
							System.out.println("Invalid label name in line: "
									+ lineCounter);
							System.exit(0);
						}
					}
				}
			}
			instruction = "";
			lineCounter++;
		}
		br.close();
		System.out.println("Validate-Instruction-Format-Done");
	}

	public static void validateInstructionRegisters() throws IOException {
		int lineCounter = 1;
		String currentLine = "";
		BufferedReader br = readFromFile();
		String instruction = "";
		while ((currentLine = br.readLine()) != null) {
			while (currentLine != null && currentLine.matches("\\s*")) {
				lineCounter++;
				currentLine = br.readLine();
			}
			if (currentLine == null) {
				break;
			}
			if (currentLine.contains(":")) {
				int begin = getCharPosition(currentLine, ':') + 1;
				currentLine = currentLine.substring(begin);
			}
			if (!currentLine.contains(":")) {
				String[] result = currentLine.split("\\s");
				String temp5 = "";
				for (int i = 0; i < result.length; i++) {
					temp5 = " " + temp5;
					if (result[i].length() != 0) {
						instruction = temp5 + result[i];
						break;
					}
				}

				if (instruction.matches("\\s*" + "add" + "\\s*")
						|| instruction.matches("\\s*" + "sub" + "\\s*")
						|| instruction.matches("\\s*" + "and" + "\\s*")
						|| instruction.matches("\\s*" + "nor" + "\\s*")
						|| instruction.matches("\\s*" + "slt" + "\\s*")
						|| instruction.matches("\\s*" + "sltu" + "\\s*")
						|| instruction.matches("\\s*" + "jr" + "\\s*")) {
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if (tempArr[0].matches("\\s*" + "\\$zero" + "\\s*")) {
						System.out.println("ERROR Line " + lineCounter
								+ ", Register Zero cannot be overwritten");
						System.exit(0);
					} else {
						for (int i = 0; i < tempArr.length; i++) {
							if (!isValidRegister(tempArr[i])) {
								System.out
										.println("Invalid register name in line: "
												+ lineCounter);
								System.exit(0);
							}
						}
					}
				} else if (instruction.matches("\\s*" + "addi" + "\\s*")
						|| instruction.matches("\\s*" + "sll" + "\\s*")
						|| instruction.matches("\\s*" + "srl" + "\\s*")) {
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if (tempArr[0].matches("\\s*" + "\\$zero" + "\\s*")) {
						System.out.println("ERROR Line " + lineCounter
								+ ", Register Zero cannot be overwritten");
						System.exit(0);
					} else {
						for (int i = 0; i < tempArr.length - 1; i++) {
							if (!isValidRegister(tempArr[i])) {
								System.out
										.println("Invalid register name in line: "
												+ lineCounter);
								System.exit(0);
							}
						}
						String x = "";
						try {
							x = tempArr[2].replaceAll("\\s", "");
							Short.parseShort(x);

						} catch (Exception E) {
							System.out
									.println("Invalid number format in line: "
											+ lineCounter);
							System.exit(0);
						}
						if (!instruction.matches("\\s*" + "addi" + "\\s*")) {
							if (Short.parseShort(x) < 0
									|| Short.parseShort(x) > 31) {
								System.out
										.println("Invalid number format in line: "
												+ lineCounter);
								System.exit(0);
							}
						}

					}
				} else if (instruction.matches("\\s*" + "lw" + "\\s*")
						|| instruction.matches("\\s*" + "lb" + "\\s*")
						|| instruction.matches("\\s*" + "lbu" + "\\s*")) {
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if (tempArr[0].matches("\\s*" + "\\$zero" + "\\s*")) {
						System.out.println("ERROR Line " + lineCounter
								+ ", Register Zero cannot be overwritten");
						System.exit(0);
					} else {
						if (!isValidRegister(tempArr[0])) {
							System.out
									.println("Invalid register name in line: "
											+ lineCounter);
							System.exit(0);
						}
						int begin = getCharPosition(tempArr[1], '(');
						int end = getCharPosition(tempArr[1], ')');
						if (begin == -1 || end == -1 || end < begin) {
							System.out.println("Invalid parameter in line: "
									+ lineCounter);
							System.exit(0);
						}
						String reg = tempArr[1].substring(begin + 1, end);
						if (!isValidRegister(reg)) {
							System.out.println("Invalid register name " + reg
									+ " in line: " + lineCounter);
							System.exit(0);
						}

						try {

							Integer.parseInt(tempArr[1].substring(0, begin)
									.replaceAll("\\s", ""));

						} catch (Exception E) {
							System.out
									.println("Invalid number format in line: "
											+ lineCounter);
							System.exit(0);
						}

					}
				} else if (instruction.matches("\\s*" + "sw" + "\\s*")
						|| instruction.matches("\\s*" + "sb" + "\\s*")) {
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if (tempArr[1].matches("\\s*" + "\\$zero" + "\\s*")) {
						System.out.println("ERROR Line " + lineCounter
								+ ", Register Zero cannot be overwritten");
						System.exit(0);
					} else {

						if (!isValidRegister(tempArr[0])) {
							System.out
									.println("Invalid register name in line: "
											+ lineCounter);
							System.exit(0);
						}

						int begin = getCharPosition(tempArr[1], '(');
						int end = getCharPosition(tempArr[1], ')');
						if (begin == -1 || end == -1 || end < begin) {
							System.out.println("Invalid parameter in line: "
									+ lineCounter);
							System.exit(0);
						}
						String reg = tempArr[1].substring(begin + 1, end);
						if (!isValidRegister(reg)) {
							System.out.println("Invalid register name \"" + reg
									+ "\" in line: " + lineCounter);
							System.exit(0);
						}

						try {

							Integer.parseInt(tempArr[1].substring(0, begin)
									.replaceAll("\\s", ""));

						} catch (Exception E) {
							System.out
									.println("Invalid number format in line: "
											+ lineCounter);
							System.exit(0);
						}
					}
				} else if (instruction.matches("\\s*" + "beq" + "\\s*")
						|| instruction.matches("\\s*" + "bne" + "\\s*")) {
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if (tempArr[0].matches("\\s*" + "\\$zero" + "\\s*")) {
						System.out.println("ERROR Line " + lineCounter
								+ ", Register Zero cannot be overwritten");
						System.exit(0);
					} else {
						for (int i = 0; i < tempArr.length - 1; i++) {
							if (!isValidRegister(tempArr[i])) {
								System.out
										.println("Invalid register name in line: "
												+ lineCounter);
								System.exit(0);
							}
						}
						if (!allLables.contains(tempArr[2].replaceAll("\\s*",
								""))) {
							System.out.println("Invalid label name in line: "
									+ lineCounter);
							System.exit(0);
						}
					}
				} else if (instruction.matches("\\s*" + "lui" + "\\s*")) {
					String temp = currentLine.substring(instruction.length());
					String[] tempArr = temp.split(",");
					if (tempArr[0].matches("\\s*" + "\\$zero" + "\\s*")) {
						System.out.println("ERROR Line " + lineCounter
								+ ", Register Zero cannot be overwritten");
						System.exit(0);
					} else {
						if (!isValidRegister(tempArr[0])) {
							System.out.println("Invalid register in Line: "
									+ lineCounter);
							System.exit(0);
						}
						try {
							String x = tempArr[1].replaceAll("\\s", "");
							Short.parseShort(x);

						} catch (Exception E) {
							System.out
									.println("Invalid number format in line: "
											+ lineCounter);
							System.exit(0);
						}
					}
				}
			}
			instruction = "";
			lineCounter++;
		}
		br.close();
		System.out.println("Validate-Instruction-Registers-Done");
	}

	public static boolean isValidRegister(String x) {
		if (!(x.matches("\\s*" + "\\$zero" + "\\s*")
				|| x.matches("\\s*" + "\\$at" + "\\s*")
				|| x.matches("\\s*" + "\\$v0" + "\\s*")
				|| x.matches("\\s*" + "\\$v1" + "\\s*")
				|| x.matches("\\s*" + "\\$a0" + "\\s*")
				|| x.matches("\\s*" + "\\$a1" + "\\s*")
				|| x.matches("\\s*" + "\\$a2" + "\\s*")
				|| x.matches("\\s*" + "\\$a3" + "\\s*")
				|| x.matches("\\s*" + "\\$t0" + "\\s*")
				|| x.matches("\\s*" + "\\$t1" + "\\s*")
				|| x.matches("\\s*" + "\\$t2" + "\\s*")
				|| x.matches("\\s*" + "\\$t3" + "\\s*")
				|| x.matches("\\s*" + "\\$t4" + "\\s*")
				|| x.matches("\\s*" + "\\$t5" + "\\s*")
				|| x.matches("\\s*" + "\\$t6" + "\\s*")
				|| x.matches("\\s*" + "\\$t7" + "\\s*")
				|| x.matches("\\s*" + "\\$t8" + "\\s*")
				|| x.matches("\\s*" + "\\$t9" + "\\s*")
				|| x.matches("\\s*" + "\\$s0" + "\\s*")
				|| x.matches("\\s*" + "\\$s1" + "\\s*")
				|| x.matches("\\s*" + "\\$s2" + "\\s*")
				|| x.matches("\\s*" + "\\$s3" + "\\s*")
				|| x.matches("\\s*" + "\\$s4" + "\\s*")
				|| x.matches("\\s*" + "\\$s5" + "\\s*")
				|| x.matches("\\s*" + "\\$s6" + "\\s*")
				|| x.matches("\\s*" + "\\$s7" + "\\s*")
				|| x.matches("\\s*" + "\\$k0" + "\\s*")
				|| x.matches("\\s*" + "\\$k1" + "\\s*")
				|| x.matches("\\s*" + "\\$gp" + "\\s*")
				|| x.matches("\\s*" + "\\$sp" + "\\s*") || x.matches("\\s*"
				+ "\\$ra" + "\\s*"))) {
			return false;
		}
		return true;
	}

	public static void fillArray() throws IOException {
		String currentLine = "";
		BufferedReader br = readFromFile();
		String fullInstruction = "";
		String label = "";
		String finalInst = "";
		while ((currentLine = br.readLine()) != null) {
			while (currentLine != null && currentLine.matches("\\s*")) {
				currentLine = br.readLine();
			}
			if (currentLine == null) {
				break;
			}
			if (currentLine.contains(":")) {
				label = currentLine.substring(0,
						getCharPosition(currentLine, ':'));
				label = label.replaceAll("\\s", "");
				if (currentLine
						.substring(getCharPosition(currentLine, ':') + 1)
						.matches("\\s*")) {

					if ((currentLine = br.readLine()) != null) {
					}
					while (currentLine.matches("\\s*")) {
						if ((currentLine = br.readLine()) != null) {
						}
					}
					fullInstruction = currentLine;
					String[] tempI = fullInstruction.split("\\s+");
					for (int i = 0; i < tempI.length; i++) {
						if (!tempI[i].matches("\\s*")) {
							finalInst = finalInst + tempI[i] + " ";
						}
					}
				} else {
					fullInstruction = currentLine.substring(getCharPosition(
							currentLine, ':') + 1);
					String[] tempI = fullInstruction.split("\\s+");
					for (int i = 0; i < tempI.length; i++) {
						if (!tempI[i].matches("\\s*")) {
							finalInst = finalInst + tempI[i] + " ";
						}
					}

				}
			} else {
				fullInstruction = currentLine;
				String[] tempI = fullInstruction.split("\\s+");
				for (int i = 0; i < tempI.length; i++) {
					if (!tempI[i].matches("\\s*")) {
						finalInst = finalInst + tempI[i] + " ";
					}
				}
			}

			String[] result = finalInst.split("\\s");
			String instName = result[0].replaceAll("\\s", "");
			// System.out.println(instName + " 33333333333333333333");
			// System.out.println(finalInst+" 66666666666666");
			// System.out.println(fullInstruction+" 2222222222222222222");
			String rd = "";
			String rs = "";
			String rt = "";
			String constant = "";
			String offset = "";
			String jumpLabel = "";
			String[] registers = finalInst.substring(instName.length()).split(
					",");
			if (instName.matches("\\s*" + "add" + "\\s*")
					|| instName.matches("\\s*" + "sub" + "\\s*")
					|| instName.matches("\\s*" + "and" + "\\s*")
					|| instName.matches("\\s*" + "nor" + "\\s*")
					|| instName.matches("\\s*" + "slt" + "\\s*")
					|| instName.matches("\\s*" + "sltu" + "\\s*")) {
				rd = registers[0].replaceAll("\\s*", "").substring(1);
				rs = registers[1].replaceAll("\\s*", "").substring(1);
				rt = registers[2].replaceAll("\\s*", "").substring(1);
				constant = null;
				offset = null;
				jumpLabel = null;

			}
			if (instName.matches("\\s*" + "lw" + "\\s*")
					|| instName.matches("\\s*" + "lb" + "\\s*")
					|| instName.matches("\\s*" + "lbu" + "\\s*")
					|| instName.matches("\\s*" + "sw" + "\\s*")
					|| instName.matches("\\s*" + "sb" + "\\s*")) {
				rt = registers[0].replaceAll("\\s*", "").substring(1);
				int begin = getCharPosition(registers[1], '(');
				int end = getCharPosition(registers[1], ')');
				rs = registers[1].substring(begin + 1, end)
						.replaceAll("\\s*", "").substring(1);
				offset = registers[1].substring(0, begin).replaceAll("\\s", "");
				rd = null;
				constant = null;
				jumpLabel = null;
			}

			if (instName.matches("\\s*" + "lui" + "\\s*")) {
				rt = registers[0].replaceAll("\\s*", "").substring(1);
				// System.out.println(rt);
				constant = registers[1].replaceAll("\\s*", "");
				// System.out.println(constant);
				rd = null;
				rs = null;
				jumpLabel = null;
				offset = null;
			}
			if (instName.matches("\\s*" + "beq" + "\\s*")
					|| instName.matches("\\s*" + "bne" + "\\s*")) {
				rs = registers[0].replaceAll("\\s*", "").substring(1);
				rt = registers[1].replaceAll("\\s*", "").substring(1);
				jumpLabel = registers[2].replaceAll("\\s*", "");
				rd = null;
				constant = null;
				// System.out.println(rs +" " + rt + " " + offset);
				offset = null;
			}
			if (instName.matches("\\s*" + "j" + "\\s*")
					|| instName.matches("\\s*" + "jal" + "\\s*")) {
				jumpLabel = registers[0].replaceAll("\\s*", "");
				rd = null;
				rs = null;
				rt = null;
				offset = null;
				constant = null;
				// System.out.println(jumpLabel);
			}
			if (instName.matches("\\s*" + "jr" + "\\s*")) {
				rs = registers[0].replaceAll("\\s*", "").substring(1);
				rt = null;
				rd = null;
				offset = null;
				constant = null;
				jumpLabel = null;
				// System.out.println(rs);
			}
			if (instName.matches("\\s*" + "addi" + "\\s*")) {
				rt = registers[0].replaceAll("\\s*", "").substring(1);
				rs = registers[1].replaceAll("\\s*", "").substring(1);
				constant = registers[2].replaceAll("\\s*", "");
				rd = null;
				jumpLabel = null;
				offset = null;
				// System.out.println(rs);
			}
			if (instName.matches("\\s*" + "sll" + "\\s*")
					|| instName.matches("\\s*" + "srl" + "\\s*")) {
				rd = registers[0].replaceAll("\\s*", "").substring(1);
				rt = registers[1].replaceAll("\\s*", "").substring(1);
				constant = registers[2].replaceAll("\\s*", "");
				rs = null;
				offset = null;
				jumpLabel = null;
				// System.out.println(rs);
			}
			Instruction temp = new Instruction(label, instName, rd, rs, rt,
					offset, constant, jumpLabel);
			InstructionSet.instructions.add(temp);
			finalInst = "";
			label = "";
			fullInstruction = "";
			instName = "";
		}
		br.close();
	}

	public static BufferedReader readFromFile() throws FileNotFoundException {
		return new BufferedReader(new FileReader("code.txt"));
	}

	public static String[] getSpecificRegister(String x) {
		System.out.println(x + "55555555555555555555");
		String instruction = "";
		String[] result = x.split(" ");
		String temp5 = "";
		for (int i = 0; i < result.length; i++) {
			temp5 = " " + temp5;
			if (result[i].length() != 0) {
				instruction = temp5 + result[i];
				break;
			}
		}
		System.out.println(instruction);
		String temp = x.substring(instruction.length());
		System.out.println(temp);
		String[] registers = temp.split(",");
		for (int i = 0; i < registers.length; i++) {
			System.out.println(registers[i].replaceAll("\\s", ""));
		}
		return null;
	}
}
