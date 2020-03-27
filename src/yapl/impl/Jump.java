package yapl.impl;

public class Jump {

	private byte opcode;
	
	private int addr;
	
	private String jumpLabel;
	

	public Jump(byte opcode, int addr, String jumpLabel) {
		super();
		this.opcode = opcode;
		this.addr = addr;
		this.jumpLabel = jumpLabel;
	}

	public byte getOpcode() {
		return opcode;
	}

	public void setOpcode(byte opcode) {
		this.opcode = opcode;
	}
	
	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public String getJumpLabel() {
		return jumpLabel;
	}

	public void setJumpLabel(String jumpLabel) {
		this.jumpLabel = jumpLabel;
	}
	
}
