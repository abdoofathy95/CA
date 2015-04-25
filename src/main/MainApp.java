package main;

import java.io.IOException;

import components.ExecutionStage;
import components.InstructionDecodeStage;
import components.InstructionFetchStage;
import components.InstructionMemory;
import components.Memory;
import components.MemoryStage;
import components.Parser;
import components.RegisterFile;
import components.WriteBackStage;

public class MainApp {
	@SuppressWarnings("static-access") // to be moved to a seperate class ( main application class)
	public static void main(String[] args) throws IOException {
		Parser x = new Parser();
		InstructionFetchStage.setPC(0);
		for (int i = 0; i < x.InstructionSet.instructions.size(); i++) {
			System.out.println(x.InstructionSet.instructions.get(i).toString());
			// Offset range in load and store
		}
		// initialize the memory
		Memory.init();
		RegisterFile.initRegistersWithAddresses();
		RegisterFile.initRegistersWithZeros();
		//
		WriteBackStage backStage = new WriteBackStage();
		MemoryStage memoryStage = new MemoryStage();
		ExecutionStage executionStage = new ExecutionStage();
		InstructionDecodeStage instructionDecodeStage = new InstructionDecodeStage();
		InstructionFetchStage fetchStage = new InstructionFetchStage();
		RegisterFile registerFile = new RegisterFile();
		int y = 0;
		while (InstructionFetchStage.currentPC < InstructionMemory.instructions.size()) {
			System.out.println("-------------Cycle " + y + "-------------");
			backStage.executeStage();
			memoryStage.executeStage();
			executionStage.executeStage();
			instructionDecodeStage.executeStage();
			fetchStage.executeStage();
			System.out.println("-------------end Cycle " + y + "-------------");
			y++;
		}
		for (int i = 0 ; i < 4 ; i++) {
			System.out.println("-------------Cycle " + y + "-------------");
			backStage.executeStage();
			memoryStage.executeStage();
			executionStage.executeStage();
			instructionDecodeStage.executeStage();
			fetchStage.executeStage();
			System.out.println("-------------end Cycle " + y + "-------------");
			y++;
		}
		System.out.println(RegisterFile.readRegisterWithItsName("t1"));
			System.out.println(RegisterFile.readRegisterWithItsName("t5"));
	}
}
