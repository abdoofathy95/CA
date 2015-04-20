package components;

import java.util.Hashtable;

public class ControlUnit {
	
	private static final String signalRegDst = "RegDst";
	private static final String signalBranch = "Branch";
	private static final String signalBNE = "BNE";
	private static final String signalMemRead = "memRead";
	private static final String signalMemToReg = "memToReg";
	private static final String signalAluOperationAdd = "Add"; // alu add
	private static final String signalAluOperationSub = "Sub"; // alu sub 
	private static final String signalAluOperationAnd = "And"; // alu and
	private static final String signalAluOperationSLL = "SLL"; // alu sll
	private static final String signalAluOperationSRL = "SRL"; // alu srl
	private static final String signalAluOperationNOR = "NOR"; // alu nor 
	private static final String signalAluOperationJR = "JR"; // alu jr
	private static final String signalAluOperationSLT = "SLT"; // alu slt
	private static final String signalAluOperationSLTU = "SLTU"; // alu sltu
	private static final String signalMemWrite = "MemWrite";
	private static final String signalAluSrc = "AluSrc";
	private static final String signalRegWrite = "RegWrite";
	private static final String signalJump = "Jump";
	private static final String signalJumpR = "JumpR";
	private static final String signalJAL = "JAL";
	private static final String signalSignExtend = "SignExtend";
	private static final String signalZeroExtend = "ZeroExtend";
	private static final String signalShiftLeft = "ShiftLeft";
	
	private final static Hashtable<String,String[]> signals = new Hashtable<String,String[]>();
	
	private static final String [] addSignals = {signalRegDst,signalAluOperationAdd,signalRegWrite};
	private static final String [] subSignals = {signalRegDst,signalAluOperationSub,signalRegWrite};
	private static final String [] andSignals = {signalRegDst,signalAluOperationAnd,signalRegWrite};
	private static final String [] lwSignals = {signalMemRead,signalAluOperationAdd,signalMemToReg,signalAluSrc,signalRegWrite};
	private static final String [] swSignals = {signalMemWrite,signalAluOperationAdd,signalAluSrc};
	private static final String [] beqSignals = {signalBranch,signalAluOperationSub};
	private static final String [] addiSignals = {signalAluSrc,signalRegWrite,signalAluOperationAdd};
	private static final String [] lbSignals = {signalMemRead,signalMemToReg,signalAluOperationAdd,signalAluSrc,signalRegWrite,signalSignExtend};
	private static final String [] lbuSignals = {signalMemRead,signalMemToReg,signalAluOperationAdd,signalAluSrc,signalRegWrite,signalZeroExtend};
	private static final String [] sbSignals = {signalAluOperationAdd,signalMemWrite,signalAluSrc};
	private static final String [] luiSignals = {signalAluSrc,signalRegWrite,signalShiftLeft};
	private static final String [] sllSignals = {signalRegDst,signalAluOperationSLL,signalRegWrite};
	private static final String [] srlSignals = {signalRegDst,signalAluOperationSRL,signalRegWrite};
	private static final String [] norSignals = {signalRegDst,signalAluOperationNOR,signalRegWrite};
	private static final String [] bneSignals = {signalBNE,signalAluOperationSub};
	private static final String [] jSignals = {signalJump};
	private static final String [] jalSignals = {signalJump,signalRegWrite,signalJAL};
	private static final String [] jrSignals = {signalJumpR,signalAluOperationJR};
	private static final String [] sltSignals = {signalRegDst,signalAluOperationSLT,signalRegWrite};
	private static final String [] sltuSignals = {signalRegDst,signalAluOperationSLTU,signalRegWrite};
	
	public static String[] getSignalsOfInstruction(String instruction) {
		return signals.get(instruction);
	}
	public void initControlUnit() {
		signals.put("add", addSignals);
		signals.put("sub", subSignals);
		signals.put("and", andSignals);
		signals.put("lw", lwSignals);
		signals.put("sw", swSignals);
		signals.put("beq", beqSignals);
		signals.put("addi", addiSignals);
		signals.put("lb", lbSignals);
		signals.put("lbu", lbuSignals);
		signals.put("sb", sbSignals);
		signals.put("lui", luiSignals);
		signals.put("sll", sllSignals);
		signals.put("srl", srlSignals);
		signals.put("nor", norSignals);
		signals.put("bne", bneSignals);
		signals.put("j", jSignals);
		signals.put("jal", jalSignals);
		signals.put("jr", jrSignals);
		signals.put("slt", sltSignals);
		signals.put("sltu", sltuSignals);
	}
}
