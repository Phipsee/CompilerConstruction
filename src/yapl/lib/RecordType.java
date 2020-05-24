package yapl.lib;

import java.util.ArrayList;
import java.util.List;

import yapl.impl.symbolcheck.SymbolImpl;

public class RecordType extends Type {

	private List<SymbolImpl> fields = new ArrayList<SymbolImpl>();

	public void addField(SymbolImpl sym) {
		fields.add(sym);
	}

	public boolean equalField(RecordType other) {
		if (fields.size() != other.fields.size()) {
			return false;
		}
		for (int i = 0; i < fields.size(); i++) {
			if (!fields.get(i).getName().equals(other.fields.get(i).getName())) {
				return false;
			}
		}

		return true;
	}

}
