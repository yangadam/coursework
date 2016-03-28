package model;

import java.util.List;

import com.google.common.base.MoreObjects;

public class FileStatuses {

	private List<FileStatus> fileStatuses;

	public FileStatuses(List<FileStatus> fileStatuses) {
		this.fileStatuses = fileStatuses;
	}

	public List<FileStatus> getFileStatuses() {
		return fileStatuses;
	}

	public void setFileStatuses(List<FileStatus> fileStatuses) {
		this.fileStatuses = fileStatuses;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("fileStatuses", fileStatuses).toString();
	}
}