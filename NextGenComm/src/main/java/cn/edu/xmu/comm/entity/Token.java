package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户临时密码，用于记住我功能
 *
 * @author Mengmeng Yang
 * @version 12/21/2014 0021
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Token {

    @Id
    private String id;

    @Column(nullable = false)
    private String token;

    Token() {
    }

    public Token(Integer id, String token) {
        this.id = String.valueOf(id);
        this.token = token + "," + this.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
