package yapl.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yapl.interfaces.BackendBinSM;
import yapl.interfaces.MemoryRegion;

public class BackendMJ implements BackendBinSM{

	private byte[] magicMarker = {0x4d, 0x4a};
	private int codeSize = 0;
	private int dataSize = 0;
	private int startPC = 0;
	
	private ArrayList<Byte> code = new ArrayList<Byte>();
	private byte[] data = new byte[0];
	
	private ArrayList<Label> labels = new ArrayList<Label>();
	private ArrayList<Jump> jumps = new ArrayList<Jump>();
	
	private int currSP = 0;
	
	public BackendMJ() {
		// writeint(int i)
		enterProc("writeint", 1, false);
		loadWord(MemoryRegion.STACK, 0);
		writeInteger();
		exitProc("writeint_end");
		// writebool(bool b)
		enterProc("writebool", 1, false);
		loadWord(MemoryRegion.STACK, 0);
		branchIf(false, "false");
		int addr = allocStringConstant("True");
		writeString(addr);
		jump("writebool_end");
		assignLabel("false");
		addr = allocStringConstant("False");
		writeString(addr);
		exitProc("writebool_end");
		// writeln()
		enterProc("writeln", 0, false);
		addr = allocStringConstant("\n");
		writeString(addr);
		exitProc("writeln_end");
		//readint()
		enterProc("readint", 0, false);
		code.add((byte) 0x32);
		codeSize++;
		exitProc("readint_end");
	}

