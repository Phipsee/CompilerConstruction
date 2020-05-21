package yapl.lib;

public class ArrayType extends Type {
	
	private int dim;
	private Type elem;
	
	public ArrayType(int dim, Type elem) {
		super();
		this.dim = dim;
		this.elem = elem;
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}

	public Type getElem() {
		return elem;
	}

	public void setElem(Type elem) {
		this.elem = elem;
	}
	
	public void incDim() {
		this.dim++;
	}
	
	public Type getSubarray() {
		if(dim - 1 > 0) {
			return new ArrayType(dim-1, elem);
		}else {
			return elem;
		}
	}
	
	
}
