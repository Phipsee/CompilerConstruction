package yapl.impl.symbolcheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import yapl.impl.parser.Token;
import yapl.interfaces.Symbol;
import yapl.interfaces.Symboltable;
import yapl.lib.YAPLException;

public class SymboltableImpl implements Symboltable{
	
	private Stack<Scope> scope = new Stack<Scope>();
	private List<Scope> allScopes = new ArrayList<Scope>();
	
	private boolean debug = false;
	
	public SymboltableImpl() {
		openScope(true);
		try {
			addSymbol(new SymbolImpl("writeint", 1), null);
			addSymbol(new SymbolImpl("writebool", 1), null);
			addSymbol(new SymbolImpl("writeln", 1), null);
			addSymbol(new SymbolImpl("readint", 1), null);
		} catch (YAPLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void openScope(boolean isGlobal) {
		scope.push(new Scope(new HashMap<String, Symbol>(), isGlobal));
		allScopes.add(scope.get(scope.size()-1));
	}

	@Override
	public void closeScope() {
		Scope s = scope.pop();
		if(!s.isGlobal()) {
			allScopes.remove(allScopes.size()-1);
		}
	}

	@Override
	public void addSymbol(Symbol s, Token t) throws YAPLException {
		if(scope.get(scope.size()-1).lookup(s.getName()) != null && lookup(t.toString()).getKind() != 0) {
			throw new YAPLException(10, s, t, lookup(t.toString()));
		}
		scope.get(scope.size()-1).put(s.getName(), s);
	}

	@Override
	public Symbol lookup(String name) throws YAPLException {
		if(name == null) {
			throw new YAPLException();
		}
		Symbol symbol = null;
		for(Scope s: scope) {
			if(s.containsKey(name)) {
				symbol = s.lookup(name);
			}	
		}
		if(symbol == null) {
			for(Scope s: allScopes) {
				if(s.getParent() != null && s.getParent().getName().equals(name)) {
					symbol = s.getParent();
				}
			}
		}
		return symbol;
	}

	@Override
	public void setParentSymbol(Symbol sym) {
		scope.get(scope.size()-1).setParent(sym);
		allScopes.get(allScopes.size()-1).setParent(sym);
	}

	@Override
	public Symbol getNearestParentSymbol(int kind) {
		Symbol symbol = null;
		for(Scope s: scope) {
			if(s.getParent().getKind() == kind) {
				symbol = s.getParent();
			}
		}
		return symbol;
	}

	@Override
	public void setDebug(boolean on) {
		debug = on;
	}

}
