package com.dedup4.storage.common.domain;

/**
 * @author Yang Mengmeng Created on Mar 22, 2016.
 */
public class FileRecipe extends UpdateInfo {

    private String actualFileName;
    private long fileSize;
    private Boolean deduped;
    private String metadataId;

    public FileRecipe(String id, String actualFileName, long fileSize) {
        this.setId(id);
        this.actualFileName = actualFileName;
        this.fileSize = fileSize;
        this.deduped = false;
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

    public Boolean isDeduped() {
        return deduped;
    }

    public void setDeduped(Boolean deduped) {
        this.deduped = deduped;
    }

    public String getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(String metadataId) {
        this.metadataId = metadataId;
    }

    public String getTempFileName() {
        return this.getId();
    }

}
