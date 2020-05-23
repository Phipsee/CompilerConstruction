package yapl.impl.symbolcheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import yapl.impl.parser.Token;
import yapl.interfaces.Symbol;
import yapl.interfaces.Symboltable;
import yapl.lib.*;

public class SymboltableImpl implements Symboltable{
	
	private Stack<Scope> scope = new Stack<Scope>();
	private List<Scope> allScopes = new ArrayList<Scope>();
	
	private boolean debug = false; 
	
	public SymboltableImpl() {
		openScope(true);
		try {
			SymbolImpl temp = new SymbolImpl("writeint", 1);
			temp.setType(new VoidType());
			ProcedureType type = new ProcedureType();
			type.addParam(new IntType());
			temp.setProcedureParams(type);
			addSymbol(temp, null);
			temp = new SymbolImpl("writebool", 1);
			temp.setType(new VoidType());
			type = new ProcedureType();
			type.addParam(new BoolType());
			temp.setProcedureParams(type);
			addSymbol(temp, null);
			temp = new SymbolImpl("writeln", 1);
			temp.setType(new VoidType());
			addSymbol(temp, null);
			temp = new SymbolImpl("readint", 1);
			temp.setType(new IntType());
			addSymbol(temp, null);
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
		if(scope.get(scope.size()-1).lookup(s.getName()) != null) {
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
				}else if(s.containsKey(name)) {
					symbol = s.lookup(name);
				}
			}
		}
		return symbol != null && symbol.getKind() == 0 ? null : symbol;
	}

	@Override
	public void setParentSymbol(Symbol sym, Token t) throws YAPLException {
		if(scope.get(0).containsKey(sym.getName())) {
			scope.get(0).remove(sym.getName());
		}
		if(scope.get(scope.size() >= 2 ? scope.size()-2 : 0).lookup(sym.getName()) != null || lookup(sym.getName()) != null) {
			throw new YAPLException(10, sym, t, lookup(t.toString()));
		}
		scope.get(scope.size()-1).setParent(sym);
		allScopes.get(allScopes.size()-1).setParent(sym);
	}

	@Override
	public Symbol getNearestParentSymbol(int kind) {
		Symbol symbol = null;
		for(Scope s: scope) {
			if(s.getParent() != null && s.getParent().getKind() == kind) {
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