	/**
	 * Generates the header for the output file
	 * @return generated header
	 * @throws IOException
	 */
	private byte[] generateHeader() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		output.write(magicMarker);
		output.write(intToByteArray(codeSize));
		output.write(intToByteArray(dataSize));
		output.write(intToByteArray(startPC));
		return output.toByteArray();
	}
	
	/**
	 * Converts a List to an Array
	 * @param List to be converted
	 * @return generated Array
	 */
	private byte[] convertListToArray(List<Byte> input) {
		byte[] output = new byte[input.size()];
		for(int i = 0; i < input.size(); i++) {
			output[i] = input.get(i);
		}
		return output;
	}
	
	/**
	 * Converts int to byte[4]
	 * @param input
	 * @return byte Array
	 */
	private byte[] intToByteArray(int input) {
		BigInteger bi = BigInteger.valueOf(input);
		byte[] temp = bi.toByteArray();
		byte[] output = new byte[4]; 
		for(int i = temp.length-1, k = 3; i >= 0; i--, k--) {
			output[k] = temp[i];
		}
		return output;
	}
	
	/**
	 * Inserts the correct addresses for the jump instructions
	 */
	private void backpatching() {
		for(Jump j: jumps) {
			for(Label l: labels) {
				if(j.getJumpLabel().equals(l.getLabel())) {
					code.set(j.getAddr()+1, l.getAddr()[2]);
					code.set(j.getAddr()+2, l.getAddr()[3]);
					break;
				}
			}
		}
	}
	
	/**
	 * Sets the correct framesize
	 * @param label
	 */
	private void setFramesize(String label) {
		for(Label l: labels) {
			if(label.contains(l.getLabel())) {
				code.set(ByteBuffer.wrap(l.getAddr()).getInt()+2, intToByteArray(currSP+1)[3]);
				break;
			}
		}
	}
	
	/**
	 * Emits code for comparison Operation jumps
	 */
	private void compOpJump() {
		loadConst(0);
		byte[] temp = intToByteArray(codeSize+8);
		code.add((byte) 0x27);
		code.add(temp[2]);
		code.add(temp[3]);
		codeSize += 3;
		loadConst(1);
	}
	
	/*--- implementation constants ---*/
	
	@Override
	public int wordSize() {
		return 4;
	}

	@Override
	public int boolValue(boolean value) {
		return value ? 1 : 0;
	}

	/*--- address labels ---*/
	
	@Override
	public void assignLabel(String label) {
		labels.add(new Label(label, intToByteArray(codeSize)));
	}

	/*--- object file I/O ---*/
	
	@Override
	public void writeObjectFile(OutputStream outStream) throws IOException {
		backpatching();
		outStream.write(generateHeader());
		outStream.write(convertListToArray(code));
		outStream.write(data);
		outStream.flush();
		outStream.close();
	}

	/*--- compile-time memory allocation ---*/
	
	@Override
	public int allocStaticData(int words) {
		int addr = data.length;
		data = Arrays.copyOf(data, data.length + (words * wordSize()));
		//data = new byte[data.length + (words * wordSize())];
		return addr;
	}

	@Override
	public int allocStringConstant(String string) {
		int addr = allocStaticData((int) Math.ceil((double) (string.length() + 1) / wordSize()));
		for(int i = addr; i < addr + string.length(); i++) {
			data[i] = (byte) string.charAt(i-addr);
		}
		dataSize = data.length / 4;
		return addr;
	}

	@Override
	public int allocStack(int words) {
		currSP += words;
		return currSP;
	}

	/*--- run-time memory allocation ---*/
	
	@Override
	public void allocHeap(int words) {
		code.add((byte) 0x1f);
		byte[] temp = intToByteArray(words);
		code.add((byte) temp[2]);
		code.add((byte) temp[3]);
		codeSize += 3;
	}

	@Override
	public void storeArrayDim(int dim) {
		
	}

	@Override
	public void allocArray(int i) {
		code.add((byte) 0x20);
		code.add(i == 0 ? (byte) 0x00 : (byte) 0x01);
		codeSize += 2;
	}

	/*--- load/store operations ---*/
	
	@Override
	public void loadConst(int value) {
		if(value > -1) { 
			code.add((byte) 0x16);
			byte[] temp = intToByteArray(value); 
			code.add(temp[0]);
			code.add(temp[1]);
			code.add(temp[2]);
			code.add(temp[3]);
			codeSize += 5;
		} else {
			code.add((byte) 0x15);
			codeSize++;
		}
	}

	@Override
	public void loadWord(MemoryRegion region, int offset) {
		byte[] temp = intToByteArray(offset);
		if(region == MemoryRegion.STATIC) {
			code.add((byte) 0x0b);
			code.add((byte) temp[2]);
			code.add((byte) temp[3]);
			codeSize += 3;
		}else if(region == MemoryRegion.STACK) {
			code.add((byte) 0x01);
			code.add(temp[3]);
			codeSize += 2;
		} else if(region == MemoryRegion.HEAP) {
			code.add((byte) 0x0d);
			code.add((byte) temp[2]);
			code.add((byte) temp[3]);
			codeSize += 3;
		}
	}

	@Override
	public void storeWord(MemoryRegion region, int offset) {
		byte[] temp = intToByteArray(offset);
		if(region == MemoryRegion.STATIC) {
			code.add((byte) 0x0c);
			code.add((byte) temp[2]);
			code.add((byte) temp[3]);
			codeSize += 3;
		} else if(region == MemoryRegion.STACK) {
			code.add((byte) 0x06);
			code.add((byte) temp[3]);
			codeSize += 2;
		} else if(region == MemoryRegion.HEAP) {
			code.add((byte) 0x0e);
			code.add((byte) temp[2]);
			code.add((byte) temp[3]);
			codeSize += 3;
		}
	}

	@Override
	public void loadArrayElement() {
		code.add((byte) 0x21);
		codeSize++;
	}

	@Override
	public void storeArrayElement() {
		code.add((byte) 0x22);
		codeSize++;
	}

	@Override
	public void arrayLength() {
		code.add((byte) 0x25);
		codeSize++;
	}

	/*--- run-time I/O operations ---*/
	
	@Override
	public void writeInteger() {
		code.add((byte) 0x0f);
		code.add((byte) 0x33);
		codeSize += 2;
	}

	@Override
	public void writeString(int addr) {
		code.add((byte) 0x37);
		byte[] input = intToByteArray(addr/4);
		code.add(input[2]);
		code.add(input[3]);
		codeSize += 3;
	}

	/*--- arithmetic operations ---*/
	
	@Override
	public void neg() {
		code.add((byte) 0x1c);
		codeSize++;
	}

	@Override
	public void add() {
		code.add((byte) 0x17);
		codeSize++;
	}

	@Override
	public void sub() {
		code.add((byte) 0x18);
		codeSize++;
	}

	@Override
	public void mul() {
		code.add((byte) 0x19);
		codeSize++;
	}

	@Override
	public void div() {
		code.add((byte) 0x1a);
		codeSize++;
	}

	@Override
	public void mod() {
		code.add((byte) 0x1b);
		codeSize++;
	}

	/*--- logical operations ---*/
	
	@Override
	public void and() {
		mul();
	}

	@Override
	public void or() {
		add();
		loadConst(1);
		isGreaterOrEqual();
	}

	/*--- comparison operations ---*/
	
	@Override
	public void isEqual() {
		code.add((byte) 0x28);
		byte[] temp = intToByteArray(codeSize+11);
		code.add(temp[2]);
		code.add(temp[3]);
		codeSize += 3;
		compOpJump();
	}

	@Override
	public void isLess() {
		code.add((byte) 0x2a);
		byte[] temp = intToByteArray(codeSize+11);
		code.add(temp[2]);
		code.add(temp[3]);
		codeSize += 3;
		compOpJump();
	}

	@Override
	public void isLessOrEqual() {
		code.add((byte) 0x2b);
		byte[] temp = intToByteArray(codeSize+11);
		code.add(temp[2]);
		code.add(temp[3]);
		codeSize += 3;
		compOpJump();
	}

	@Override
	public void isGreater() {
		code.add((byte) 0x2c);
		byte[] temp = intToByteArray(codeSize+11);
		code.add(temp[2]);
		code.add(temp[3]);
		codeSize += 3;
		compOpJump();
	}

	@Override
	public void isGreaterOrEqual() {
		code.add((byte) 0x2d);
		byte[] temp = intToByteArray(codeSize+11);
		code.add(temp[2]);
		code.add(temp[3]);
		codeSize += 3;
		compOpJump();
	}

	/*--- jump instructions ---*/
	
	@Override
	public void branchIf(boolean value, String label) {
		loadConst(boolValue(value));
		jumps.add(new Jump((byte) 0x28, codeSize, label)); 
		code.add((byte) 0x28);
		code.add((byte) 0);
		code.add((byte) 0);
		codeSize += 3;
	}

	@Override
	public void jump(String label) {
		jumps.add(new Jump((byte) 0x27, codeSize, label));
		code.add((byte) 0x27);
		code.add((byte) 0);
		code.add((byte) 0);
		codeSize += 3;
	}

	/*--- procedure calls ---*/
	
	@Override
	public void callProc(String label) {
		jumps.add(new Jump((byte) 0x2e, codeSize, label));
		code.add((byte) 0x2e);
		code.add((byte) 0);
		code.add((byte) 0);
		codeSize += 3;
	}

	@Override
	public void enterProc(String label, int nParams, boolean main) {
		currSP = nParams - 1;
		labels.add(new Label(label, intToByteArray(codeSize)));
		if(main) {
			startPC = codeSize;
		}
		code.add((byte) 0x30);
		byte[] params = intToByteArray(nParams);
		code.add(params[3]);
		code.add(params[3]);
		codeSize += 3;
	}

	@Override
	public void exitProc(String label) {
		setFramesize(label);
		currSP = 0;
		labels.add(new Label(label, intToByteArray(codeSize)));
		code.add((byte) 0x31);
		code.add((byte) 0x2f);
		codeSize += 2;
	}

	@Override
	public int paramOffset(int index) {
		return index;
	}

}
