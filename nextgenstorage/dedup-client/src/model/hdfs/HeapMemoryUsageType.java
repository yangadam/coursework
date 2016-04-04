package model.hdfs;

import com.google.gson.GsonBuilder;

import util.commons.Unit;

public class HeapMemoryUsageType {
	private long committed;
	private long max;
	private long used;

	public long getCommitted() {
		return committed / Unit.Storage.MB;
	}
	public long getMax() {
		return max / Unit.Storage.MB;
	}
	public long getUsed() {
		return used / Unit.Storage.MB;
	}

	@Override
	public String toString() {
		return new GsonBuilder().create().toJson(this);
	}
}