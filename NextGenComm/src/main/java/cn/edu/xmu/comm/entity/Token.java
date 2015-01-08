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

    //region Constructors

    //region Private Instance Variables
    private String token;
    private Integer uid;
    //endregion

    //region Getters

    /**
     * 构造函数
     *
     * @param token 临时密码
     * @param uid   用户id
     */
    public Token(String token, Integer uid) {
        this.uid = uid;
        this.token = token;
    }
    //endregion

    /**
     * 获得临时密码（主键）
     *
     * @return 临时密码
     */
    @Id
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    //endregion

    /**
     * 获得用户主键
     *
     * @return 用户主键
     */
    @Column(nullable = false)
    public Integer getId() {
        return uid;
    }

    //region Setters
    public void setId(Integer uid) {
        this.uid = uid;
    }
    //endregion

}
