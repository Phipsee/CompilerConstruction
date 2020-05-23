package yapl.lib;

import java.util.ArrayList;
import java.util.List;

public class ProcedureType extends Type{
	
	private int parameterCount = 0;
	private List<Type> parameters = new ArrayList<Type>();
	
	
	public void addParam(Type type) {
		parameterCount++;
		parameters.add(type);
	}
	
	public boolean hasParam(int index, Type type) {
		return parameters.get(index).getClass().equals(type.getClass()) ? true : false;
	}
	
	public int getParameterCount() {
		return parameterCount;
	}
	public void setParameterCount(int parameterCount) {
		this.parameterCount = parameterCount;
	}
	public List<Type> getParameters() {
		return parameters;
	}
	public void setParameters(List<Type> parameters) {
		this.parameters = parameters;
	}

}
