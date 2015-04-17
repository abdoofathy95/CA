package components;

public class Instruction {
	private String label;
	private String instruction;
	
	public Instruction(String label, String instruction){
		this.label = label;
		this.instruction = instruction;
	}
	
	public Instruction(String instruction){
		this.label = null;
		this.instruction = instruction;
	}
}
