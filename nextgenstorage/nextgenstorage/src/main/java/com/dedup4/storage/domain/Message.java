package com.dedup4.storage.domain;

/**
 * @author Yang Mengmeng Created on Mar 29, 2016.
 */
public class Message extends UpdateInfo {

    private String usernameTo;

    private String header;

    private String content;

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
