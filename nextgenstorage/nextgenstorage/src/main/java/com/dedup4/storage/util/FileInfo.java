package com.dedup4.storage.util;

import java.io.Serializable;

public class FileInfo implements Serializable, Comparable<Object> {

    private static final long serialVersionUID = 2017092119115214141L;
    private String fileName;
    private long fileSize;
    private int isDirectory;
    private int fileId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getIsDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(int isDirectory) {
        this.isDirectory = isDirectory;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public int compareTo(Object o) {

        FileInfo another = (FileInfo) o;

        if (this.isDirectory == 1 && another.getIsDirectory() == 0)
            return -1;

        else if (this.isDirectory == 0 && another.getIsDirectory() == 1)
            return 1;

        else {
            return this.fileName.compareTo(another.getFileName());
        }

    }

}
