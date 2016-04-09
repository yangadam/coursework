package com.dedup4.storage.common.domain;

public class FileRecipe extends UpdateInfo {

    private String actualFileName;
    private long fileSize;
    private Boolean onHdfs;

    public FileRecipe(String md5, String actualFileName, long fileSize) {
        this.setId(md5);
        this.actualFileName = actualFileName;
        this.fileSize = fileSize;
        this.onHdfs = false;
    }

    public String getActualFileName() {
        return actualFileName;
    }

    public void setActualFileName(String actualFileName) {
        this.actualFileName = actualFileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Boolean isOnHdfs() {
        return onHdfs;
    }

    public void setOnHdfs(Boolean onHdfs) {
        this.onHdfs = onHdfs;
    }

    public String getTempFileName() {
        return this.getId();
    }

}
