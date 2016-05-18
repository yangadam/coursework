package com.dedup4.storage.webapp.domain;

import com.dedup4.storage.common.domain.IdentityData;

import java.util.Date;

public class UserOperation extends IdentityData {

    private Type type;
    private Date date;
    private long count;
    private long size;

    public UserOperation(Type type) {
        this.type = type;
        this.date = new Date();
        this.count = 1L;
        this.size = 0;
    }

    public UserOperation(Type type, long size) {
        this(type);
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public enum Type {
        UPLOAD, DOWNLOAD, LOGIN
    }

}
