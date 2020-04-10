package yapl.test.backend.sm;

import java.io.FileOutputStream;
import java.io.IOException;

import yapl.impl.BackendMJ;
import yapl.interfaces.BackendBinSM;

public class Test7 {

	public static void main(String[] args) throws IOException {
		
		BackendBinSM backend = new BackendMJ();
		backend.enterProc("main", 0, true);
		int addr = backend.allocStringConstant("\n");
		backend.loadConst(-1); //load -1
		backend.neg();	// negate t0
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(1); // load 1
		backend.loadConst(5); // load 5
		backend.add();  // t1 + t0
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(6); // load 6
		backend.loadConst(2); // load 2
		backend.div();  // t1 / t0
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(3); // load 3
		backend.loadConst(2); // load 2
		backend.mod();  // t1 % t0
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(3); // load 3
		backend.allocArray(); // new Array[3]
		backend.arrayLength(); // t0 = array.length
		backend.writeInteger();
		backend.exitProc("main_end");
		
		backend.writeObjectFile(new FileOutputStream(args[0]));
		System.out.println("wrote object file to " + args[0]);
	}

}
