package com.dedup4.storage.webapp.domain;

import com.dedup4.storage.common.domain.UpdateInfo;

/**
 * @author Yang Mengmeng Created on Mar 29, 2016.
 */
public class Notification extends UpdateInfo {

    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
