package yapl.impl.symbolcheck;

import yapl.interfaces.Symbol;
import yapl.lib.ProcedureType;
import yapl.lib.Type;

public class SymbolImpl implements Symbol {

	private int kind;
	private String identifier;
	private Type type;
	private boolean isReference;
	private boolean isReadOnly;
	private boolean isGlobal = true;
	private int offset;
	private Symbol nextSymbol;
	private boolean seen;
	private Type procedureParams = new ProcedureType();

	
	public SymbolImpl() {}
	
	public SymbolImpl(String name, int kind) {
		this.identifier = name;
		this.kind = kind;
	}
	
	@Override
	public int getKind() {
		return kind;
	}

	@Override
	public String getKindString() {
		switch (kind) {
		case 0:
			return "program";
		case 1:
			return "procedure";
		case 2:
			return "variable";
		case 3:
			return "constant";
		case 4:
			return "typename";
		case 5:
			return "field";
		case 6:
			return "parameter";
		}
		return "";
	}

	@Override
	public void setKind(int kind) {
		this.kind = kind;
	}

	@Override
	public String getName() {
		return identifier;
	}
	
	public void setName(String name) {
		this.identifier = name;
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
	public boolean isReference() {
		return isReference;
	}

	@Override
	public void setReference(boolean isReference) {
		this.isReference = isReference;
	}

	@Override
	public boolean isReadonly() {
		return isReadOnly;
	}

	@Override
	public void setReadonly(boolean isReadonly) {
		this.isReadOnly = isReadonly;
	}

	@Override
	public boolean isGlobal() {
		return isGlobal;
	}

	@Override
	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
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
	public Symbol getNextSymbol() {
		return nextSymbol != null ? nextSymbol : null;
	}

	@Override
	public void setNextSymbol(Symbol symbol) {
		this.nextSymbol = symbol;
	}

	@Override
	public boolean getReturnSeen() {
		return seen;
	}

	@Override
	public void setReturnSeen(boolean seen) {
		this.seen = seen;
	}

	@Override
	public String toString() {
		return "SymbolImpl [kind=" + kind + ", identifier=" + identifier + ", type=" + type + ", isReference="
				+ isReference + ", isReadOnly=" + isReadOnly + ", isGlobal=" + isGlobal + ", offset=" + offset
				+ ", nextSymbol=" + nextSymbol + ", seen=" + seen + "]";
	}

	public Type getProcedureParams() {
		return procedureParams;
	}

	public void setProcedureParams(Type procedureParams) {
		this.procedureParams = procedureParams;
	}
	
}
