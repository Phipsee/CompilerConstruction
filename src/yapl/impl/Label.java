package yapl.impl;

public class Label {

	private String label;
	
	private byte[] addr;
	
	public Label(String label, byte[] addr) {
		super();
		this.label = label;
		this.addr = addr;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public byte[] getAddr() {
		return addr;
	}

	public void setAddr(byte[] addr) {
		this.addr = addr;
	}
	
}
