package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.base.MoreObjects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.http.hdfs.HdfsAccess;

public class FileStatus {
	static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static final int STORAGE_UNIT_LEN = 10;
	static final String[] STORAGE_UNIT = new String[] {
			" B", " KB", " MB", " GB", " TB", " PB", " EB", " ZB", " YB", " BB"
	};
	static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("#.00");
	static NumberFormat NUMBER_FORMATTER = NumberFormat.getInstance();


	private long accessTime;
	private long blockSize;
	private String group;
	private long length;
	private long modificationTime;
	private String owner;
	private String pathSuffix;
	private String permission;
	private int replication;
	private String type; // "FILE", "DIRECTORY"
	private String absolutePath;
	private boolean isOpened;

	public FileStatus() {
	}

	public StringProperty accessTimeProperty() {
		return new SimpleStringProperty(TIME_FORMATTER.format(new Date(accessTime)));
	}

	public StringProperty blockSizeProperty() {
		if(!isDirectory()) {
			return new SimpleStringProperty(formatStorageShow(blockSize));
		}
		return new SimpleStringProperty("");
	}

	public StringProperty groupProperty() {
		return new SimpleStringProperty(group);
	}

	public StringProperty lengthProperty() {
		if(!isDirectory()) {
			return new SimpleStringProperty(formatStorageShow(length));
		}
		return new SimpleStringProperty("");
	}

	public StringProperty modificationTimeProperty() {
		return new SimpleStringProperty(TIME_FORMATTER.format(new Date(modificationTime)));
	}

	public StringProperty ownerProperty() {
		return new SimpleStringProperty(owner);
	}

	public StringProperty pathSuffixProperty() {
		return new SimpleStringProperty(getPathSuffix());
	}

	public StringProperty permissionProperty() {
		if(permission != null) {
			return new SimpleStringProperty(HdfsAccess.parsePermission(permission));
		}
		return new SimpleStringProperty("");
	}

	public StringProperty replicationProperty() {
		if(!isDirectory()) {
			return new SimpleStringProperty(String.valueOf(replication));
		} else {
			return new SimpleStringProperty("");
		}
	}

	public StringProperty typeProperty() {
		return new SimpleStringProperty(type);
	}

	public boolean isDirectory() {
		if(type != null) {
			return type.equals("FILE") ? false : true;
		}
		return false;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getPathSuffix() {
		if(pathSuffix == null || pathSuffix.equals("")) {
			return "/";
		}
		return pathSuffix;
	}

	public boolean isOpened() {
		return isOpened;
	}

	public void setOpened(boolean isOpened) {
		this.isOpened = isOpened;
	}

	public void setPathSuffix(String pathSuffix) {
		this.pathSuffix = pathSuffix;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("accessTime", accessTime)
				.add("blockSize", blockSize)
				.add("group", group)
				.add("length", length)
				.add("modificationTime", modificationTime)
				.add("owner", owner)
				.add("pathSuffix", pathSuffix)
				.add("permission", permission)
				.add("replication", replication)
				.add("type", type).toString();
	}

	private String formatStorageShow(long x) {
		int index = 0;
		double xTmp = x;
		while(xTmp > 1024) {
			xTmp /= 1024;
			index++;
		}
		if (index > STORAGE_UNIT_LEN) {
			return NUMBER_FORMATTER.format(blockSize) + STORAGE_UNIT[0];
		}

		return String.valueOf(DECIMAL_FORMATTER.format(xTmp) + STORAGE_UNIT[index]);
	}
}