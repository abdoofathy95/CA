package components;

public class Instruction {
	private String label;
	private String instruction;

	public Instruction(String label, String instruction) {
		this.label = label;
		this.instruction = instruction;
	}


	public String toString() {
		if(label.equals("")){
			return this.instruction;
		}
		return (this.label + ": " + this.instruction);

	}
}
