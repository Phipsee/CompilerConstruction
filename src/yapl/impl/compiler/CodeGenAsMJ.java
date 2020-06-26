package yapl.impl.compiler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import yapl.impl.parser.Token;
import yapl.interfaces.Attrib;
import yapl.interfaces.BackendBinSM;
import yapl.interfaces.CodeGen;
import yapl.interfaces.MemoryRegion;
import yapl.interfaces.Symbol;
import yapl.lib.ArrayType;
import yapl.lib.RecordType;
import yapl.lib.YAPLException;

public class CodeGenAsMJ implements CodeGen {

	private static final String LABEL_IDENTIFIER = "L";

	private BackendBinSM backend;
	private int labelId = 1;
	
	private FileOutputStream outfile;

	@Override
	public String newLabel() {
		return LABEL_IDENTIFIER + labelId++;
	}

	@Override
	public void assignLabel(String label) {
		backend.assignLabel(label);
	}

	@Override
	public byte loadValue(Attrib attr) throws YAPLException {
		backend.loadConst(attr.getInteger());
		return 0;
	}

	@Override
	public byte loadAddress(Attrib attr) throws YAPLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void freeReg(Attrib attr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void allocVariable(Symbol sym) throws YAPLException {
//		if (sym.isGlobal()) {
			sym.setOffset(backend.allocStaticData(1)/4);
//		} else {
//			sym.setOffset(backend.paramOffset(backend.allocStack(1)));
//		}
	}

	@Override
	public void setFieldOffsets(RecordType record) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeArrayDim(int dim, Attrib length) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Attrib allocArray(ArrayType arrayType) throws YAPLException {

		return null;
	}

	@Override
	public Attrib allocRecord(RecordType recordType) throws YAPLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParamOffset(Symbol sym, int pos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void arrayOffset(Attrib arr, Attrib index) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void recordOffset(Attrib record, Symbol field) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Attrib arrayLength(Attrib arr) throws YAPLException {
		return arr;
	}

	@Override
	public void assign(Attrib lvalue, Attrib expr) throws YAPLException {
		if (lvalue.getKind() != expr.getKind()) {
			throw new YAPLException(YAPLException.TypeMismatchAssign, null, null);
		}

	}

	@Override
	public Attrib op1(Token op, Attrib x) throws YAPLException {
		switch (op.toString()) {
			case "-":
				backend.neg();
			default:
				break;
		}
		return null;
	}

	@Override
	public Attrib op2(Attrib x, Token op, Attrib y) throws YAPLException {
		switch (op.toString()) {
			case "*":
				backend.mul();
				break;
			case "/":
				backend.div();
				break;
			case "%":
				backend.mod();
				break;
			case "+":
				backend.add();
				break;
			case "-":
				backend.sub();
				break;
			case "And":
				backend.and();
				break;
			case "Or":
				backend.or();
				break;
			case "==":
				backend.isEqual();
				break;
			case "<":
				backend.isLess();
				break;
			case "<=":
				backend.isLessOrEqual();
				break;
			case ">=":
				backend.isGreaterOrEqual();
				break;
			case ">":
				backend.isGreater();
				break;
			default:
				break;
		}	
		return null;
	}

	@Override
	public Attrib relOp(Attrib x, Token op, Attrib y) throws YAPLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attrib equalOp(Attrib x, Token op, Attrib y) throws YAPLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enterProc(Symbol proc) throws YAPLException {
		if(proc.getName().contains("test")) {
			backend.enterProc(proc.getName(), 0, true);
		}else {
			// not main funtion
		}
	}

	@Override
	public void exitProc(Symbol proc) throws YAPLException {
		backend.exitProc(proc.getName());
	}

	@Override
	public void returnFromProc(Symbol proc, Attrib returnVal) throws YAPLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Attrib callProc(Symbol proc, List<Attrib> args) throws YAPLException {
		backend.callProc(proc.getName());
		return null;
	}

	@Override
	public void writeString(String string) throws YAPLException {
		// If String consists only of double quotes, it is empty
		if (string.length() <= 2) {
			return;
		}
		String pureString = string.substring(1, string.length() - 1);
		backend.writeString(backend.allocStringConstant(pureString));
	}

	@Override
	public void branchIfFalse(Attrib condition, String label) throws YAPLException {
		boolean cond = loadValue(condition) == 0 ? false : true;
		backend.branchIf(cond, label);

	}

	@Override
	public void jump(String label) {
		backend.jump(label);
	}

	public CodeGenAsMJ(BackendBinSM backend) {
		this.backend = backend;
	}
	
	@Override
	public void writeObjectFile() throws IOException {
		backend.writeObjectFile(outfile);
	}
	
	@Override
	public void setOutFile(FileOutputStream outfile) {
		this.outfile = outfile;
	}

	@Override
	public void storeVariable(Symbol sym) {
		backend.storeWord(MemoryRegion.STATIC, sym.getOffset());
	}

	@Override
	public void loadVariable(Symbol sym) {
		backend.loadWord(MemoryRegion.STATIC, sym.getOffset());
	}

}
