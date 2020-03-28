package yapl.test.backend.sm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import yapl.impl.BackendMJ;
import yapl.interfaces.BackendBinSM;

public class Test8 {

	public static void main(String[] args) throws IOException {
		
		BackendBinSM backend = new BackendMJ();
		backend.enterProc("main", 0, true);
		int addr = backend.allocStringConstant("\n");
		backend.loadConst(1);
		backend.loadConst(1);
		backend.and();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(0);
		backend.loadConst(1);
		backend.or();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(0);
		backend.loadConst(0);
		backend.isEqual();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(3);
		backend.loadConst(2);
		backend.isLess();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(2);
		backend.loadConst(2);
		backend.isLessOrEqual();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(3);
		backend.loadConst(2);
		backend.isGreater();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(2);
		backend.loadConst(3);
		backend.isGreaterOrEqual();
		backend.writeInteger();
		backend.writeString(addr);
		backend.exitProc("main_end");
		
		backend.writeObjectFile(new FileOutputStream(args[0]));
		System.out.println("wrote object file to " + args[0]);
        
//		Code for testing Code Coverage
//		Remove before submission
//		backend.writeObjectFile(new OutputStream() {
//			
//			@Override
//			public void write(int b) throws IOException {
//				System.out.println(b);
//			}
//		});
	}

}
