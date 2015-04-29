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
import java.util.ArrayList;

public class MainApp {
     public boolean executionDone = false;
     public static ArrayList <String> log;
	@SuppressWarnings("static-access") // to be moved to a seperate class ( main application class)
        public static void addToLog(String txt)
        {
 
        log.add(txt);
        }
	public void start()
        {
             log = new ArrayList<String>();
		InstructionFetchStage.setPC(0);
		
		// initialize the memory
		
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
			log.add("-------------Cycle " + y + "-------------");
                        
			backStage.executeStage();
			memoryStage.executeStage();
			executionStage.executeStage();
			instructionDecodeStage.executeStage();
			fetchStage.executeStage();
			log.add("-------------end Cycle " + y + "-------------");
			y++;
		}
		for (int i = 0 ; i < 4 ; i++) {
			log.add("-------------Cycle " + y + "-------------");
			backStage.executeStage();
			memoryStage.executeStage();
			executionStage.executeStage();
			instructionDecodeStage.executeStage();
			fetchStage.executeStage();
			log.add("-------------end Cycle " + y + "-------------");
			y++;
		}
                
			log.add("//////////////////////"+Memory.readFromMemory(0));
                        
          
         executionDone = true;    
          
        }
        
        
     
}
	