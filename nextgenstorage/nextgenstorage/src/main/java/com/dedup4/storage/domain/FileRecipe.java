package com.dedup4.storage.domain;

public class FileRecipe extends UpdateInfo {

    private int refCount;

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

}
