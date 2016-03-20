package com.dedup4.storage.domain;

public class FileRecipeContent {

    private String id;
    private String fileRecipeId;
    private String containerId;
    private String chunkId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileRecipeId() {
        return fileRecipeId;
    }

    public void setFileRecipeId(String fileRecipeId) {
        this.fileRecipeId = fileRecipeId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getChunkId() {
        return chunkId;
    }

    public void setChunkId(String chunkId) {
        this.chunkId = chunkId;
    }

}
