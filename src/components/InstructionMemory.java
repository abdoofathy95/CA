package components;

import java.lang.reflect.Array;
import java.util.ArrayList;

/* THIS CLASS SIMULATES THE INSTRUCTION MEMORY (HOLDING THE PROGRAM)
* WHEN READING THE PROGRAM FILL THE CURRENT CLASS WITH STRINGS (INSTRUCTIONS)
* SIMULATE IT WITH DATASTRUCTURE (ARRAY 8albn)
*/
public class InstructionMemory {
	ArrayList<Instruction> instructions;
	
	public InstructionMemory(){
		this.instructions = new ArrayList<Instruction>();
	}
}
