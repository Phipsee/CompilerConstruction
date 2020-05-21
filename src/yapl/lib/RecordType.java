package yapl.lib;

import java.util.ArrayList;
import java.util.List;

import yapl.impl.symbolcheck.SymbolImpl;

public class RecordType extends Type {

	private List<SymbolImpl> fields = new ArrayList<SymbolImpl>();
	
	void addField(SymbolImpl sym) {
		fields.add(sym);
	}
	

	
}
