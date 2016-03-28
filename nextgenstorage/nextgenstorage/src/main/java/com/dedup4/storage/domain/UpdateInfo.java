package com.dedup4.storage.domain;

import java.util.Date;

/**
 * @author Yang Mengmeng Created on Mar 22, 2016.
 */
public class UpdateInfo extends IdentityData {

    private Date createdDate;

    private Date lastModifiedDate;

    private String lastModifiedUser;

    public UpdateInfo() {
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }
}
