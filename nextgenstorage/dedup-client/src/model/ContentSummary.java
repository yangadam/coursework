package model;

import com.google.common.base.MoreObjects;

public class ContentSummary {
	private int directoryCount;
	private int fileCount;
	private long length;
	private long quota;
	private long spaceConsumed;
	private int spaceQuota;

	public int getDirectoryCount() {
		return directoryCount;
	}

	public int getFileCount() {
		return fileCount;
	}

	public long getLength() {
		return length;
	}

	public long getQuota() {
		return quota;
	}

	public long getSpaceConsumed() {
		return spaceConsumed;
	}

	public int getSpaceQuota() {
		return spaceQuota;
	}

	public String getDisplayText() {
		return "Directory Count," + directoryCount + ","
			 + "File Count," + fileCount + ","
			 + "Length," + length + ","
			 + "Quota," + quota + ","
			 + "Space Consumed," + ","
			 + "Space Quota," + spaceQuota;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("directoryCount", directoryCount)
				.add("fileCount", fileCount)
				.add("length", length)
				.add("quota", quota)
				.add("spaceConsumed", spaceConsumed)
				.add("spaceQuota", spaceQuota).toString();
	}
}