package com.dedup4.storage.domain;

public class FileRecipe {

    private String id;
    private int refCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

}
