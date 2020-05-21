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
		/* Symbol check errors */
		case 10:
			return CompilerError.SymbolExists;
		case 11:
			return CompilerError.IdentNotDecl;
		case 12:
			return CompilerError.SymbolIllegalUse;
		case 13:
			return CompilerError.EndIdentMismatch;
		/* Type check errors */
		case 20:
			return CompilerError.SelectorNotArray;
		case 21:
			return CompilerError.BadArraySelector;
		case 22:
			return CompilerError.ArrayLenNotArray;
		case 23:
			return CompilerError.IllegalOp1Type;
		case 24:
			return CompilerError.IllegalOp2Type;
		case 25:
			return CompilerError.IllegalRelOpType;
		case 26:
			return CompilerError.IllegalEqualOpType;
		case 27:
			return CompilerError.ProcNotFuncExpr;
		case 28:
			return CompilerError.ReadonlyAssign;
		case 29:
			return CompilerError.TypeMismatchAssign;
		case 30:
			return CompilerError.ArgNotApplicable;
		case 31:
			return CompilerError.ReadonlyArg;
		case 32:
			return CompilerError.TooFewArgs;
		case 33:
			return CompilerError.CondNotBool;
		case 34:
			return CompilerError.ReadonlyNotReference;
		case 35:
			return CompilerError.MissingReturn;
		case 36:
			return CompilerError.InvalidReturnType;
		case 37:
			return CompilerError.IllegalRetValProc;
		case 38:
			return CompilerError.IllegalRetValMain;
		case 39:
			return CompilerError.SelectorNotRecord;
		case 40:
			return CompilerError.InvalidRecordField;
		case 41:
			return CompilerError.InvalidNewType;
		/* Code generation errors */
		case 50:
			return CompilerError.NoMoreRegs;
		case 51:
			return CompilerError.TooManyDims;
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
		/* Symbol check error messages*/
		case 10:
			return "symbol '" + sym.getName() + "' already declared in current scope (as " + found.getKindString() + ")";
		case 11:
			return "identifier '" + token.toString() + "' not declared";
		case 12:
			return "illegal use of " + sym.getKindString() + " '" + sym.getName() + "'";
		case 13:
			return "End " + token.toString() + " does not match " + sym.getKindString() + " " + sym.getName();
		/* Type check error messages */
		case 20:
			return "expression before '[' is not an array type";
		case 21:
			return "array index or dimension is not an integer type";
		case 22:
			return "expression after '#' is not an array type";
		case 23:
			return "illegal operand type for unary operator";
		case 24:
			return "illegal operand type for binary operator";
		case 25:
			return "illegal operand type for relational operator";
		case 26:
			return "illegal operand type for equality operator";
		case 27:
			return "using procedure (not a function) in expression";
		case 28:
			return "read-only l-value in assignment";
		case 29:
			return "type mismatch in assignment";
		case 30:
			return "argument not applicable to procedure";
		case 31:
			return "argument not applicable to procedure";
		case 32:
			return "too few arguments for procedure";
		case 33:
			return "condition is not a boolean expression";
		case 34:
			return "readonly not followed by reference type";
		case 35:
			return "missing return statement in function";
		case 36:
			return "returning none or invalid type from function";
		case 37:
			return "illegal return value in procedure (not a function)";
		case 38:
			return "illegal return value in main program";
		case 39:
			return "expression before '.' is not a record type";
		case 40:
			return "invalid field of record";
		case 41:
			return "invalid type used with 'new' operator";
		/* Code generation errors */
		case 50:
			return "too many registers used";
		case 51:
			return "too many array dimensions";
		}		
		return "unknown error number";
	}

}
