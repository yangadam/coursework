package model.hdfs;

import com.google.gson.GsonBuilder;

public class FSNamesystemState {
	private String name;
	private String modelerType;
	private long CapacityTotal;
	private long CapacityUsed;
	private long CapacityRemaining;
	private long TotalLoad;
	private String SnapshotStats;
	private long BlocksTotal;
	private long MaxObjects;
	private long FilesTotal;
	private long PendingReplicationBlocks;
	private long UnderReplicatedBlocks;
	private long ScheduledReplicationBlocks;
	private long PendingDeletionBlocks;
	private long BlockDeletionStartTime;
	private String FSState;
	private long NumLiveDataNodes;
	private long NumDeadDataNodes;
	private long NumDecomLiveDataNodes;
	private long NumDecomDeadDataNodes;
	private long NumDecommissioningDataNodes;
	private long NumStaleDataNodes;
	private long NumStaleStorages;

	public String getName() {
		return name;
	}
	public String getModelerType() {
		return modelerType;
	}
	public long getCapacityTotal() {
		return CapacityTotal;
	}
	public long getCapacityUsed() {
		return CapacityUsed;
	}
	public long getCapacityRemaining() {
		return CapacityRemaining;
	}
	public long getTotalLoad() {
		return TotalLoad;
	}
	public String getSnapshotStats() {
		return SnapshotStats;
	}
	public long getBlocksTotal() {
		return BlocksTotal;
	}
	public long getMaxObjects() {
		return MaxObjects;
	}
	public long getFilesTotal() {
		return FilesTotal;
	}
	public long getPendingReplicationBlocks() {
		return PendingReplicationBlocks;
	}
	public long getUnderReplicatedBlocks() {
		return UnderReplicatedBlocks;
	}
	public long getScheduledReplicationBlocks() {
		return ScheduledReplicationBlocks;
	}
	public long getPendingDeletionBlocks() {
		return PendingDeletionBlocks;
	}
	public long getBlockDeletionStartTime() {
		return BlockDeletionStartTime;
	}
	public String getFSState() {
		return FSState;
	}
	public long getNumLiveDataNodes() {
		return NumLiveDataNodes;
	}
	public long getNumDeadDataNodes() {
		return NumDeadDataNodes;
	}
	public long getNumDecomLiveDataNodes() {
		return NumDecomLiveDataNodes;
	}
	public long getNumDecomDeadDataNodes() {
		return NumDecomDeadDataNodes;
	}
	public long getNumDecommissioningDataNodes() {
		return NumDecommissioningDataNodes;
	}
	public long getNumStaleDataNodes() {
		return NumStaleDataNodes;
	}
	public long getNumStaleStorages() {
		return NumStaleStorages;
	}

	@Override
	public String toString() {
		return new GsonBuilder().create().toJson(this);
	}
}
