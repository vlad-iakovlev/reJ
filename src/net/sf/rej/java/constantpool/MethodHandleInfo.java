package net.sf.rej.java.constantpool;

import net.sf.rej.util.ByteSerializer;

public class MethodHandleInfo extends ConstantPoolInfo {

	private int referenceKind;
	private int referenceIndex;

	public MethodHandleInfo(int referenceKind, int referenceIndex, ConstantPool pool) {
		super(METHOD_HANDLE, pool);
		this.referenceKind = referenceKind;
		this.referenceIndex = referenceIndex;
	}

	@Override
	public String toString() {
		return "(method_handle " + this.referenceKind + " " + this.referenceIndex + ")";
	}

	public String getMethodHandleString() {
		return this.pool.get(this.referenceIndex).getValue();
	}

	@Override
	public byte[] getData() {
		ByteSerializer ser = new ByteSerializer(true);
		ser.addByte(getType());
		ser.addByte(this.referenceKind);
		ser.addShort(this.referenceIndex);
		return ser.getBytes();
	}

	@Override
	public int hashCode() {
		int i = getMethodHandleString().hashCode();
		i += this.referenceKind;
		i += this.referenceIndex;
		return i;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) return false;

		try {
			MethodHandleInfo info = (MethodHandleInfo) other;
			if (this.referenceKind != info.referenceKind) return false;
			if (this.referenceIndex != info.referenceIndex) return false;
			return true;
		} catch (ClassCastException cce) {
			return false;
		}
	}

	@Override
	public String getTypeString() {
		return "Method handle constant";
	}

}