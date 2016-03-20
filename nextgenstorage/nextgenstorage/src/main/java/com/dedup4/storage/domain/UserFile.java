package com.dedup4.storage.domain;

import java.util.Date;

public class UserFile {

    private String id;
    private String userId;
    private String fileName;
    private long size;
    private Date uploadDate;
    private String directory;
    private boolean open;
    private String fingerprint;
    private String fileRecipeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFileRecipeId() {
        return fileRecipeId;
    }

    public void setFileRecipeId(String fileRecipeId) {
        this.fileRecipeId = fileRecipeId;
    }

}
