package com.dedup4.storage.domain;

import java.util.Date;

/**
 * @author Yang Mengmeng Created on Mar 22, 2016.
 */
public class TimeInfoData extends IdentityData {

    private Date createdDate;

    private Date lastUpdatedDate;

    public TimeInfoData() {
        this.createdDate = new Date();
        this.lastUpdatedDate = new Date();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

}
