package yapl.test.backend.sm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import yapl.impl.BackendMJ;
import yapl.interfaces.BackendBinSM;

public class Test7 {

	public static void main(String[] args) throws IOException {
		
		BackendBinSM backend = new BackendMJ();
		backend.enterProc("main", 0, true);
		int addr = backend.allocStringConstant("\n");
		backend.loadConst(-1);
		backend.neg();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(1);
		backend.loadConst(5);
		backend.add();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(6);
		backend.loadConst(2);
		backend.div();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(3);
		backend.loadConst(2);
		backend.mod();
		backend.writeInteger();
		backend.writeString(addr);
		backend.loadConst(3);
		backend.allocArray();
		backend.arrayLength();
		backend.writeInteger();
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
