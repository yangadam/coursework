package model;

import com.google.common.base.MoreObjects;

public class FileChecksum {

	private String algorithm;
	private String bytes;
	private long length;

	public String getAlgorithm() {
		return algorithm;
	}

	public String getBytes() {
		return bytes;
	}

	public long getLength() {
		return length;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("algorithm", algorithm).add("bytes", bytes).add("length", length)
				.toString();
	}
}
