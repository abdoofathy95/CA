package components;

public class Instruction {
	private String label;
	private String instructionName;
	private String rd;
	private String rs;
	private String rt;
	private String offset;
	private String constant;
	private String jumpLabel;

	public Instruction(String label, String instruction, String rd, String rs,
			String rt, String offset, String constant, String jumpLabel) {
		this.label = label;
		this.instructionName = instruction;
		this.rd = rd;
		this.rt = rt;
		this.rs = rs;
		this.offset = offset;
		this.constant = constant;
		this.jumpLabel = jumpLabel;
	}

	public Instruction(String label, String instruction) {
		this.label = label;
		this.instructionName = instruction;
	}

	public String toString() {
		String x = "";
		if (!label.equals("")) {
			x = label + ": ";
		}
		x = x + instructionName;
		if (rd != null) {
			x = x + " (rd: " + rd + ") ";
		}

		if (rt != null) {
			x = x + " (rt: " + rt + ") ";
		}
		if (offset != null) {
			x = x + " (offset: " + offset + ") ";
		}
		if (rs != null) {
			x = x + " (rs: " + rs + ") ";
		}

		if (jumpLabel != null) {
			x = x + " (Jumping to: " + jumpLabel + ") ";
		}

		if (constant != null) {
			x = x + " (constant: " + constant + ")";
		}
		return (x);

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getInstructionName() {
		return instructionName;
	}

	public void setInstructionName(String instructionName) {
		this.instructionName = instructionName;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getConstant() {
		return constant;
	}

	public void setConstant(String constant) {
		this.constant = constant;
	}

	public String getJumpLabel() {
		return jumpLabel;
	}

	public void setJumpLabel(String jumpLabel) {
		this.jumpLabel = jumpLabel;
	}
}