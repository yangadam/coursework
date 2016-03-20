package com.dedup4.storage.domain;

public class ChunkInfo {

    private String id;
    private String fingerprint;
    private int refCount;
    private int length;
    private int offset;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

}
