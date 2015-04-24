package main;

import java.io.IOException;

import components.InstructionFetchStage;
import components.InstructionMemory;
import components.Memory;
import components.Parser;
import components.RegisterFile;

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
		while(InstructionFetchStage.currentPC < InstructionMemory.instructions.size()) {
			InstructionFetchStage.ExecuteStage();
		}
	}
}
