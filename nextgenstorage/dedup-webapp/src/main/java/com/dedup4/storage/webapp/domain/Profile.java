package com.dedup4.storage.webapp.domain;

import com.dedup4.storage.common.domain.UpdateInfo;

public class Profile extends UpdateInfo {

    private String userId;

    private String sex;

    private String mail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}