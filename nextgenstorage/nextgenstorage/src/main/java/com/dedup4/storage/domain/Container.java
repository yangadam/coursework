package com.dedup4.storage.domain;

public class Container {

    private String id;
    private String path;
    private String fileName;
    private long size;
    private int chunkNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public int getChunkNum() {
        return chunkNum;
    }

    public void setChunkNum(int chunkNum) {
        this.chunkNum = chunkNum;
    }

}
