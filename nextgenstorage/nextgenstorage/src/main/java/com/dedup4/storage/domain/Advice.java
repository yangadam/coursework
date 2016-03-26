package com.dedup4.storage.domain;

/**
 * @author Yang Mengmeng Created on Mar 26, 2016.
 */
public class Advice extends UpdateInfo {

    private String username;

    private String title;

    private String content;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
