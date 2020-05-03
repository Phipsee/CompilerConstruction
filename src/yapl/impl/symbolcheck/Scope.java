package yapl.impl.symbolcheck;

import java.util.HashMap;

import yapl.interfaces.Symbol;

public class Scope {

	private HashMap<String, Symbol> symbols;
	private boolean isGlobal;
	private Symbol parent;
	
	public Scope(HashMap<String, Symbol> symbols, boolean isGlobal) {
		super();
		this.symbols = symbols;
		this.isGlobal = isGlobal;
	}
	
	public void put(String key, Symbol value) {
		symbols.put(key, value);
	}
	
	public Symbol lookup(String key) {
		return symbols.get(key);
	}

	public boolean containsKey(String key) {
		return symbols.containsKey(key);
	}
	
	public Symbol getParent() {
		return parent;
	}

	public void setParent(Symbol parent) {
		this.parent = parent;
	}

	public boolean isGlobal() {
		return isGlobal;
	}

	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
	}
	
	public void remove(String name) {
		symbols.remove(name);
	}
}
