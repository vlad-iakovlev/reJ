package net.sf.rej.java.constantpool;

import net.sf.rej.util.ByteSerializer;

public class DynamicInfo extends ConstantPoolInfo {

	private int tag;
	private int bootstrapMethodAttrIndex;
	private int nameAndTypeIndex;

	public DynamicInfo(int tag, int bootstrapMethodAttrIndex, int nameAndTypeIndex, ConstantPool pool) {
		super(tag, pool);
		this.tag = tag;
		this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	@Override
	public String toString() {
		return "(dynamic " + this.bootstrapMethodAttrIndex + " " + this.nameAndTypeIndex + ")";
	}

	@Override
	public byte[] getData() {
		ByteSerializer ser = new ByteSerializer(true);
		ser.addByte(getType());
		ser.addShort(this.bootstrapMethodAttrIndex);
		ser.addShort(this.nameAndTypeIndex);
		return ser.getBytes();
	}

	@Override
	public int hashCode() {
		int i = this.bootstrapMethodAttrIndex;
		i += this.nameAndTypeIndex;
		return i;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) return false;

		try {
			DynamicInfo info = (DynamicInfo) other;
			if (this.tag != info.tag) return false;
			if (this.bootstrapMethodAttrIndex != info.bootstrapMethodAttrIndex) return false;
			if (this.nameAndTypeIndex != info.nameAndTypeIndex) return false;
			return true;
		} catch (ClassCastException cce) {
			return false;
		}
	}

	@Override
	public String getTypeString() {
        switch (this.tag) {
        case DYNAMIC:
            return "Dynamic constant";
        case INVOKE_DYNAMIC:
            return "Invoke dynamic constant";
        default:
            throw new RuntimeException("Internal error, undefined Ref type = " + this.tag);
        }
	}

}