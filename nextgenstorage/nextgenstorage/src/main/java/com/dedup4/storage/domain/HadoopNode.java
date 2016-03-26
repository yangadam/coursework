package com.dedup4.storage.domain;

/**
 * @author Yang Mengmeng Created on Mar 26, 2016.
 */
public class HadoopNode extends UpdateInfo {

    private String host;

    private String name;

    private int memorySize;

    private int diskSize;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(int diskSize) {
        this.diskSize = diskSize;
    }

}
