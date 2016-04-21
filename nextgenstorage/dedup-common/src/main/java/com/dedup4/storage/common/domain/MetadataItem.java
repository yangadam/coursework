package com.dedup4.storage.common.domain;

/**
 * @author Yang Mengmeng Created on Apr 20, 2016.
 */
public class MetadataItem {
    private String containerId;
    private String offset;
    private String lenth;

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLenth() {
        return lenth;
    }

    public void setLenth(String lenth) {
        this.lenth = lenth;
    }
}
