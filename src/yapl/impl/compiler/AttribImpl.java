package yapl.impl.compiler;

import yapl.interfaces.Attrib;
import yapl.lib.Type;

public class AttribImpl implements Attrib {

	private byte kind;
	private Type type;
	private boolean constant;
	private boolean global;
	private boolean readonly;
	private int offset;
	private byte register;
	
	private int integer;

	@Override
	public byte getKind() {
		return kind;
	}

	@Override
	public void setKind(byte kind) {
		this.kind = kind;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public boolean isConstant() {
		return constant;
	}

	@Override
	public void setConstant(boolean isConstant) {
		this.constant = isConstant;

	}

	@Override
	public boolean isReadonly() {
		return readonly;
	}

	@Override
	public void setReadonly(boolean isReadonly) {
		this.readonly = isReadonly;
	}

	@Override
	public boolean isGlobal() {
		return global;
	}

	@Override
	public void setGlobal(boolean isGlobal) {
		this.global = isGlobal;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public byte getRegister() {
		return register;
	}

	@Override
	public void setRegister(byte register) {
		this.register = register;
	}

	@Override
	public void setInteger(int i) {
		integer = i;
	}

	@Override
	public int getInteger() {
		return integer;
	}

}
