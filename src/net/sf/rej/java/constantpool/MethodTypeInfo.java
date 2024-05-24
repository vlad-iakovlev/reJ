package net.sf.rej.java.constantpool;

import net.sf.rej.util.ByteSerializer;

public class MethodTypeInfo extends ConstantPoolInfo {

	private int methodTypeIndex;

	public MethodTypeInfo(int methodTypeIndex, ConstantPool pool) {
		super(METHOD_TYPE, pool);
		this.methodTypeIndex = methodTypeIndex;
	}

	@Override
	public String toString() {
		return "(method_type " + this.methodTypeIndex + ")";
	}

	@Override
	public byte[] getData() {
		ByteSerializer ser = new ByteSerializer(true);
		ser.addByte(getType());
		ser.addShort(this.methodTypeIndex);
		return ser.getBytes();
	}

	@Override
	public int hashCode() {
		int i = this.methodTypeIndex;
		return i;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) return false;

		try {
			MethodTypeInfo info = (MethodTypeInfo) other;
			return this.methodTypeIndex == info.methodTypeIndex;
		} catch (ClassCastException cce) {
			return false;
		}
	}

	@Override
	public String getTypeString() {
		return "Method type constant";
	}

}