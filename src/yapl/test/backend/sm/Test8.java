package yapl.test.backend.sm;

import java.io.FileOutputStream;
import java.io.IOException;

import yapl.impl.BackendMJ;
import yapl.interfaces.BackendBinSM;

public class Test8 {

	public static void main(String[] args) throws IOException {
		
		BackendBinSM backend = new BackendMJ();
		backend.enterProc("main", 0, true);
		int addr = backend.allocStringConstant("\n");
		backend.loadConst(1); // load 1
		backend.loadConst(1); // load 1
		backend.and();  // t1 & t0
		backend.writeInteger(); 
		backend.writeString(addr);
		backend.loadConst(1); // load 0
		backend.loadConst(1); // load 1
		backend.or(); // t1 | t0
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(0); // load 0
		backend.loadConst(0); // load 1
		backend.isEqual(); // t1 == t0
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(3); // load 3
		backend.loadConst(2); // load 2
		backend.isLess(); // t1 < t0
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(2); // load 2
		backend.loadConst(2); // load 2
		backend.isLessOrEqual(); // t1 <= t0
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(3); // load 3
		backend.loadConst(2); // load 2
		backend.isGreater(); // t1 > t0
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(2); // load 2
		backend.loadConst(3); // load 3
		backend.isGreaterOrEqual();  // t1 >= t0
		backend.writeInteger();
		backend.exitProc("main_end");
		
		backend.writeObjectFile(new FileOutputStream(args[0]));
		System.out.println("wrote object file to " + args[0]);
	}

}
