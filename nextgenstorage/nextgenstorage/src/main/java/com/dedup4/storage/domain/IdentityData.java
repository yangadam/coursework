package com.dedup4.storage.domain;

import org.springframework.data.annotation.Id;

/**
 * @author Yang Mengmeng Created on Mar 22, 2016.
 */
public class IdentityData {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
