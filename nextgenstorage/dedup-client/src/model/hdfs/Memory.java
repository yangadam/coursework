package model.hdfs;

import com.google.gson.GsonBuilder;

public class Memory {
	private String name;
	private String modelerType;
	private boolean Verbose;
	private int ObjectPendingFinalizationCount;
	private HeapMemoryUsageType HeapMemoryUsage;
	private NonHeapMemoryUsageType NonHeapMemoryUsage;
	private String ObjectName;

	public String getName() {
		return name;
	}
	public String getModelerType() {
		return modelerType;
	}
	public boolean isVerbose() {
		return Verbose;
	}
	public int getObjectPendingFinalizationCount() {
		return ObjectPendingFinalizationCount;
	}
	public HeapMemoryUsageType getHeapMemoryUsage() {
		return HeapMemoryUsage;
	}
	public NonHeapMemoryUsageType getNonHeapMemoryUsage() {
		return NonHeapMemoryUsage;
	}
	public String getObjectName() {
		return ObjectName;
	}

	@Override
	public String toString() {
		return new GsonBuilder().create().toJson(this);
	}
}
