package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
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
public class Token extends DataEntity {

    /**
     * 临时密码，作为主键
     */
    @Id
    private String token;

    /**
     * 用户主键
     */
    @Column(nullable = false)
    private Integer uid;

    Token() {
    }

    public Token(String token, Integer uid) {
        this.uid = uid;
        this.token = token;
    }

    public Integer getId() {
        return uid;
    }

    public void setId(Integer uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
