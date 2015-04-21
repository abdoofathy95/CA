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
			x = x + " (rd: " + rd + "), ";
		}

		if (rt != null) {
			x = x + " (rt: " + rt + "), ";
		}
		if (offset != null) {
			x = x + " (offset: " + offset + "), ";
		}
		if (rs != null) {
			x = x + " (rs: " + rs + "), ";
		}

		if (jumpLabel != null) {
			x = x + " (Jumping to: " + jumpLabel + "), ";
		}

		if (constant != null) {
			x = x + " (constant: " + constant + ")";
		}
		return (x);

	}
}
// Documentation of registers used in each instruction
/*
 * add $rd, $rs, $rt 
 * sub $rd, $rs, $rt 
 * and $rd, $rs, $rt 
 * nor $rd, $rs, $rt 
 * slt $rd, $rs, $rt 
 * sltu $rd, $rs, $rt 
 * ----------------------------------
 * addi $rt, $rs, constant
 * ---------------------------------- 
 * sll $rd, $rt, constant 
 * srl $rd, $rt,constant
 * ----------------------------------- 
 * lw $rt, offset($rs) 
 * lb $rt, offset($rs)
 * lbu $rt, offset($rs) 
 * sw $rt, offset($rs) 
 * sb $rt, offset($rs)
 * ----------------------------------- 
 * lui $rt, constant
 * ----------------------------------- 
 * beq $rs, $rt, jumpLabel 
 * bne $rs, $rt, jumpLabel 
 * ----------------------------------- 
 * j jumpLabel 
 * jal jumpLabel
 * ----------------------------------- 
 * jr $rs
 */
