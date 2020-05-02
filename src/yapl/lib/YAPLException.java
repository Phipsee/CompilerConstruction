package yapl.lib;

import yapl.impl.parser.Token;
import yapl.interfaces.CompilerError;
import yapl.interfaces.Symbol;

public class YAPLException extends Throwable implements CompilerError{

	private int errorNumber;
	private Symbol sym;
	private Token token;
	private Symbol found;
	
	public YAPLException() {}
	
	public YAPLException(int errorNumber, Symbol sym, Token token) {
		this.errorNumber = errorNumber;
		this.sym = sym;
		this.token = token;
	}
	
	public YAPLException(int errorNumber, Symbol sym, Token token, Symbol found) {
		this.errorNumber = errorNumber;
		this.sym = sym;
		this.token = token;
		this.found = found;
	}
	
	@Override
	public int errorNumber() {
		switch (errorNumber) {
		case 10:
			return CompilerError.SymbolExists;
		case 11:
			return CompilerError.IdentNotDecl;
		case 12:
			return CompilerError.SymbolIllegalUse;
		case 13:
			return CompilerError.EndIdentMismatch;
		}
		return 0;
	}

	@Override
	public int line() {
		return (token == null) ? -1 
	              : token.beginLine;
	}

	@Override
	public int column() {
		return (token == null) ? -1 
	              : token.beginColumn;
	}

	@Override
	public String getMessage() {
		switch (errorNumber) {
		case 10:
			return "symbol '" + sym.getName() + "' already declared in current scope (as " + found.getKindString() + ")";
		case 11:
			return "identifier '" + token.toString() + "' not declared";
		case 12:
			return "illegal use of " + sym.getKindString() + " " + sym.getName();
		case 13:
			return "End " + token.toString() + " does not match " + sym.getKindString() + " " + sym.getName();
		}
		return "unknown error number";
	}

}
