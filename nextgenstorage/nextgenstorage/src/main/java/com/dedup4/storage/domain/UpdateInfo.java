package com.dedup4.storage.domain;

import java.util.Date;

/**
 * @author Yang Mengmeng Created on Mar 22, 2016.
 */
public class UpdateInfo extends IdentityData {

    private Date createDate;

    private Date lastUpdatedTime;

    private String lastUpdateUser;

    public UpdateInfo() {
        this.createDate = new Date();
        this.lastUpdatedTime = new Date();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
}
